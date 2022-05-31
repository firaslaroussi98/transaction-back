package com.bns.app.client.Services;

import com.bns.app.client.DTO.ClientDto;
import com.bns.app.client.Entities.Client;

import java.util.List;

public interface IClientService {
    List<ClientDto> retrieveAllClients();
    public Client getClientByCIN(int ClientCIN);
    public Client getClientByPasseport(String ClientPasseport);
    public ClientDto ajouterClient(ClientDto clientdto);
    public ClientDto mettreAjourClient(ClientDto clientdto);
    public String deleteClientByCIN(int ClientCIN);
    public String deleteClientByPasseport(String ClientPasseport);
    public void checkAlreadyUsedCin(ClientDto clientdto);
}
