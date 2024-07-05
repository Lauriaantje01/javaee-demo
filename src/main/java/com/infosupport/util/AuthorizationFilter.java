package com.infosupport.util;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;


@Provider
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (resourceInfo.getResourceMethod().isAnnotationPresent(NotSecured.class)) return;

        if ("false".equals(requestContext.getHeaderString("Authorization"))) {
            throw new ForbiddenException();
        }
    }
}
