package dev.da0hn.keycloak.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan(
  "dev.da0hn.keycloak.course.configuration.properties"
)
@EnableConfigurationProperties
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
