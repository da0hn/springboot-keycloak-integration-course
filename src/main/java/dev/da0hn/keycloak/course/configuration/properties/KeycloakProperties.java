package dev.da0hn.keycloak.course.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak")
public record KeycloakProperties(
  String clientId,
  String clientSecret,
  String realm,
  String url,
  String jwkSetUri

) {
}
