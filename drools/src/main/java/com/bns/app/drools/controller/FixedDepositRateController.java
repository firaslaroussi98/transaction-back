package com.bns.app.drools.controller;

import com.bns.app.drools.domain.FDRequest;
import com.bns.app.drools.domain.LimitationRequest;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/drools")
public class FixedDepositRateController {
	
	
    private final KieContainer kieContainer;

    public FixedDepositRateController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @RequestMapping(value = "/getFDInterestRate", method = RequestMethod.GET, produces = "application/json")
    public FDRequest getQuestions(@RequestParam(required = true) float montant ){
        KieSession kieSession = kieContainer.newKieSession();
        FDRequest fdRequest = new FDRequest(montant);
        kieSession.insert(fdRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return fdRequest;
    }
    @RequestMapping(value = "getFDInterestRate/{montant}", method = RequestMethod.GET, produces = "application/json")
    public FDRequest getQuestions2( @PathVariable Float montant) {
        KieSession kieSession = kieContainer.newKieSession();
        FDRequest fdRequest = new FDRequest(montant);
        kieSession.insert(fdRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return fdRequest;
    }

    @RequestMapping(value = "getLimitation/{soldeBancaire}/{montantEmmetteur}", method = RequestMethod.GET, produces = "application/json")
    public LimitationRequest getQuestions3(@PathVariable float soldeBancaire, @PathVariable float montantEmmetteur) {
        KieSession kieSession = kieContainer.newKieSession();
        LimitationRequest limitationRequest = new LimitationRequest(soldeBancaire, montantEmmetteur);
        kieSession.insert(limitationRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return limitationRequest;
    }
  
    
}
