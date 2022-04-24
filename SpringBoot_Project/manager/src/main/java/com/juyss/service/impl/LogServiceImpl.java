package com.juyss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juyss.entity.SysLog;
import com.juyss.mapper.SysLogMapper;
import com.juyss.service.LogService;
import org.springframework.stereotype.Service;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: LogServiceImpl
 * @Desc:
 * @package com.juyss.service.impl
 * @project manager
 * @date 2021/1/21 13:09
 */
@Service
public class LogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements LogService {
}