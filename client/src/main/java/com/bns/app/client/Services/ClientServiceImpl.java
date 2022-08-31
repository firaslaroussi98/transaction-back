package com.bns.app.client.Services;

import com.bns.app.client.Exception.AlreadyExistingClient;
import com.bns.app.client.DTO.ClientDto;
import com.bns.app.client.Entities.Client;
import com.bns.app.client.Mapper.ClientMapper;
import com.bns.app.client.Repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    ClientRepository clientrepo;
    @Autowired
    ClientMapper clientmapper;


    @Override
    public List<ClientDto> retrieveAllClients() {
        return clientrepo.findAll().stream()
                .filter(c -> c.getId() > 3)
                .limit(5)
                .map(clientmapper::toDto)
              //  .sorted(Comparator.comparing(ClientDto::getNomDto))

                .collect(toList());
    }

    @Override
    public Client getClientByCIN(int ClientCIN) {

        return clientrepo.findByCin(ClientCIN);
    }

    @Override
    public Client getClientByPasseport(String ClientPasseport) {

        return clientrepo.findByPasseport(ClientPasseport);
    }

    @Override
    public ClientDto ajouterClient(ClientDto clientdto) {
        log.info("debut ajout");

        checkAlreadyUsedCin(clientdto);

            Client client = clientrepo.save(Client.builder()
                    .cin(clientdto.getCinDto())
                    .matriculeFs(clientdto.getMatriculeFsDto())
                    .nom(clientdto.getNomDto())
                    .passeport(clientdto.getPasseportDto())
                    .prenom(clientdto.getPrenomDto())
                    .rne(clientdto.getRneDto())
                    .build());

            log.info("l'ajout est realisé avec succés, fin methode ajouterClient");
            return clientmapper.toDto(client);


    }

    @Override
    public ClientDto mettreAjourClient(ClientDto clientdto) {

        log.info("debut modification");


        Client client = clientrepo.save(Client.builder()
                .cin(clientdto.getCinDto())
                .matriculeFs(clientdto.getMatriculeFsDto())
                .nom(clientdto.getNomDto())
                .passeport(clientdto.getPasseportDto())
                .prenom(clientdto.getPrenomDto())
                .rne(clientdto.getRneDto())
                .build());

        log.info("la modification est realisé avec succés, fin methode mettreAjourClient");
        return clientmapper.toDto(client);
    }

    @Override
    public ClientDto updateClient(ClientDto clientdto) {

        Client existingCLT = clientrepo.findById(clientdto.getIdDto()).orElse(null);
        System.out.println(clientdto);
        if(existingCLT == null) {
            System.out.println("client not found");
            Client client = clientrepo.save(Client.builder()
                    .cin(clientdto.getCinDto())
                    .matriculeFs(clientdto.getMatriculeFsDto())
                    .nom(clientdto.getNomDto())
                    .passeport(clientdto.getPasseportDto())
                    .prenom(clientdto.getPrenomDto())
                    .rne(clientdto.getRneDto())
                    .build());

            log.info("la modification est realisé avec succés, fin methode mettreAjourClient");
            return clientmapper.toDto(client);
        }else  {
            existingCLT.setCin(clientdto.getCinDto());
            existingCLT.setNom(clientdto.getNomDto());
            existingCLT.setPrenom(clientdto.getPrenomDto());
            existingCLT.setPasseport(clientdto.getPasseportDto());
            existingCLT.setRne(clientdto.getRneDto());
            existingCLT.setMatriculeFs(clientdto.getMatriculeFsDto());
            clientrepo.save(existingCLT);
        }
        return clientdto;
    }


    @Override
    public String deleteClientByCIN(int ClientCIN) {
        String msg="";
        try {
            log.info("Finding Client with CIN = %d"+ClientCIN);
            clientrepo.deleteClientByCIN(ClientCIN);
            log.info("Client Deleted Successfuly ");
            msg="Delete Done";
        }catch (Exception e){
            log.error("The client with CIN = %d does not Exist"+ClientCIN);
            msg="error";
        }


        return msg;
    }

    @Override
    public String deleteClientByPasseport(String ClientPasseport) {

        String msg="";
        try {
            log.info("Finding Client with Passeport = %s"+ClientPasseport);
            clientrepo.deleteClientByPasseport(ClientPasseport);
            log.info("Client Deleted Successfuly ");
            msg="Delete Done";
        }catch (Exception e){
            log.error("The client with Passeport = %s does not Exist"+ClientPasseport);
            msg="error";
        }


        return msg;
    }

    @Override
    public boolean deleteClientByID(long idDto) {

        Client existingCLT = clientrepo.getById(idDto);

        if(existingCLT != null) {
            clientrepo.deleteById(idDto);
            return true;
        }
        return false;
    }



    @Override
    public void checkAlreadyUsedCin(ClientDto clientdto)  {
        clientrepo.findOneByCin(clientdto.getCinDto()).ifPresent(existingClient -> {
            try {
                throw new AlreadyExistingClient(clientdto.getCinDto());
            } catch (AlreadyExistingClient alreadyExistingClient) {
                alreadyExistingClient.printStackTrace();
            }

        });
    }

}
