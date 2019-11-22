package io.turntabl.springwebservice.configurations;

import com.zaxxer.hikari.*;
import io.turntabl.springwebservice.pubsub.Publisher;
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

    @Bean
    @Profile("postgres")
    public DataSource postgresDataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");
        URI dbUri;
        try {
            dbUri = new URI(databaseUrl);
        }
        catch (URISyntaxException ignored) {
            Publisher.publish(String.format("Invalid DATABASE_URL= %s [ERROR]", databaseUrl));
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
        Publisher.publish(String.format("DATABASE_URL Connection Successful= %s [CONNECT]", databaseUrl));
        return dataSource;
    }


}