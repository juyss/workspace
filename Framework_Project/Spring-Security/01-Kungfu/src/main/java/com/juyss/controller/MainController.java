package com.juyss.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MainController
 * @Desc:
 * @package com.juyss.controller
 * @project Spring-Security
 * @date 2020/10/19 20:13
 */
@Controller
public class MainController {

    private final Logger log = Logger.getLogger(AdminController.class);

    @RequestMapping("/level1/{path}")
    public String level1Page(@PathVariable("path")String path){
        log.debug("level1/"+path);
        return "level1/"+path;
    }

    @RequestMapping("/level2/{path}")
    public String level2Page(@PathVariable("path")String path){
        log.debug(path);
        return "level2/"+path;
    }

    @RequestMapping("/level3/{path}")
    public String level3Page(@PathVariable("path")String path){
        log.debug(path);
        return "level3/"+path;
    }

}
