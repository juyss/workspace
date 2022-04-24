import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: LoadPathTest
 * @Desc: 测试文件输入流的读取路径
 * @package PACKAGE_NAME
 * @project TestDemo
 * @date 2020/7/21 11:41
 */
public class LoadPathTest {
    //main方法中
    public static void main(String[] args) {
        FileInputStream is = null;
        try {
            is = new FileInputStream("InputStream\\src\\hi.txt");
            System.out.println(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        is = null;
        try {
            is = new FileInputStream(new File("InputStream\\src\\hi.txt"));
            System.out.println(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //单元测试方法中
    @Test
    public void Test01() {
        FileInputStream is = null;
        try {
            is = new FileInputStream(new File("src\\hi.txt"));
            System.out.println(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
