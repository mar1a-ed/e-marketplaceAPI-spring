package com.mar1a_ed.e_marketplace.controller;

import com.mar1a_ed.e_marketplace.dto.client.ClientCreateDto;
import com.mar1a_ed.e_marketplace.dto.client.ClientMapper;
import com.mar1a_ed.e_marketplace.dto.client.ClientResponseDto;
import com.mar1a_ed.e_marketplace.model.entity.Client;
import com.mar1a_ed.e_marketplace.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/users/{userId}")
    public ResponseEntity<ClientResponseDto> create(@Valid @PathVariable Long userId, @RequestBody ClientCreateDto clientCreateDto){
        Client client = ClientMapper.toClient(clientCreateDto);

        clientService.create(userId, client);

        return ResponseEntity.status(HttpStatus.CREATED).body(ClientMapper.toDto(client));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> findById(@Valid @PathVariable Long clientId){
        Client client = clientService.findById(clientId);

        return ResponseEntity.ok(ClientMapper.toDto(client));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientResponseDto>> findAll(){
        List<Client> clients = clientService.findAll();

        return ResponseEntity.ok(ClientMapper.toListDto(clients));
    }
}
