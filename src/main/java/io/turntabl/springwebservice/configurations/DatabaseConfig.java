package io.turntabl.springwebservice.configurations;

import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;


    @Bean
    public DataSource dataSource() {
        String initQuery = "" +
                "CREATE TABLE IF NOT EXISTS customers(\n" +
                "   id serial PRIMARY KEY,\n" +
                "   name VARCHAR (150) NOT NULL,\n" +
                "   address VARCHAR (150) NOT NULL,\n" +
                "   telephoneNumber VARCHAR (50) NOT NULL,\n" +
                "   email VARCHAR (355) UNIQUE NOT NULL,\n" +
                "   active BOOL DEFAULT 't'\n" +
                ");";
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setConnectionInitSql(initQuery);
        return new HikariDataSource(config);
    }

    /*
    @Bean
    public DataSource dbConnect() {
        return new DriverManagerDataSource(dbUrl, "fatozvbztjkqfh", "3550fdca7e812d85c97af0afc5c90cd050bc117d701260d689096165b03882e3");
    }*/

}