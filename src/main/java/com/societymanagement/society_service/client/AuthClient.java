package com.societymanagement.society_service.client;

import com.societymanagement.society_service.dtos.TokenRequest;
import com.societymanagement.society_service.dtos.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", url = "${auth.service.url}")
public interface AuthClient {
    @PostMapping("/auth/validate")
    ResponseEntity<TokenResponse> validateToken(@RequestBody TokenRequest request);
}
