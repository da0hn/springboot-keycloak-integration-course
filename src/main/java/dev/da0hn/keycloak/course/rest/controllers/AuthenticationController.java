package dev.da0hn.keycloak.course.rest.controllers;

import dev.da0hn.keycloak.course.configuration.properties.KeycloakProperties;
import dev.da0hn.keycloak.course.rest.client.KeycloakRestClient;
import dev.da0hn.keycloak.course.rest.client.TokenResponsePayload;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

  private final KeycloakRestClient keycloakRestClient;

  private final KeycloakProperties keycloakProperties;

  @PostMapping("/token")
  public ResponseEntity<TokenResponsePayload> authenticate(
    @RequestBody final LoginRequestPayload request
  ) {
    final var payload = this.keycloakRestClient.getToken(
      request.username(),
      request.password(),
      "password",
      this.keycloakProperties.clientId(),
      this.keycloakProperties.clientSecret()
    );
    return ResponseEntity.ok(payload);
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<TokenResponsePayload> refreshToken(@RequestParam("refresh-token") final String refreshToken) {
    final var payload = this.keycloakRestClient.refreshToken(
      refreshToken,
      "refresh_token",
      this.keycloakProperties.clientId(),
      this.keycloakProperties.clientSecret()
    );
    return ResponseEntity.ok(payload);
  }

}
