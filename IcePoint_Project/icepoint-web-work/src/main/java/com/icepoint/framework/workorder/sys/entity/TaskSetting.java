package com.icepoint.framework.workorder.sys.entity;

import java.util.Map;

import javax.persistence.Column;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统通知设置实体类
 *
 * @author admin
 * @since 2021-07-20 14:08:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_task_setting")
public class TaskSetting extends LongStdEntity {
	/**
	 * 关联字典id
	 */
    @Column(name = "item_id")
    private Long itemId;
    
	/**
	 * 图标
	 */
    @Column(name = "icon")
    private String icon;
    
	/**
	 *  是否声音提示
	 */
    @Column(name = "is_voice")
    private Integer isVoice;
    
	/**
	 *  播放次数
	 */
    @Column(name = "play_count")
    private Integer playCount;
    
	/**
	 * 提示音ID
	 */
    @Column(name = "voice_id")
    private Long voiceId;
    
	/**
	 *  是否开启弹窗
	 */
    @Column(name = "open_alert")
    private Integer openAlert;
    
	/**
	 *  隐藏方式
	 */
    @Column(name = "hide_type")
    private Integer hideType;
    
	/**
	 * 备注
	 */
    @Column(name = "note")
    private String note;
	
    public TaskSetting(Map<String,Object> map){
    	
    }
}
