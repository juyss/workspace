package com.icepoint.framework.data.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.joining;

/**
 * 扩展了Mybatis Plus的{@link TableInfo}，增加了可配置前缀的{@link #getAllSqlSelect(String)}方法
 *
 * @author Jiawei Zhao
 */
@EqualsAndHashCode(callSuper = true)
public class ExtendedTableInfo extends TableInfo {

    private final TableInfo delegate;

    public ExtendedTableInfo(TableInfo delegate) {
        super(delegate.getEntityType());
        this.delegate = delegate;
    }

    /**
     * 获取对应表的所有字段的Select语句，并且在字段前拼接prefix + .
     *
     * @param prefix 字段前缀
     * @return 所有字段的Select语句
     */
    public String getAllSqlSelect(String prefix) {

        String keySqlSelect = prefix + DOT + getKeySqlSelect();

        String fieldsSqlSelect = getFieldList().stream()
                .filter(TableFieldInfo::isSelect)
                .map(TableFieldInfo::getSqlSelect)
                .map(s -> prefix + DOT + s)
                .collect(joining(COMMA));

        if (StringUtils.isNotBlank(keySqlSelect) && StringUtils.isNotBlank(fieldsSqlSelect)) {
            return keySqlSelect + COMMA + fieldsSqlSelect;
        } else if (StringUtils.isNotBlank(fieldsSqlSelect)) {
            return fieldsSqlSelect;
        }
        return keySqlSelect;
    }

    @Override
    public String getSqlStatement(String sqlMethod) {
        return this.delegate.getSqlStatement(sqlMethod);
    }

    @Override
    public String getKeySqlSelect() {
        return this.delegate.getKeySqlSelect();
    }

    @Override
    public String getAllSqlSelect() {
        return this.delegate.getAllSqlSelect();
    }

    @Override
    public String chooseSelect(Predicate<TableFieldInfo> predicate) {
        return this.delegate.chooseSelect(predicate);
    }

    @Override
    public String getKeyInsertSqlProperty(String prefix, boolean newLine) {
        return this.delegate.getKeyInsertSqlProperty(prefix, newLine);
    }

    @Override
    public String getKeyInsertSqlColumn(boolean newLine) {
        return this.delegate.getKeyInsertSqlColumn(newLine);
    }

    @Override
    public String getAllInsertSqlPropertyMaybeIf(String prefix) {
        return this.delegate.getAllInsertSqlPropertyMaybeIf(prefix);
    }

    @Override
    public String getAllInsertSqlColumnMaybeIf() {
        return this.delegate.getAllInsertSqlColumnMaybeIf();
    }

    @Override
    public String getAllSqlWhere(boolean ignoreLogicDelFiled, boolean withId, String prefix) {
        return this.delegate.getAllSqlWhere(ignoreLogicDelFiled, withId, prefix);
    }

    @Override
    public String getAllSqlSet(boolean ignoreLogicDelFiled, String prefix) {
        return this.delegate.getAllSqlSet(ignoreLogicDelFiled, prefix);
    }

    @Override
    public String getLogicDeleteSql(boolean startWithAnd, boolean isWhere) {
        return this.delegate.getLogicDeleteSql(startWithAnd, isWhere);
    }

    @Override
    public Class<?> getEntityType() {
        return this.delegate.getEntityType();
    }

    @Override
    public IdType getIdType() {
        return this.delegate.getIdType();
    }

    @Override
    public String getTableName() {
        return this.delegate.getTableName();
    }

    @Override
    public String getResultMap() {
        return this.delegate.getResultMap();
    }

    @Override
    public boolean isAutoInitResultMap() {
        return this.delegate.isAutoInitResultMap();
    }

    @Override
    public boolean isInitResultMap() {
        return this.delegate.isInitResultMap();
    }

    @Override
    public boolean isKeyRelated() {
        return this.delegate.isKeyRelated();
    }

    @Override
    public String getKeyColumn() {
        return this.delegate.getKeyColumn();
    }

    @Override
    public String getKeyProperty() {
        return this.delegate.getKeyProperty();
    }

    @Override
    public Class<?> getKeyType() {
        return this.delegate.getKeyType();
    }

    @Override
    public KeySequence getKeySequence() {
        return this.delegate.getKeySequence();
    }

    @Override
    public List<TableFieldInfo> getFieldList() {
        return this.delegate.getFieldList();
    }

    @Override
    public String getCurrentNamespace() {
        return this.delegate.getCurrentNamespace();
    }

    @Override
    public boolean isLogicDelete() {
        return this.delegate.isLogicDelete();
    }

    @Override
    public boolean isUnderCamel() {
        return this.delegate.isUnderCamel();
    }

    @Override
    public MybatisConfiguration getConfiguration() {
        return this.delegate.getConfiguration();
    }

    public boolean isWithInsertFill() {
        return this.delegate.isWithInsertFill();
    }

    public boolean isWithUpdateFill() {
        return this.delegate.isWithUpdateFill();
    }

    public boolean isWithVersion() {
        return this.delegate.isWithVersion();
    }

    public TableFieldInfo getVersionFieldInfo() {
        return this.delegate.getVersionFieldInfo();
    }
}
