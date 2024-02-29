package org.vaadin.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
@EnableFeignClients
public class Application implements AppShellConfigurator {
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
