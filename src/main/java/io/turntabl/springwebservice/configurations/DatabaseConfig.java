package io.turntabl.springwebservice.configurations;

import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;

@Configuration
public class DatabaseConfig {
    String initQuery = "" +
            "CREATE TABLE IF NOT EXISTS customers(\n" +
            "   id serial PRIMARY KEY,\n" +
            "   name VARCHAR (150) NOT NULL,\n" +
            "   address VARCHAR (150) NOT NULL,\n" +
            "   telephoneNumber VARCHAR (50) NOT NULL,\n" +
            "   email VARCHAR (355) UNIQUE NOT NULL,\n" +
            "   active BOOL DEFAULT 't'\n" +
            ");";

    @Bean
    @Profile("postgres")
    public DataSource postgresDataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");

        URI dbUri;
        try {
            dbUri = new URI(databaseUrl);
        }
        catch (URISyntaxException ignored) {
            //log.error(String.format("Invalid DATABASE_URL: %s", databaseUrl), e);
            return null;
        }

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
                + dbUri.getPort() + dbUri.getPath();

        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .password(password)
                .url(dbUrl)
                .username(username)
                .build();
        return dataSource;
    }

    /**
    @Bean
    public DataSource dbConnect() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String dbUrl = "jdbc:postgres://fatozvbztjkqfh:3550fdca7e812d85c97af0afc5c90cd050bc117d701260d689096165b03882e3@ec2-174-129-27-3.compute-1.amazonaws.com:5432/de4kfrlvkp6d3v";
        return new DriverManager(dbUrl, "fatozvbztjkqfh", "3550fdca7e812d85c97af0afc5c90cd050bc117d701260d689096165b03882e3");
    }*/

}