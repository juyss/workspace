package com.icepoint.framework.workorder.work.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.io.File;

public interface ExcelService {
	/**
	 * 通用导入Excle
	 * @param file 上传的文件
	 * @param startLine 数据起始行
	 * @param fields 导入的数据字段名
	 * @return
	 */
	List<Map<String, Object>> uploadFile(File file, Integer startLine, List<String> fields);
	
	/**
	 * 导入数据处理
	 * @param list 要处理的数据
	 * @param assetsId 对应资产
	 * @param mode 处理模式 1-导入 2-更新
	 * @return
	 */
	Integer uploadProcess(List<Map<String, Object>> list,Long assetsId,Integer mode);
}
