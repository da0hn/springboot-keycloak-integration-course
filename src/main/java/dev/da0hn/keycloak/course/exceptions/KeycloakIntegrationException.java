package dev.da0hn.keycloak.course.exceptions;

import org.springframework.http.ProblemDetail;

import java.io.Serial;

public abstract class KeycloakIntegrationException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -5627414258401837831L;

  protected KeycloakIntegrationException(final String message) {
    super(message);
  }

  protected KeycloakIntegrationException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public abstract ProblemDetail toProblemDetail();

}
