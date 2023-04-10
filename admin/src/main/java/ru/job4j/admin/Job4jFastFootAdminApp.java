package ru.job4j.admin;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class Job4jFastFootAdminApp {

    private static ConfigurableApplicationContext context;

    private static Environment env;

    public static void main(String[] args) {

        context = SpringApplication.run(Job4jFastFootAdminApp.class, args);
    }

    public static void restart(Object port) {
        ApplicationArguments args = context.getBean(ApplicationArguments.class);
        String[] sourceArgs1 = args.getSourceArgs();
        String[] sourceArgs = new String[]{(String) port};
        Thread thread = new Thread(() -> {
            context.close();
            SpringApplication application = new SpringApplication(Job4jFastFootAdminApp.class);
            System.setProperty("server.port", sourceArgs[0]);
            context = application.run(sourceArgs1);
        });

        thread.setDaemon(false);
        thread.start();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofMillis(300000))
                .setReadTimeout(Duration.ofMillis(300000)).build();
    }
}
