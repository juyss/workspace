import com.juyss.mapper.FileMapper;
import com.juyss.pojo.File;
import com.juyss.service.FileService;
import com.juyss.service.FileServiceImpl;
import com.juyss.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: FileMapperTest
 * @Desc: FileMapper测试类
 * @package PACKAGE_NAME
 * @project TestDemo
 * @date 2020/8/26 0:27
 */
public class FileMapperTest {

    private SqlSession sqlSession = MybatisUtils.getSqlSession();
    private FileService fileService = new FileServiceImpl();

    @Test
    public void queryAllTest() {
        List<File> fileList = fileService.queryAll(sqlSession);
        for (File file : fileList) {
            System.out.println(file);
        }

    }

    @Test
    public void queryFileByIdTest() {
        File file = fileService.queryFileById(sqlSession, 2);
        System.out.println(file);
    }

    @Test
    public void insertTest(){
        File file = new File(6, "Rick", "Anime", "ScCode");
        Boolean insert = fileService.insert(sqlSession, file);
        System.out.println(insert);
    }

    @Test
    public void updateTest(){
        File file = new File(4, "嘻嘻", "path1", "password1");
        Boolean update = fileService.update(sqlSession, file);
    }

    @Test
    public void deleteTest(){
        Boolean delete = fileService.delete(sqlSession, 5);
        System.out.println(delete);
    }
}
