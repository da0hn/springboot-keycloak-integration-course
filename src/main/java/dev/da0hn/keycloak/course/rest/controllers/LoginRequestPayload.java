package dev.da0hn.keycloak.course.rest.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestPayload(
  @NotBlank(message = "Username is required")
  String username,
  @NotBlank(message = "Password is required")
  @Size(min = 6, message = "Password must be at least 6 characters long")
  String password
) {
}
