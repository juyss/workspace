package com.icepoint.framework.generator;

import com.icepoint.framework.core.util.OrderedComparator;
import com.icepoint.framework.core.util.OrderedUtils;
import com.icepoint.framework.core.util.RecursiveUtils;
import com.icepoint.framework.data.domain.ParentIdHierarchy;
import com.icepoint.framework.generator.entity.*;
import com.icepoint.framework.generator.loader.TableMetadataLoader;
import com.squareup.javapoet.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.maven.plugin.logging.Log;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.icepoint.framework.generator.GeneratorUtils.*;

/**
 * @author Jiawei Zhao
 */
public class EntityGenerator {

    private static final String VALUE = "value";

    private final ClassLoader classLoader;

    private final Log log;

    private final Resource resource;

    private Boolean isStdEntity;

    private FieldMetadata idField;

    private FieldMetadata orderField;

    private FieldMetadata parentIdField;

    private final List<MethodSpec> extentMethods = new ArrayList<>();

    private Modifier[] modifiers;

    private TypeName supperClass;

    private List<? extends TypeName> superInterfaces;

    private List<AnnotationSpec> annotations;

    private List<FieldSpec> fieldSpecs;

    private boolean initialized = false;

    private final String basePackage;

    private String packageName;

    /**
     * 实体类的全类名
     */
    private String className;

    public EntityGenerator(String basePackage, Resource resource, ClassLoader classLoader, Log log) {
        this.resource = resource;
        this.classLoader = classLoader;
        this.log = log;
        this.basePackage = basePackage;
    }

    public void init() {

        loadKeyFields();

        modifiers = new Modifier[]{Modifier.PUBLIC};
        supperClass = getSupperClass();
        superInterfaces = getInterfaces();
        annotations = getAnnotations();
        fieldSpecs = getFields();

        LinkedList<String> paths = new LinkedList<>();
        RecursiveUtils.execute(resource.getModule(),
                Module::getParent,
                Objects::nonNull,
                module -> paths.addFirst(module.getPath()));

        Assert.notEmpty(paths, "无法解析paths");
        String relativePackage = String.join("/", paths);

        if (!basePackage.endsWith(".")) {
            packageName = basePackage + ".module." + relativePackage + ".entity";
        } else {
            packageName = basePackage + "module." + relativePackage + ".entity";
        }

        initialized = true;
    }

    private List<AnnotationSpec> getAnnotations() {
        List<AnnotationSpec> annotationSpecs = new ArrayList<>();

        String nameAlias = resource.getNameAlias();
        if (StringUtils.hasText(nameAlias)) {
            annotationSpecs.add(AnnotationSpec.builder(ApiModel.class)
                    .addMember(VALUE, "$S", nameAlias).build());
        }

        annotationSpecs.add(AnnotationSpec.builder(Table.class)
                .addMember("name", "$S", resource.getTable().getName()).build());
        annotationSpecs.add(AnnotationSpec.builder(Entity.class).build());

        annotationSpecs.add(AnnotationSpec.builder(NoArgsConstructor.class).build());
        annotationSpecs.add(AnnotationSpec.builder(AllArgsConstructor.class).build());
        annotationSpecs.add(AnnotationSpec.builder(SuperBuilder.class).build());
        annotationSpecs.add(AnnotationSpec.builder(Getter.class).build());
        annotationSpecs.add(AnnotationSpec.builder(Setter.class).build());
        annotationSpecs.add(AnnotationSpec.builder(ToString.class).build());

        annotationSpecs.add(AnnotationSpec.builder(Generated.class)
                .addMember(VALUE, "$S", EntityGenerator.class.getName()).build());

        return annotationSpecs;
    }

    private List<FieldSpec> getFields() {

        return resource.getTable().getFields().stream()
                .sorted(OrderedComparator.INSTANCE)
                .filter(field -> {

                    if (Boolean.TRUE.equals(field.getPrimaryKey())) {
                        return false;
                    }

                    return !isStdEntity() || !isStdEntityProperty(field.getName());

                })
                .map(field -> FieldSpec.builder(extractFieldClass(field), field.getName(), Modifier.PRIVATE)
                        .addAnnotations(getFieldAnnotations(field))
                        .build())
                .collect(Collectors.toList());
    }

