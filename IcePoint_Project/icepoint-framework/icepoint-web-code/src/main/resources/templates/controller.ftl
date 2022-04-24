package ${code.packageName}.web;

import ${code.packageName}.entity.${code.entityName};
import ${code.packageName}.service.${code.entityName}Service;
import org.springframework.web.bind.annotation.*;
import com.icepoint.framework.web.system.controller.GenericController;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
* ${code.entityName}表控制层
* @author makejava
* @since ${.now}
*/
@RestController
@RequestMapping("${code.uri}")
@RequiredArgsConstructor
public class ${code.entityName}Controller extends GenericController<${code.entityName}service,${code.entityName},Long>{

        @Resource
        private final ${code.entityName}Service  service;

        /**
        *
        *页查询所有数据,可以做条件查询
        */
       public List<${code.entityName}> get(){
                return null;
        }
}
