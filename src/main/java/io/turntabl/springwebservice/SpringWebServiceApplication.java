package io.turntabl.springwebservice;

import io.turntabl.springwebservice.DAOs.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SpringWebServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringWebServiceApplication.class, args);
	}

	@Bean
	public CustomerDAO getCustomerDAO() {
		return new CustomerDAO();
	}
 
}
