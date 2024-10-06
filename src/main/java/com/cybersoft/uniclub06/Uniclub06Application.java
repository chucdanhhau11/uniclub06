package com.cybersoft.uniclub06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // thông baáo cho phép lưu trữ dữ catching dữ liệu
public class Uniclub06Application {

	public static void main(String[] args) {
		SpringApplication.run(Uniclub06Application.class, args);
	}

}
