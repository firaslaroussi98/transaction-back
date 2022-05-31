package com.bns.app.drools.domain;

import lombok.Data;

@Data
public class LimitationRequest {

    private float soldeBancaire;
    private float montantEmmetteur;
    private boolean accepted;
    private String message;

    public LimitationRequest(float soldeBancaire, float montantEmmetteur) {
        this.soldeBancaire = soldeBancaire;
        this.montantEmmetteur = montantEmmetteur;
    }
}
