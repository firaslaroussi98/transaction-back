package com.bns.app.emetteur.Services;


import com.bns.app.emetteur.DTO.EmetteurDto;
import com.bns.app.emetteur.Entities.Emetteur;

import java.util.List;

public interface IEmetteurService {

    List<EmetteurDto> retrieveAllEmetteurs();
    public Emetteur getEmetteurByIdEmetteur(String IdEmetteur);
    public Emetteur getEmetteurByPv(String EmetteurPv);
    public EmetteurDto ajouterEmetteur(EmetteurDto emetteurdto);
    public EmetteurDto mettreAjourEmetteur(EmetteurDto emetteurdto);
    public String deleteEmetteurByIdEmetteur(String IdEmetteur);
    public String deleteEmetteurByPv(String EmetteurPv);
    public void checkAlreadyUsedIdEmetteur(EmetteurDto emetteurdto);

}
