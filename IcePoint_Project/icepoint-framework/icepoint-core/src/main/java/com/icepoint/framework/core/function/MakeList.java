package com.icepoint.framework.core.function;

import com.icepoint.framework.core.util.MapUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class MakeList {
    /**
     * 创建数组
     *
     * @param type 数组对象类型
     *             1	字节型
     *             2	短整型
     *             3	整型
     *             4	长整型
     *             5	浮点型
     *             6	双精度型
     *             7	布尔型
     *             8	字符串型
     *             9	日期
     *             10	时间
     *             11 对象，此时要求根据第一个对象的对象类型确定，后续的对象都需要和第一个对象类型一样
     * @param args 不定长参数
     * @return map Map 键值对象
     */
    public static Map<String, Object> execute(Integer type, Object... args) {
        Map<String, Object> result = new HashMap<>();
        if (ObjectUtils.isEmpty(type) || args.length < 1) {
            return Collections.singletonMap("list", null);
        }

        if(type.equals(1)){
			List<Character> list = new ArrayList<>(args.length);
			Character[] characters = Arrays.stream(args).toArray(Character[]::new);
			Collections.addAll(list,characters);
			return Collections.singletonMap("list", list);
		}
        else if(type.equals(2)){
			List<Short> list = new ArrayList<>(args.length);
			Short[] shorts = Arrays.stream(args).toArray(Short[]::new);
			Collections.addAll(list,shorts);
			return Collections.singletonMap("list", list);
		}
        else if(type.equals(3)){
			List<Integer> list = new ArrayList<>(args.length);
			Integer[] integers = Arrays.stream(args).toArray(Integer[]::new);
			Collections.addAll(list,integers);
			return Collections.singletonMap("list", integers);
		}
        else if(type.equals(4)){
			List<Long> list = new ArrayList<>(args.length);
			Long[] longs = Arrays.stream(args).toArray(Long[]::new);
			Collections.addAll(list,longs);
			return Collections.singletonMap("list", list);
		}
        else if(type.equals(5)){
			List<Float> list = new ArrayList<>(args.length);
			Float[] floats = Arrays.stream(args).toArray(Float[]::new);
			Collections.addAll(list,floats);
			return Collections.singletonMap("list", floats);
		}
        else if(type.equals(6)){
			List<Double> list = new ArrayList<>(args.length);
			Double[] doubles = Arrays.stream(args).toArray(Double[]::new);
			Collections.addAll(list,doubles);
			return Collections.singletonMap("list", doubles);
		}
        else if(type.equals(7)){
			List<Boolean> list = new ArrayList<>(args.length);
			Boolean[] booleans = Arrays.stream(args).toArray(Boolean[]::new);
			Collections.addAll(list,booleans);
			return Collections.singletonMap("list", booleans);
		}
        else if(type.equals(8)){
			List<String> list = new ArrayList<>(args.length);
			String[] strings = Arrays.stream(args).toArray(String[]::new);
			Collections.addAll(list,strings);
			return Collections.singletonMap("list", strings);
		}
        else if(type.equals(9)){
			List<Date> list = new ArrayList<>(args.length);
			Date[] dates = Arrays.stream(args).toArray(Date[]::new);
			Collections.addAll(list,dates);
			return Collections.singletonMap("list", dates);
		}
        else if(type.equals(10)){
			List<LocalDateTime> list = new ArrayList<>(args.length);
			LocalDateTime[] localDateTimes = Arrays.stream(args).toArray(LocalDateTime[]::new);
			Collections.addAll(list,localDateTimes);
			return Collections.singletonMap("list", localDateTimes);
		}
        else{//11
			List<Map<String, Object>> list = Arrays
					.stream(args)
					.map(item -> MapUtils.objectToMap(item)).collect(Collectors.toList());
			return Collections.singletonMap("list", list);
		}
    }
}
