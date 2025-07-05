package com.societymanagement.society_service.dtos;

import com.societymanagement.society_service.entity.Blocks;

import java.util.List;

public class SocietyDto {

    private Long id;
    private String societyName;
    private String societyAddress;
    private String societyContactNumber;
    private String societyEmail;
    private List<Blocks> blocks;
}
