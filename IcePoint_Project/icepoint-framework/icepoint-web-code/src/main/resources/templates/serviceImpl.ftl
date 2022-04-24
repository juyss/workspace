package ${code.packageName}.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import ${code.packageName}.dao.${code.entityName}Dao;
import ${code.packageName}.entity.${code.entityName};
import ${code.packageName}.service.${code.entityName}Service;

@Service
@RequiredArgsConstructor
public class ${code.entityName}ServiceImpl  implements ${code.entityName}Service {

    @Getter
    private final SampleRepository repository;
}