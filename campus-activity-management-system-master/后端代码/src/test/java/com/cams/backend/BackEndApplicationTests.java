package com.cams.backend;

import com.cams.backend.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BackEndApplicationTests {

    @Resource
    private UsersMapper userMapper;

    @Test
    void contextLoads() {
    }

    }
