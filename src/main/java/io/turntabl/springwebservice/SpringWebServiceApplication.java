package io.turntabl.springwebservice;

import io.turntabl.springwebservice.controllers.CustomerDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@EnableSwagger2
@SpringBootApplication
public class SpringWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebServiceApplication.class, args);
	}

	@Bean
	public DataSource dbConnect() {
		return new DriverManagerDataSource("jdbc:postgresql://localhost/tcms", "dawud", "dawud");
	}

	@Bean
	public CustomerDAO getCustomerDAO() {
		return new CustomerDAO();
	}

}
