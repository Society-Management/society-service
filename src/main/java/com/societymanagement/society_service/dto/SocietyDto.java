package com.societymanagement.society_service.dto;

import com.societymanagement.society_service.entity.Blocks;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class SocietyDto {

    private Long id;
    private String societyName;
    private String societyAddress;
    private String societyContactNumber;
    private String societyEmail;
    private List<Blocks> blocks;
}
