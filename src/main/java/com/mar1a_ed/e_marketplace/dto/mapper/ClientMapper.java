package com.mar1a_ed.e_marketplace.dto.mapper;

import com.mar1a_ed.e_marketplace.dto.clients.ClientCreateDto;
import com.mar1a_ed.e_marketplace.dto.clients.ClientResponseDto;
import com.mar1a_ed.e_marketplace.model.entity.Client;
import org.modelmapper.ModelMapper;

public class ClientMapper {

    public static Client toClient(ClientCreateDto createDto){
        return new ModelMapper().map(createDto, Client.class);
    }

    public static ClientResponseDto toDto(Client client){
        return new ModelMapper().map(client, ClientResponseDto.class);
    }
}
