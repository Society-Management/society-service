package com.societymanagement.society_service.Mapper;

import com.societymanagement.society_service.dto.SocietyDto;
import com.societymanagement.society_service.entity.Society;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocietyMapper {

    SocietyDto toSocietyDto(Society society);

}
