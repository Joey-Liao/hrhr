package com.wiwj.appinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppinterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppinterfaceApplication.class, args);
    }


    /*将springboot项目打包成war*/
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        // 注意这里要指向原先用main方法执行的Application启动类
//        return builder.sources(AppinterfaceApplication.class);
//    }

}
