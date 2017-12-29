package com.xzchaoo.archaius2.ext.springbootwebdemo;

import com.netflix.archaius.DefaultConfigLoader;
import com.xzchaoo.archaius2.ext.spring.Archaius2ConfigConfigurer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author xzchaoo
 * @date 2017/12/29
 */
@SpringBootApplication
public class DemoApp {
	@Bean
	public Archaius2ConfigConfigurer archaius2ConfigConfigurer() {
		return b -> {
			DefaultConfigLoader loader = DefaultConfigLoader.builder().build();
			b.withConfig("business", loader.newLoader().load("business.properties"));
			b.withConfig("default", loader.newLoader().load("default-config.properties"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApp.class, args);
	}
}
