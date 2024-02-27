package org.vaadin.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

@SpringBootApplication
@Configuration
//@EnableAsync
@EnableFeignClients
@Theme(value = "my-theme")
@PWA(name = "My App", shortName = "My App", offlineResources = {})
public class Application implements AppShellConfigurator {

	@Bean 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
    private static final long serialVersionUID = -1501968338403573964L;

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
