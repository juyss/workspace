package ${code.packageName}.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ucsmy.core.ext.BasicServiceImpl;
import com.ucsmy.core.vo.RetMsg;
import ${code.packageName}.dao.${code.entityName}Dao;
import ${code.packageName}.entity.${code.entityName};
import ${code.packageName}.service.${code.entityName}Service;

@Service
public class ${code.entityName}ServiceImpl extends BasicServiceImpl<${code.entityName}, ${code.entityName}Dao> implements ${code.entityName}Service {

}