    private Iterable<AnnotationSpec> getFieldAnnotations(FieldMetadata field) {

        List<AnnotationSpec> annotationSpecs = new ArrayList<>();

        String name = field.getName();
        String nameAlias = field.getNameAlias();
        String defaultValue = field.getDefaultValue();
        Boolean optional = field.getOptional();
        Integer maxLength = field.getMaxLength();
        Integer max = field.getMax();
        Integer min = field.getMin();

        if (StringUtils.hasText(nameAlias)) {
            annotationSpecs.add(AnnotationSpec.builder(ApiModelProperty.class)
                    .addMember(VALUE, "$S", nameAlias).build());
        }

        if (optional != null && !optional) {
            annotationSpecs.add(AnnotationSpec.builder(NotNull.class).build());
        }

        if (maxLength != null) {
            annotationSpecs.add(AnnotationSpec.builder(Length.class)
                    .addMember("max", "$L", maxLength).build());
        }

        if ((max != null || min != null) && Number.class.isAssignableFrom(extractFieldClass(field))) {
            if (max != null && min != null) {

                annotationSpecs.add(AnnotationSpec.builder(Range.class)
                        .addMember("min", "$L", min)
                        .addMember("max", "$L", max).build());

            } else if (max != null) {

                annotationSpecs.add(AnnotationSpec.builder(Max.class)
                        .addMember(VALUE, "$L", max).build());

            } else {

                annotationSpecs.add(AnnotationSpec.builder(Min.class)
                        .addMember(VALUE, "$L", min).build());
            }
        }

        if (StringUtils.hasText(defaultValue)) {
            annotationSpecs.add(AnnotationSpec.builder(ColumnDefault.class)
                    .addMember(VALUE, "%S", defaultValue).build());
        }

        annotationSpecs.add(AnnotationSpec.builder(Column.class)
                .addMember("name", "$S", toUnderScore(name)).build());

        return annotationSpecs;
    }

