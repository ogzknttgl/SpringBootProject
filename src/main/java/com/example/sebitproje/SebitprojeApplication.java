package com.example.sebitproje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import com.example.sebitproje.KullaniciEntity;
import com.example.sebitproje.KullaniciRepository;
import com.example.sebitproje.ProjeEntity;
import com.example.sebitproje.ProjeRepository;
@EntityScan("com.example.sebitproje")
@SpringBootApplication
public class SebitprojeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SebitprojeApplication.class, args);
	}


}
