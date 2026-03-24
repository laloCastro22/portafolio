package com.practica.java_api_portfolio.api.mapper;

import com.practica.java_api_portfolio.api.dto.UserResponse;
import com.practica.java_api_portfolio.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserApiMapper {

    @Mapping(target = "id", expression = "java(usuario.getId().value())")
    @Mapping(target = "username", expression = "java(usuario.getUsername().value())")
    @Mapping(target = "status", expression = "java(usuario.getStatus().toString())")
    UserResponse toResponse(Usuario usuario);
}
