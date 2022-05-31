package com.bns.app.drools.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class FDRequest {

    private Float montant;
    private Integer commition;
    private Float resultat;


    public FDRequest(Float montant) {
        this.montant = montant;

    }


    public FDRequest() {

    }

    public static void Commition() {

        FDRequest fdRequest = new FDRequest();
        fdRequest.resultat = (fdRequest.getMontant() * fdRequest.getCommition()) / 100;


    }

}



