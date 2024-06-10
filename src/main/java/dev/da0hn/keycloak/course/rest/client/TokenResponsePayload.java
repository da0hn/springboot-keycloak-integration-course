package dev.da0hn.keycloak.course.rest.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponsePayload(
  @JsonProperty("access_token")
  String accessToken,
  @JsonProperty("refresh_token")
  String refreshToken,
  @JsonProperty("expires_in")
  Long expiresIn,
  @JsonProperty("refresh_expires_in")
  Long refreshExpiresIn,
  @JsonProperty("token_type")
  String tokenType,
  @JsonProperty("not-before-policy")
  Long notBeforePolicy,
  @JsonProperty("session_state")
  String sessionState,
  @JsonProperty("scope")
  String scope
) {
}