    private String toUnderScore(String str) {
        String regex = "([A-Z])";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            String target = matcher.group();
            str = str.replaceAll(target, "_" + target.toLowerCase());
        }
        return str;
    }

    private List<? extends TypeName> getInterfaces() {

        List<TypeName> typeNames = new ArrayList<>();

        if (orderField != null) {
            typeNames.add(TypeName.get(Ordered.class));

            MethodSpec getOrder = MethodSpec.methodBuilder("getOrder")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(TypeName.INT)
                    .addStatement("return $T.nullable(this.order)", OrderedUtils.class)
                    .build();
            extentMethods.add(getOrder);
        }

        if (parentIdField != null) {
            typeNames.add(ParameterizedTypeName.get(ParentIdHierarchy.class, extractFieldClass(parentIdField)));
        }

        return typeNames;
    }

    private TypeName getSupperClass() {
        return isStdEntity() ? getStdEntityTypeName(idField) : getBasicEntityTypeName(idField);
    }

    public void loadKeyFields() {

        for (FieldMetadata field : resource.getTable().getFields()) {

            if (idField == null && field.getPrimaryKey()) {

                Assert.isTrue(field.getName().equals("id"), "主键字段名必须为id");
                idField = field;

            } else if (orderField == null && field.getName().equals("order")) {

                Assert.isTrue(extractFieldClass(field) == Integer.class, "order的类型必须是Integer");
                orderField = field;

            } else if (parentIdField == null && field.getName().equals("parentId"))

                parentIdField = field;
        }

        if (idField == null) {
            throw new IllegalStateException("找不到主键字段");
        }

        if (parentIdField != null && !idField.getJavaType().equals(parentIdField.getJavaType())) {
            throw new IllegalStateException("id和parentId的类型必须一致");
        }
    }

    public boolean isStdEntity() {

        if (isStdEntity == null) {
            isStdEntity = resource.getTable().getFields().stream()
                    .map(FieldMetadata::getName)
                    .distinct()
                    .filter(this::isStdEntityProperty)
                    .count() == getStdEntityPropertyNumber();
        }

        return isStdEntity;
    }

    private int getStdEntityPropertyNumber() {
        return 8;
    }

    private boolean isStdEntityProperty(String fieldName) {
        return "id".equals(fieldName) || "appId".equals(fieldName) || "ownerId".equals(fieldName)
                || "createTime".equals(fieldName) || "createUser".equals(fieldName)
                || "updateTime".equals(fieldName) || "updateUser".equals(fieldName)
                || "deleted".equals(fieldName);
    }

    public void generate(String generateSourceRoot) {

        TypeSpec entity = getEntity();

        File entityFile = new File(generateSourceRoot +
                "/" + packageName.replace(".", "/"),
                entity.name + ".java");

        if (hasClass(className, classLoader) && !entityFile.exists()) {
            log.info("该实体类已存在: " + className);
        }

        try {
            JavaFile.builder(packageName, entity)
                    .indent("    ")
                    .build()
                    .writeTo(new File(generateSourceRoot));

            log.info("实体类生成成功: " + className);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TypeSpec getEntity() {

        if (!initialized) {
            throw new IllegalStateException("EntityGenerator未初始化");
        }

        String entityName = StringUtils.capitalize(resource.getName());
        className = packageName + "." + entityName;
        return TypeSpec.classBuilder(entityName)
                .addModifiers(modifiers)
                .superclass(supperClass)
                .addSuperinterfaces(superInterfaces)
                .addAnnotations(annotations)
                .addFields(fieldSpecs)
                .addMethods(extentMethods)
                .build();
    }

    public Resource getResource() {
        return resource;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public void handleAssociations(Map<Long, EntityGenerator> generatedEntitiesByTableId) {

        TableMetadata table = resource.getTable();
        List<TableLink> links = table.getLinks();
        if (CollectionUtils.isEmpty(links)) {
            return;
        }

        for (TableLink link : links) {

            Long linkTableId = link.getLinkTableId();
            TableMetadata linkTable = link.getLinkTable();

            switch (link.getAssociationType()) {
                case MANY_TO_MANY:

                    EntityGenerator linkGenerator = generatedEntitiesByTableId.get(linkTableId);
                    Assert.notNull(linkGenerator, "找不到关联的实体");

                    TypeSpec linkEntity = linkGenerator.getEntity();

                    ClassName linkEntityClassName = ClassName.bestGuess(linkGenerator.getClassName());
                    String linkFieldName = linkEntity.name + "s";
                    FieldMetadata fkField = link.getFkField();
                    TableMetadata joinTable = TableMetadataLoader.getTableMetadataById(fkField.getTableId());

                    String otherTableFkFieldName = Optional.of(linkTable)
                            .orElse(TableMetadataLoader.getTableMetadataById(linkTable.getId()))
                            .getLinks()
                            .stream()
                            .filter(l -> l.getLinkTableId().equals(table.getId()))
                            .map(TableLink::getFkField)
                            .map(FieldMetadata::getName)
                            .findAny()
                            .orElseThrow(() -> new IllegalStateException("找不到多对多的外键字段元数据"));

                    FieldSpec manyToManyField = FieldSpec.builder(ParameterizedTypeName.get(ClassName.get(List.class), linkEntityClassName),
                            linkFieldName, Modifier.PRIVATE)
                            .addAnnotation(AnnotationSpec.builder(ApiModelProperty.class)
                                    .addMember(VALUE, "$S", "关联的" + linkTable.getNameAlias()).build())
                            .addAnnotation(ManyToMany.class)
                            .addAnnotation(AnnotationSpec.builder(JoinTable.class)
                                    .addMember("name", "$S", joinTable.getName())
                                    .addMember("joinColumns", "$L", AnnotationSpec.builder(JoinColumn.class)
                                            .addMember("name", "$S", toUnderScore(fkField.getName()))
                                            .addMember("referencedColumnName", "$S", "id").build())
                                    .addMember("inverseJoinColumns", "$L", AnnotationSpec.builder(JoinColumn.class)
                                            .addMember("name", "$S", toUnderScore(otherTableFkFieldName))
                                            .addMember("referencedColumnName", "$S", "id").build())
                                    .build())
                            .build();

                    fieldSpecs.add(manyToManyField);

                    break;
            }
        }
    }
}
