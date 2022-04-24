package ${code.packageName}.dao;
import com.icepoint.framework.data.dao.BaseRepository;
import ${code.packageName}.entity.${code.entityName};
import org.springframework.stereotype.Repository;

@MultiplyPathRepositoryRestResource(path = "/${code.uri}")
@Repository
public interface ${code.entityName}Repository extends BasicRepository<${code.entityName}, Long> {

}
