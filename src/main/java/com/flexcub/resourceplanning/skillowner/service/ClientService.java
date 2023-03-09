package com.flexcub.resourceplanning.skillowner.service;

import com.flexcub.resourceplanning.skillowner.dto.Client;
import com.flexcub.resourceplanning.skillowner.dto.ClientDetails;
import com.flexcub.resourceplanning.skillowner.entity.ClientEntity;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    ClientDetails getClient(int ownerId);

    Client insertClient(Client client);

    ClientEntity updateClient(ClientEntity clientEntity);

    void deleteClient(int id);
}
