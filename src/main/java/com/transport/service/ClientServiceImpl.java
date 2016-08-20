package com.transport.service;

import com.transport.model.Client;
import com.transport.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by maksim on 8/20/2016.
 */
@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public String getClientNameByPhone(String phone) {
        Client client = clientRepository.findByPhone(phone);
        return client == null ? "" : client.getFullName();
    }
}
