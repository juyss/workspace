package com.icepoint.framework.core;

import com.icepoint.framework.core.function.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.util.*;

import static com.icepoint.framework.core.function.Processors.listToFieldArray;

/**
 * @author Jiawei Zhao
 */
@Slf4j
class FunctionTests {

    @Test
    void testListNum() {
        List<Object> list = new ArrayList<>();
        list.add(Pair.of("1", "1"));
        list.add(Pair.of("1", "1"));
        list.add(Pair.of("1", "2"));

        ListNum listNum = Processors.listNum();

        // 两个字段都去重，结果应该是2
        Map<String, Object> result1 = listNum.process(new ListNum.Parameters(list, true, Arrays.asList("first", "second")));
        Assertions.assertEquals(2, result1.get("size"));

        // 只对第一个字段去重，结果应该是1
        Map<String, Object> result2 = listNum.process(new ListNum.Parameters(list, true, Collections.singletonList("first")));
        Assertions.assertEquals(1, result2.get("size"));

        // 只对第二个字段去重，结果应该是2
        Map<String, Object> result3 = listNum.process(new ListNum.Parameters(list, true, Collections.singletonList("second")));
        Assertions.assertEquals(2, result3.get("size"));

        log.info("testListNum 测试通过");
    }

    @Test
    void testListAddMap() {
        TestUser testUser = new TestUser("张三", 1);
        List<Object> list = new ArrayList<>();
        list.add(testUser);
        Map<String, Object> map = new HashMap<>();
//        map.put("test", new TestUser2("222"));
//        map.put("test2", new TestUser2("2222"));

        final Map<String, Object> result = Processors.listAddMap().process(new ListAddMap.Parameters(list, map, null, false));
        Assertions.assertEquals(4, ((Map<?, ?>) ((Collection<?>) result.get("collection")).iterator().next()).size());

        log.info("testListAddMap 测试通过");
    }

    @Test
    void testListStats(){
        TestUser testUser = new TestUser("张三", 1);
        TestUser testUser1 = new TestUser("张三", 2);
        TestUser testUser3 = new TestUser("张三", 3);
        TestUser testUser4 = new TestUser("张三", 3);
        TestUser testUser5 = new TestUser("张三", 3);
        List<Object> list = new ArrayList<>();
        list.add(testUser);
        list.add(testUser1);
        list.add(testUser3);
        list.add(testUser4);
        list.add(testUser5);
        ListStats listStats = new ListStats();
         Map<String, Object> map = listStats.processInternal(new ListStats.Parameters(list, "num", 1));
        log.info("ListStats 测试通过");
    }

    @Test
    void TestListContainObj(){
        TestUser testUser = new TestUser("张三", 1);
        TestUser testUser1 = new TestUser("张三", 2);
        TestUser testUser3 = new TestUser("张三", 3);
        TestUser testUser4 = new TestUser("张三", 3);
        TestUser testUser5 = new TestUser("张三", 3);
        List<Object> list = new ArrayList<>();
        list.add(testUser);
        list.add(testUser1);
        list.add(testUser3);
        list.add(testUser4);
        list.add(testUser5);
        ListContainObj listContainObj = new ListContainObj();

        List<Object> value = new ArrayList<>();
        value.add(8);

         Map<String, Object> map = listContainObj.processInternal(new ListContainObj.Parameters(list, "num", value, 2));
        log.info("ListContainObj 测试通过");
    }

//    @Test
//    void testMakeMap() {
//
//        Map<String, Object> result = makeMap().process(new Object[]{ "a", 1, "b", 2, "c" });
//        Map<?, ?> map = (Map<?, ?>) result.get("map");
//        Assertions.assertNotNull(map);
//
//        Assertions.assertEquals(1, map.get("a"));
//        Assertions.assertEquals(2, map.get("b"));
//        Assertions.assertNull(map.get("c"));
//
//        log.info("testMakeMap 测试通过");
//    }

