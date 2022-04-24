package com.icepoint.framework.icepoint.web.crewschedule.entity;

import com.icepoint.framework.web.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName UserWithSchedule
 * @description
 * @since 2021-07-30 15:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithSchedule extends User {

    /*
     * 排班信息
     */
    private ToolSchedule toolSchedule;
}
