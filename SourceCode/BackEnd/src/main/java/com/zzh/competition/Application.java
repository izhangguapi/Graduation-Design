package com.zzh.competition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("|---------- 正在启动...... ----------|");
        try {
            SpringApplication.run(Application.class, args);
            System.out.println("|---------- 启动成功 ----------|");
        } catch (Exception e) {
            System.out.println("|---------- 启动失败 ----------|");
        }
    }
}
