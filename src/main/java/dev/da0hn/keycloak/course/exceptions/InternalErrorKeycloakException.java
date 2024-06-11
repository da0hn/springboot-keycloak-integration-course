package dev.da0hn.keycloak.course.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.io.Serial;

public class InternalErrorKeycloakException extends KeycloakIntegrationException {

  @Serial
  private static final long serialVersionUID = 5812724555826687237L;

  public InternalErrorKeycloakException(final String message) {
    super(message);
  }

  public InternalErrorKeycloakException(final String message, final Throwable cause) {
    super(message, cause);
  }

  @Override
  public ProblemDetail toProblemDetail() {
    final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, this.getMessage());
    problemDetail.setTitle("Keycloak Integration Error");
    return problemDetail;
  }

}
