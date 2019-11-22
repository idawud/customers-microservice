package io.turntabl.springwebservice;

import io.turntabl.springwebservice.DAO.CustomerDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class CustomersMicroservice {
	public static void main(String[] args) {
		SpringApplication.run(CustomersMicroservice.class, args);

	}

	@Bean
	public CustomerDAO getCustomerDAO() {
		return new CustomerDAO();
	}

}
