package com.icepoint.framework.core.function;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class MakeMap {
	/**
	 * 创建Map
	 * @param type 数组对象类型
	 *        1	字节型
	 *        2	短整型
	 *        3	整型
	 *        4	长整型
	 *        5	浮点型
	 *        6	双精度型
	 *        7	布尔型
	 *        8	字符串型
	 *        9	日期
	 *        10	时间
	 *        11 对象，此时要求根据第一个对象的对象类型确定，后续的对象都需要和第一个对象类型一样
	 * @param args 不定长参数
	 * @return
	 * map Map 键值对象
	 */
	public static Map<String,Object> execute(Integer type,Object... args){
		int len = args.length;
		String key = null;
        Object map = null;
		switch(type){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			Map<String,String> mp8 = new HashMap<String,String>();
			for (int i = 0; i < len; i=i+2) {
				key = String.valueOf(args[i]);
				if (null == key || StringUtils.isEmpty(key)){
					continue;
				}
				if (i+1<len){
					mp8.put(key, String.valueOf(args[i+1]));
				}
				else{
					mp8.put(key, null);
				}
	        }
			
			map=mp8;
			break;
		case 9:
			break;
		case 10:
			break;
		default:
			Map<String,Object> mp11 = new HashMap<String,Object>();
			for (int i = 0; i < len; i=i+2) {
				key = String.valueOf(args[i]);
				if (null == key || StringUtils.isEmpty(key)){
					continue;
				}
				if (i+1<len){
					mp11.put(key, args[i+1]);
				}
				else{
					mp11.put(key, null);
				}
	        }
			
			map=mp11;
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("map", map);
		return result;
	}
}
