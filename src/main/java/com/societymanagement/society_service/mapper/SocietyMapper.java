package com.societymanagement.society_service.mapper;

import com.societymanagement.society_service.dtos.SocietyDto;
import com.societymanagement.society_service.entity.Society;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocietyMapper {

    SocietyDto toSocietyDto(Society society);

}
