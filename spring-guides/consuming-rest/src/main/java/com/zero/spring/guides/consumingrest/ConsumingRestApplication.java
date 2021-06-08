package com.zero.spring.guides.consumingrest;

import com.zero.spring.guides.consumingrest.entity.Hitokoto;
import com.zero.spring.guides.consumingrest.entity.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger logger = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner runner(RestTemplate restTemplate) {
		return args -> {
			for(int i = 0; i < 10; i++) {
				Hitokoto quote = restTemplate.getForObject("https://v1.hitokoto.cn", Hitokoto.class);
				logger.info(quote.toString());
			}
		};
	}
}
