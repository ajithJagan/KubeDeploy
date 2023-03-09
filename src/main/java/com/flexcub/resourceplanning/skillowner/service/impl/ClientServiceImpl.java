package com.flexcub.resourceplanning.skillowner.service.impl;

import com.flexcub.resourceplanning.exceptions.ServiceException;
import com.flexcub.resourceplanning.skillowner.dto.Client;
import com.flexcub.resourceplanning.skillowner.dto.ClientDetails;
import com.flexcub.resourceplanning.skillowner.entity.ClientEntity;
import com.flexcub.resourceplanning.skillowner.entity.SkillOwnerEntity;
import com.flexcub.resourceplanning.skillowner.repository.ClientRepository;
import com.flexcub.resourceplanning.skillowner.repository.SkillOwnerRepository;
import com.flexcub.resourceplanning.skillowner.service.ClientService;
import com.flexcub.resourceplanning.utils.NullPropertyName;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.flexcub.resourceplanning.utils.FlexcubErrorCodes.*;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SkillOwnerRepository skillOwnerRepository;
    @Autowired
    ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Transactional
    @Override
    public ClientDetails getClient(int ownerId) {
        try {
            List<ClientEntity> bySkillOwnerEntityId = clientRepository.findBySkillOwnerEntityId(ownerId);

            ClientDetails clientDetails2 = new ClientDetails();
            if (!bySkillOwnerEntityId.isEmpty()) {
                for (ClientEntity clientEntity1 : bySkillOwnerEntityId) {
                    modelMapper.map(bySkillOwnerEntityId, Client.class);
                    BeanUtils.copyProperties(bySkillOwnerEntityId, clientEntity1, NullPropertyName.getNullPropertyNames(bySkillOwnerEntityId));
                    clientDetails2.setClient(bySkillOwnerEntityId);
                    clientDetails2.setSkillOwnerEntityId(clientEntity1.getSkillOwnerEntityId());
                }
                logger.info("ClientServiceImpl || getClient || Get all clients from the ClientEntity");
                return modelMapper.map(clientDetails2, ClientDetails.class);

            } else {
                throw new ServiceException(INVALID_OWNER_ID.getErrorCode(), INVALID_OWNER_ID.getErrorDesc());
            }
        } catch (NullPointerException e) {
            throw new ServiceException(INVALID_REQUEST.getErrorCode(), INVALID_REQUEST.getErrorDesc());
        }

    }

    @Override
    public Client insertClient(Client client) {

        ClientEntity clientEntity = new ClientEntity();
        Optional<SkillOwnerEntity> bySkillOwnerEntityId = skillOwnerRepository.findById(client.getSkillOwnerEntityId());
        try {
            if (bySkillOwnerEntityId.isPresent()) {
                clientEntity.setClientId(client.getClientId());
                clientEntity.setDepartment(client.getDepartment());
                clientEntity.setSkillOwnerEntityId(client.getSkillOwnerEntityId());
                clientEntity.setEmployerName(client.getEmployerName());
                clientEntity.setJobTitle(client.getJobTitle());
                clientEntity.setProjectDescription(client.getProjectDescription());
                clientEntity.setStartDate(client.getStartDate());
                clientEntity.setEndDate(client.getEndDate());
                clientEntity.setLocation(client.getLocation());
                clientEntity.setProject(client.getProject());
                clientEntity.setCurrentEmployer(client.getCurrentEmployer());
                clientRepository.save(clientEntity);
                logger.info("ClientServiceImpl || insertClient || Client detail was inserted=={}", client);
                return modelMapper.map(clientEntity, Client.class);
            } else {
                throw new ServiceException(INVALID_OWNER_ID.getErrorCode(), INVALID_OWNER_ID.getErrorDesc());
            }
        } catch (NullPointerException e) {
            throw new ServiceException(INVALID_REQUEST.getErrorCode(), INVALID_REQUEST.getErrorDesc());
//        } catch (Exception e) {
//            throw new ServiceException(DATA_NOT_FOUND.getErrorCode(), "Invalid Input or Null");
        }
    }


    @Override
    public ClientEntity updateClient(ClientEntity clientEntity) {
        logger.info("ClientServiceImpl || updateClient || client detail was updated=={}", clientEntity);
        try {
            Optional<ClientEntity> client = clientRepository.findById(clientEntity.getClientId());
            if (client.isPresent()) {
                int skillOwnerId = client.get().getSkillOwnerEntityId();
                BeanUtils.copyProperties(clientEntity, client.get(), NullPropertyName.getNullPropertyNames(clientEntity));
                client.get().setSkillOwnerEntityId(skillOwnerId);
                return clientRepository.save(client.get());
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            throw new ServiceException(INVALID_REQUEST.getErrorCode(), INVALID_REQUEST.getErrorDesc());
        } catch (Exception e) {
            throw new ServiceException(DATA_NOT_FOUND.getErrorCode(), "Invalid Input or Null");
        }
    }

    @Override
    public void deleteClient(int clientId) {
        logger.info("ClientServiceImpl || deleteClient || Client detail was deleted by particular ClientId=={}", clientId);
        try {
            clientRepository.deleteById(clientId);
        } catch (NullPointerException e) {
            throw new ServiceException(INVALID_REQUEST.getErrorCode(), INVALID_REQUEST.getErrorDesc());
        } catch (Exception e) {
            throw new ServiceException(INVALID_REQUEST.getErrorCode(), INVALID_REQUEST.getErrorDesc());
        }
    }
}
