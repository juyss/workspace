package com.icepoint.framework.web.security.controller;

import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseBuilder;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public Response<Void> register(@RequestBody User user) {
        service.register(user);
        return ResponseBuilder.justOk();
    }
}
