package com.societymanagement.society_service.utils;

import lombok.Data;

@Data
public class JwtUserPrincipal {
    private final Long userId;
    private final Long societyId;

    public JwtUserPrincipal(Long userId, Long societyId) {
        this.userId = userId;
        this.societyId = societyId;
    }
}