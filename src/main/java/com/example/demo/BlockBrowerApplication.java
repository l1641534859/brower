package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@MapperScan("com.example.demo.mapper")
public class BlockBrowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockBrowerApplication.class, args);
	}

}
