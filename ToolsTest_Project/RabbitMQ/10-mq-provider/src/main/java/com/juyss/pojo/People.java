package com.juyss.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: People
 * @package com.juyss.pojo
 * @project RabbitMQ
 * @date 2021/9/19 14:13
 */
@Component
@Data
public class People {

    private static final long serialVersionUID=1L;

    private Long id;

    private String name;

    private String gender;

    private String queueName;

}
