package com.yidong;

		import org.mybatis.spring.annotation.MapperScan;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yidong.mapper")
public class WechatGdutFundApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatGdutFundApplication.class, args);
	}
}
