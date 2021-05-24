package com.dept.filesite;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class DeptFileSiteApplication {

    public static void main(String[] args) {

        SpringApplication.run(DeptFileSiteApplication.class, args);
    }



}
