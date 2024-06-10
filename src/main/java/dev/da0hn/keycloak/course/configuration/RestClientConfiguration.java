package dev.da0hn.keycloak.course.configuration;

import dev.da0hn.keycloak.course.configuration.properties.KeycloakProperties;
import dev.da0hn.keycloak.course.rest.client.KeycloakRestClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@AllArgsConstructor
public class RestClientConfiguration {

  private final KeycloakProperties keycloakProperties;

  @Bean
  public KeycloakRestClient keycloakRestClient() {
    final var restClient = RestClient.builder()
      .baseUrl(this.keycloakProperties.url())
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .defaultStatusHandler(HttpStatusCode::is4xxClientError, (req, res) -> {
        throw new RuntimeException("Ocorreu um erro ao chamar o serviço de autenticação");
      })
      .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
        throw new RuntimeException("Ocorreu um erro no servidor ao chamar o serviço de autenticação");
      })
      .build();

    return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
      .build()
      .createClient(KeycloakRestClient.class);
  }

}
