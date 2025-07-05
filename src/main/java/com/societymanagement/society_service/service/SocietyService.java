package com.societymanagement.society_service.service;

import com.societymanagement.society_service.mapper.SocietyMapper;
import com.societymanagement.society_service.dtos.SocietyDto;
import com.societymanagement.society_service.entity.Society;
import com.societymanagement.society_service.exception.CustomException;
import com.societymanagement.society_service.repository.SocietyRepository;
import com.societymanagement.society_service.utils.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class SocietyService {

    private final SocietyRepository societyRepository;
    private final SocietyMapper societyMapper;

    public SocietyService(SocietyRepository societyRepository, SocietyMapper societyMapper) {
        this.societyRepository = societyRepository;
        this.societyMapper = societyMapper;
    }

    public SocietyDto getSocietyDetails(){
        Society response =  societyRepository.findById(CurrentUser.getSocietyId())
                .orElseThrow(() -> new CustomException("Society not found with id: " + CurrentUser.getSocietyId()));

        return societyMapper.toSocietyDto(response);
    }
}
