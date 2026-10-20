package br.com.marceloflp.spring_boot_with_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootWithCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithCacheApplication.class, args);
	}

}
