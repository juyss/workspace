package com.icepoint.framework.code;

import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.icepoint.framework.code.xml.entity.Definitions;
import com.icepoint.framework.code.xml.entity.Group;
import com.icepoint.framework.code.xml.entity.Process;
import com.icepoint.framework.core.io.Serializers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-09 11:12
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class Object2XmlTest {

    @Test
    public void test() throws FileNotFoundException {

        File xml = new File("G:\\workspace\\icepoint-framework\\icepoint-web-code\\src\\main\\resources\\static\\doc\\com.ucsmy.prj.usrAction.add.xml");

        Process process = Serializers.xml().deserialize(new FileInputStream(xml), Process.class);

        System.out.println(process);

        File newFile = new File("G:\\workspace\\icepoint-framework\\icepoint-web-code\\src\\main\\resources\\static\\doc\\test.xml");

        Serializers.xml().serialize(process, new FileOutputStream(newFile), customizer -> customizer
                .withDefaultPrettyPrinter()
                .withRootName("process")
                .with(ToXmlGenerator.Feature.WRITE_XML_DECLARATION));

    }

    @Test
    public void test01() throws FileNotFoundException {

        File xml = new File("G:\\workspace\\icepoint-framework\\icepoint-web-code\\src\\main\\resources\\static\\doc\\com.ucsmy.prj.usrAction.add.xml");

        Process process = Serializers.xml().deserialize(new FileInputStream(xml), Process.class);

        Group group = new Group();
        List<Process> processes = new ArrayList<>();
        process.setName("节点一");
        processes.add(process);
        processes.add(process);
        group.setProcesses(processes);

        Definitions definitions = new Definitions();
        ArrayList<Group> groups = new ArrayList<>();
        group.setName("组节点一");
        groups.add(group);
        groups.add(group);
        definitions.setGroups(groups);

        File newFile = new File("G:\\workspace\\icepoint-framework\\icepoint-web-code\\src\\main\\resources\\static\\doc\\test.xml");

        Serializers.xml().serialize(definitions, new FileOutputStream(newFile), customizer -> customizer
                .withDefaultPrettyPrinter()
                .with(ToXmlGenerator.Feature.WRITE_XML_DECLARATION));

    }

    @Test
    public void test02() throws FileNotFoundException {
        File file = new File("G:\\workspace\\icepoint-framework\\icepoint-web-code\\src\\main\\resources\\static\\doc\\ProcessList.xml");
        Definitions definitions = Serializers.xml().deserialize(new FileInputStream(file), Definitions.class);
        System.out.println(definitions);
        File newFile = new File("G:\\workspace\\icepoint-framework\\icepoint-web-code\\src\\main\\resources\\static\\doc\\test.xml");

        Serializers.xml().serialize(definitions, new FileOutputStream(newFile), customizer -> customizer
                .withDefaultPrettyPrinter()
                .with(ToXmlGenerator.Feature.WRITE_XML_DECLARATION));
    }
}
