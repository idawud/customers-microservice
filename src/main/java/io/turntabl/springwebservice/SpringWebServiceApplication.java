package io.turntabl.springwebservice;

import io.turntabl.springwebservice.controllers.Maths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SpringWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebServiceApplication.class, args);
	}

	@Bean
	public Maths maths(){
		return new Maths();
	}
}
