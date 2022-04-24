package test;

import java.util.ArrayList;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Test01
 * @Desc: 删除链表中重复出现的元素
 *      例如：[10,20,30,20,10,50]
 *      结果：[10,20,30,50]
 * @package test
 * @project TestDemo
 * @date 2020/7/20 16:26
 */
public class Test01 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(60);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(30);
        list.add(20);
        list.add(10);
        list.add(50);
        ArrayList<Integer> duplicate = removeDuplicate(list);
        System.out.println(duplicate);
    }

    /**
     *      思路：从第一个元素开始，与之后所有的元素对比，如果出现重复，就把这个元素放进新的链表，然后结束循环
     *           一轮循环结束时，然后判断这个元素是否存在于新的链表中，如果不存在，证明这个元素是没有出现过重复的，也需要加入进新的链表
     * @param list 需要处理的链表
     * @return 返回去重过后的链表
     */
    public static ArrayList<Integer> removeDuplicate(ArrayList<Integer> list){

        //创建一个新链表用于保存不重复的元素
        ArrayList<Integer> handleList = new ArrayList<>();

        //取链表中的每一个元素与后面的元素对比
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {

                //如果有重复
                if (list.get(i).equals(list.get(j))){

                    //如果新的链表中不包含这个元素，就把这个元素添加到新的链表中
                    if (!handleList.contains(list.get(i))){
                        handleList.add(list.get(i));
                        break;
                    }
                }
            }

            //没有重复的元素也需要加到新的链表
            if (!handleList.contains(list.get(i))){
                handleList.add(list.get(i));
            }

        }
        return handleList;
    }
}
