package com.icepoint.framework.code.response.vo;

import com.icepoint.framework.code.sysfunction.entity.SysFunction;
import com.icepoint.framework.code.xml.entity.Process;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FunctionVO {

    private SysFunction sysFunction;

    private Process process;
}
