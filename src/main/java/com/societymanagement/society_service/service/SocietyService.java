package com.societymanagement.society_service.service;

import com.societymanagement.society_service.entity.Society;
import com.societymanagement.society_service.exception.CustomException;
import com.societymanagement.society_service.repository.SocietyRepository;
import com.societymanagement.society_service.utils.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class SocietyService {

    private final SocietyRepository societyRepository;

    public SocietyService(SocietyRepository societyRepository) {
        this.societyRepository = societyRepository;
    }

    public Society getSocietyDetails(){
        return societyRepository.findById(CurrentUser.getSocietyId())
                .orElseThrow(() -> new CustomException("Society not found with id: " + CurrentUser.getSocietyId()));
    }
}
