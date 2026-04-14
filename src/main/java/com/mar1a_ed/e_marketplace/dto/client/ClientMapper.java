package com.mar1a_ed.e_marketplace.dto.client;

import com.mar1a_ed.e_marketplace.model.entity.Client;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    public static Client toClient(ClientCreateDto clientCreateDto){
        return new ModelMapper().map(clientCreateDto, Client.class);
    }

    public static ClientResponseDto toDto(Client client){
        return new ModelMapper().map(client, ClientResponseDto.class);
    }

    public static List<ClientResponseDto> toListDto(List<Client> clients){
        return clients.stream().map(client -> toDto(client)).collect(Collectors.toList());
    }
}
