package dev.da0hn.keycloak.course.rest.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

  @GetMapping("/admin")
  @PreAuthorize("hasAnyAuthority('ADMIN_READ', 'ADMIN_WRITE')")
  public String getAdminResource() {
    return "Admin Resource";
  }

  @GetMapping("/operation")
  @PreAuthorize("hasAnyAuthority('OPERATION_READ', 'OPERATION_WRITE')")
  public String getOperationResource() {
    return "Operation Resource";
  }

}
