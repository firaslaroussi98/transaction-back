 package com.bns.app.drools.controller;


 import com.bns.app.drools.exception.ResourceNoFoundException;
 import com.bns.app.drools.repository.RulesRepository;
 import com.bns.app.drools.rules.Rules;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.io.IOException;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;


 @CrossOrigin(origins = "*")
 @RestController
 @RequestMapping("/drools")
 public class RulesController {
     @Autowired
     private RulesRepository ruleRepository;


     // get all rules
     @GetMapping("/rules")
     public List<Rules> getAllRules(){

         return ruleRepository.findAll();
     }

     // create rule rest api
     @PostMapping("/rules")
     public Rules createRule(@RequestBody Rules rule) {
         try {
             rule.add_rules(rule );
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         return ruleRepository.save(rule);
     }

     // get rule by id rest api
     @GetMapping("/rules/{id}")
     public ResponseEntity<Rules> getRuleById(@PathVariable Long id) {
         Rules rule = ruleRepository.findById(id)
                 .orElseThrow(() -> new ResourceNoFoundException("Rule not exist with id :" + id));
         return ResponseEntity.ok(rule);
     }

     // update rule rest api

     @PutMapping("/rules/{id}")
     public ResponseEntity<Rules> updateRule(@PathVariable Long id, @RequestBody Rules ruleDetails){
         Rules rule = ruleRepository.findById(id)
                 .orElseThrow(() -> new ResourceNoFoundException("Rule not exist with id :" + id));
                 try {
                     rule.del_rules(rule.getRuleName()) ;
                 } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
         rule.setRuleName(ruleDetails.getRuleName());
         rule.setRuleCondition(ruleDetails.getRuleCondition());
         rule.setRuleAction(ruleDetails.getRuleAction());
         Rules updatedRule = ruleRepository.save(rule);
         try {
             rule.add_rules(rule );
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }

         return ResponseEntity.ok(updatedRule);
     }

     // delete rule rest api
     @DeleteMapping("/rules/{id}")
     public ResponseEntity<Map<String, Boolean>> deleteRule(@PathVariable Long id){
         Rules rule = ruleRepository.findById(id)
                 .orElseThrow(() -> new ResourceNoFoundException("Rule not exist with id :" + id));

         ruleRepository.delete(rule);
         Map<String, Boolean> response = new HashMap<>();
         response.put("deleted", Boolean.TRUE);
         try {
             rule.del_rules(rule.getRuleName()) ;
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         return ResponseEntity.ok(response);
     }
 }
