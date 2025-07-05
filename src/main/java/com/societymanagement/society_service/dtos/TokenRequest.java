package com.societymanagement.society_service.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class TokenRequest {
    @NotBlank(message = "Bearer token is required")
    public String bearerToken;
}
