package com.juyss.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: HelloController
 * @Desc:
 * @package com.juyss.controller
 * @project KuangStudy-SpringMVC
 * @date 2020/9/11 16:33
 */
public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView view = new ModelAndView();

        view.addObject("msg", "Hello Spring-Mvc");

        view.setViewName("Hello");
        return view;
    }
}
