package com.mert;

/**
 * Created by Yasin Mert on 25.02.2017.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BootsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootsecurityApplication.class, args);
	}
}
