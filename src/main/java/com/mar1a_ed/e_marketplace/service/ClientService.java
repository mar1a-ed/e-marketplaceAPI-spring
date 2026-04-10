package com.mar1a_ed.e_marketplace.service;

import com.mar1a_ed.e_marketplace.exception.CPFUniqueViolationException;
import com.mar1a_ed.e_marketplace.model.entity.Client;
import com.mar1a_ed.e_marketplace.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client create(Client client){
        try{
            return clientRepository.save(client);
        }catch (DataIntegrityViolationException e){
            throw new CPFUniqueViolationException("CPF already register.");
        }
    }
}
