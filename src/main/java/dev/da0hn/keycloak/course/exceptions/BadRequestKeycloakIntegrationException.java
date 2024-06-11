package dev.da0hn.keycloak.course.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.io.Serial;

public class BadRequestKeycloakIntegrationException extends KeycloakIntegrationException {

  @Serial
  private static final long serialVersionUID = -8605737371280351332L;

  protected BadRequestKeycloakIntegrationException(final String message) {
    super(message);
  }

  @Override
  public ProblemDetail toProblemDetail() {
    final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, this.getMessage());
    problemDetail.setTitle("Keycloak Integration Error");
    return problemDetail;
  }

}
