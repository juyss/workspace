package com.juyss;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecurityDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("102850");
        //$2a$10$s9tN3gflVSesHCRGOER7Fer5IFFoDyODtezS3tWofkSufygMJjtGG
        System.out.println(encode);
    }

}
