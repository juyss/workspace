import com.icepoint.framework.sample.SampleApplication;
import com.icepoint.framework.web.system.dao.TableMetadataMapper;
import com.icepoint.framework.web.system.entity.TableMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Jiawei Zhao
 */
@ActiveProfiles("dev")
@SpringBootTest(classes = SampleApplication.class)
class SampleTests {

    @Autowired
    private TableMetadataMapper mapper;

    @Test
    void testMybatisPagination() {
        PageRequest request = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<TableMetadata> page = mapper.findAll(request);
        System.out.println(page);
    }

}
