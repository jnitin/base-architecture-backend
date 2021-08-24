package com.backend.api.config.security.permission;

import com.backend.api.annotation.security.CrudPermission;
import com.backend.api.annotation.security.RequiresPermission;
import com.backend.api.enums.Permission;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.AbstractMethodSecurityMetadataSource;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PermissionAnnotationBasedSecurityMetadataSource extends AbstractMethodSecurityMetadataSource {
  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return List.of();
  }

  @Override
  public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {
    final var annotation = findAnnotation(method, targetClass, RequiresPermission.class);
    final var crudAnnotation = findAnnotation(targetClass, CrudPermission.class);

    if (annotation == null && crudAnnotation == null) {
      return List.of();
    }

    final List<Permission> permissionList = new ArrayList<>();

    if (annotation != null) {
      permissionList.addAll(Arrays.stream(annotation.value()).collect(Collectors.toList()));
    }

    if (crudAnnotation != null) {
      switch (method.getName()) {
        case "insert":
          permissionList.add(crudAnnotation.create());
          break;
        case "update":
          permissionList.add(crudAnnotation.update());
          break;
        case "delete":
          permissionList.add(crudAnnotation.delete());
          break;
        case "find":
          permissionList.add(crudAnnotation.findById());
          break;
        case "listAll":
          permissionList.add(crudAnnotation.listAll());
          break;
      }
    }

    var securityAttribute = new PermissionSecurityAttribute(permissionList);

    return List.of(securityAttribute);
  }

  private <A extends Annotation> A findAnnotation(
      Method method,
      Class<?> targetClass,
      Class<A> annotationClass
  ) {
    // The method may be on an interface, but we need attributes from the target
    // class.
    // If the target class is null, the method will be unchanged.
    Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
    A annotation = AnnotationUtils.findAnnotation(specificMethod, annotationClass);

    if (annotation != null) {
      logger.debug(annotation + " found on specific method: " + specificMethod);
      return annotation;
    }

    // Check the original (e.g. interface) method
    if (specificMethod != method) {
      annotation = AnnotationUtils.findAnnotation(method, annotationClass);

      if (annotation != null) {
        logger.debug(annotation + " found on: " + method);
        return annotation;
      }
    }

    // Check the class-level (note declaringClass, not targetClass, which may not
    // actually implement the method)
    annotation = AnnotationUtils.findAnnotation(specificMethod.getDeclaringClass(),
        annotationClass);

    if (annotation != null) {
      logger.debug(annotation + " found on: "
          + specificMethod.getDeclaringClass().getName());
      return annotation;
    }

    return null;
  }

  private <A extends Annotation> A findAnnotation(Class<?> targetClass, Class<A> annotationClass) {
    return AnnotationUtils.findAnnotation(targetClass, annotationClass);
  }

}
