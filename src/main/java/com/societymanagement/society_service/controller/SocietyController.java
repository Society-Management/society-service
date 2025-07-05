package com.societymanagement.society_service.controller;

import com.societymanagement.society_service.dtos.SocietyDto;
import com.societymanagement.society_service.entity.Society;
import com.societymanagement.society_service.exception.CustomException;
import com.societymanagement.society_service.service.SocietyService;
import com.societymanagement.society_service.utils.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/society")
public class SocietyController {

    private final SocietyService societyService;

    @Autowired
    public SocietyController(SocietyService societyService) {
        this.societyService = societyService;
    }
    @GetMapping
    public String getUser() {
        return "Admin" + CurrentUser.getUserId();
    }
    public ResponseEntity<?> getSocietyDetails() {
        try {
            SocietyDto society = societyService.getSocietyDetails();
            return ResponseEntity.ok(society);
        } catch (CustomException ex) {
            // If CustomException was thrown, it is already handled by GlobalExceptionHandler
            throw ex;
        } catch (Exception ex) {
            // For any unexpected exception
            throw new CustomException("An unexpected error occurred while fetching society details");
        }
    }
}