    @Test
    void testListToFieldArray() {

        List<Object> list = Arrays.asList(
                new TestUser("a", 1),
                new TestUser("b", null),
                new TestUser("c", 3)
        );

        Map<String, Object> result1 = listToFieldArray().process(new ListToFieldArray.Parameters(list, "userName", "default", true));
        Assertions.assertEquals(Arrays.asList("a", "b", "c"), result1.get("array"));

        Map<String, Object> result2 = listToFieldArray().process(new ListToFieldArray.Parameters(list, "age", 5, true));
        Assertions.assertEquals(Arrays.asList(1, 5, 3), result2.get("array"));

        Map<String, Object> result3 = listToFieldArray().process(new ListToFieldArray.Parameters(list, "third", "default", true));
        Assertions.assertEquals(Collections.singletonList("default"), result3.get("array"));

        log.info("testListToFieldArray 测试通过");
    }


    @Test
    void testMapJoin() {

    }

    @Test
    void testBatchObjSplitByField(){
        TestUser testUser = new TestUser("张三", 1);
        TestUser testUser1 = new TestUser("张三", 2);
        TestUser testUser3 = new TestUser("张三", 3);
        TestUser testUser4 = new TestUser("李四,王五,田器", 3);
        TestUser testUser5 = new TestUser("张三", 3);

        TestUser2 testUser6 = new TestUser2(new String[]{"战三", "王五"},5);

        List<Object> list = new ArrayList<>();
        list.add(testUser);
        list.add(testUser1);
        list.add(testUser3);
        list.add(testUser4);
        list.add(testUser6);
        BatchObjSplitByField batchObjSplitByField = new BatchObjSplitByField();
        Map<String, Object> map = batchObjSplitByField.process(new BatchObjSplitByField.Parameters(list, "userName"));
        log.info("testBatchObjSplitByField 测试通过");
    }

    @Test
    void testListGrpByFld(){
        TestUser testUser = new TestUser("张三", 1);
        TestUser testUser1 = new TestUser("张三", 2);
        TestUser testUser3 = new TestUser("张三", 2);
        TestUser testUser4 = new TestUser("李四,王五,田器", 3);
        TestUser testUser5 = new TestUser("张三", 3);

        TestUser2 testUser6 = new TestUser2(new String[]{"战三", "王五"},5);

        List<Object> list = new ArrayList<>();
        list.add(testUser);
        list.add(testUser1);
        list.add(testUser3);
        list.add(testUser4);
        list.add(testUser5);
        list.add(testUser6);
        ListGrpByFld listGrpByFld = new ListGrpByFld();
        Map<String, Object> map = listGrpByFld.process(new ListGrpByFld.Parameters(list,"num"));
        log.info("testListGrpByFld 测试通过");
    }

    @Test
    void testConvertTreeBranch (){
        TreeUser treeUser = new TreeUser(1,"张三",2,null,null);
        TreeUser treeUser1 = new TreeUser(2,"张三",2,null,null);
        TreeUser treeUser2 = new TreeUser(3,"张三",2,1,null);
        TreeUser treeUser3 = new TreeUser(4,"张三",2,2,null);
        TreeUser treeUser4 = new TreeUser(5,"张三",2,3,null);
        TreeUser treeUser5 = new TreeUser(6,"张三",2,4,null);
        TreeUser treeUser6 = new TreeUser(7,"张三",2,5,null);
        List<Object> list = new ArrayList<>();
        list.add(treeUser);
        list.add(treeUser1);
        list.add(treeUser2);
        list.add(treeUser3);
        list.add(treeUser4);
        list.add(treeUser5);
        list.add(treeUser6);
        ConvertTreeBranch convertTreeBranch = new ConvertTreeBranch();
        Map<String, Object> map = convertTreeBranch.process(new ConvertTreeBranch.Parameters(list, "id", "parentId", "childen"));
        System.out.println(map);

    }


    @Test
    void testListToString(){
        ListToString listToString = new ListToString();
        List list = new ArrayList();
        list.add(11L);
        list.add(12L);
        Map<String, Object> process = listToString.process(new ListToString.Parameters(list, null));
        System.out.println(process);
    }



}
