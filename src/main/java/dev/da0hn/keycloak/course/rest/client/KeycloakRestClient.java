package dev.da0hn.keycloak.course.rest.client;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface KeycloakRestClient {

  @PostExchange(
    url = "/auth/realms/${keycloak.realm}/protocol/openid-connect/token",
    contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  ResponseEntity<TokenResponsePayload> getToken(
    @RequestParam("username") String username,
    @RequestParam("password") String password,
    @RequestParam("grant_type") String grantType,
    @RequestParam("client_id") String clientId,
    @RequestParam("client_secret") String clientSecret
  );

}
