package com.mar1a_ed.e_marketplace.service;

import com.mar1a_ed.e_marketplace.exception.ClientNotFoundException;
import com.mar1a_ed.e_marketplace.exception.NoRegisteredClientsException;
import com.mar1a_ed.e_marketplace.exception.UserNotFoundException;
import com.mar1a_ed.e_marketplace.model.entity.Client;
import com.mar1a_ed.e_marketplace.model.entity.User;
import com.mar1a_ed.e_marketplace.repository.ClientRepository;
import com.mar1a_ed.e_marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Transactional
    public Client create(Long userId, Client client){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("User {id=%s} not found.",userId))
        );

        client.setUser(user);

        clientRepository.save(client);

        return client;
    }

    @Transactional(readOnly = true)
    public Client findById(Long clientId){
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ClientNotFoundException(String.format("Client {id=%s} not found.",clientId))
        );

        return client;
    }

    @Transactional(readOnly = true)
    public List<Client> findAll(){
        try{
            List<Client> clients = clientRepository.findAll();
            return clients;
        }catch (Exception e){
            throw new NoRegisteredClientsException("No Registered Clients.");
        }
    }
}
