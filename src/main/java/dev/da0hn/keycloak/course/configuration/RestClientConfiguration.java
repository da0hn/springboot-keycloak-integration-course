package dev.da0hn.keycloak.course.configuration;

import dev.da0hn.keycloak.course.configuration.properties.KeycloakProperties;
import dev.da0hn.keycloak.course.exceptions.InternalErrorKeycloakException;
import dev.da0hn.keycloak.course.rest.client.KeycloakRestClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
@Configuration
@AllArgsConstructor
public class RestClientConfiguration {

  private final KeycloakProperties keycloakProperties;

  @Bean
  public KeycloakRestClient keycloakRestClient(final ConfigurableBeanFactory configurableBeanFactory) {
    final var restClient = RestClient.builder()
      .baseUrl(this.keycloakProperties.url())
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .defaultStatusHandler(HttpStatusCode::is4xxClientError, (req, res) -> {
        log.error("Ocorreu um erro ao chamar {} {}", req.getURI(), req.getURI());
        throw new BadRequestException("Ocorreu um erro ao chamar o serviço de autenticação");
      })
      .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
        log.error("Ocorreu um erro ao chamar {} {}", req.getURI(), req.getURI());
        throw new InternalErrorKeycloakException("Ocorreu um erro no servidor ao chamar o serviço de autenticação");
      })
      .build();

    return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
      // https://github.com/spring-projects/spring-framework/issues/28492
      .embeddedValueResolver(configurableBeanFactory::resolveEmbeddedValue)
      .build()
      .createClient(KeycloakRestClient.class);
  }

}
