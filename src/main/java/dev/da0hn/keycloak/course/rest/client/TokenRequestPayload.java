package dev.da0hn.keycloak.course.rest.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenRequestPayload(
  String username,
  String password,
  @JsonProperty("grant_type")
  String grantType,
  @JsonProperty("client_id")
  String clientId,
  @JsonProperty("client_secret")
  String clientSecret
) {

  public static TokenRequestPayload withPasswordGrantType(
    final String username,
    final String password,
    final String clientId,
    final String clientSecret
  ) {
    return new TokenRequestPayload(
      username,
      password,
      "password",
      clientId,
      clientSecret
    );
  }

}
