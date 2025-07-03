package com.societymanagement.society_service.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {

    public static JwtUserPrincipal getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof JwtUserPrincipal)) {
            throw new RuntimeException("User not authenticated or principal type invalid");
        }
        return (JwtUserPrincipal) authentication.getPrincipal();
    }

    public static long getUserId() {
        return getPrincipal().getUserId();
    }
    public static long getSocietyId() {
        return getPrincipal().getSocietyId();
    }


}
