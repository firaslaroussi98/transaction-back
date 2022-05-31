package com.bns.app.drools.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rules")
public class Rules {

 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "rule_name",length = 65535, columnDefinition = "text")
	private String ruleName;

	@Column(name = "rule_condition",length = 65535, columnDefinition = "text")
	private String ruleCondition;
	
	@Column(name = "rule_action",length = 65535, columnDefinition = "text")
	private String ruleAction;



	public void add_rules(Rules rule) throws IOException {
		File outFile = new File("C:/Users/asus/Downloads/backend/drools-demo-master/src/main/resources/FDInterestRate.drl");
	   FileWriter fWriter = new FileWriter(outFile,true);
	   PrintWriter pWriter = new PrintWriter(fWriter);
	   pWriter.println(' ');
	   pWriter.println ("rule " +"\"" + ruleName + "\"" );

	   pWriter.println ("when");
	   pWriter.println (ruleCondition);
	   pWriter.println ("then");
	   pWriter.println (ruleAction);
	   pWriter.println ("end");
	   pWriter.close();
	   }
   public void del_rules(String ruleName) throws IOException {
	   
	   File outFile = new File("C:/Users/asus/Downloads/backend/drools-demo-master/src/main/resources/FDI.drl");
	   File ffile = new File("C:/Users/asus/Downloads/backend/drools-demo-master/src/main/resources/FDInterestRate.drl");
	   
	   FileWriter fWriter = new FileWriter(outFile );
	   PrintWriter pWriter = new PrintWriter(fWriter);
	  
		String file = "C:/Users/asus/Downloads/backend/drools-demo-master/src/main/resources/FDInterestRate.drl";
			   try(BufferedReader br = new BufferedReader(new FileReader(file)))
			   {
				   String line  ;
				   while ((line = br.readLine()) != null) {
						
					   if(line.indexOf(ruleName) >= 0)
						   {
						   while (line.indexOf("end") < 0) {line = br.readLine();}
						   }
					   else {
						   pWriter.println (line);
						}		
				   }
				   pWriter.close();
				   }
			   catch (IOException e) {
				   System.out.println("An error occurred.");
				   e.printStackTrace();
			   }
			   if(ffile.delete()){
				   System.out.println(ffile.getName() + " est supprimé.");
				  }else{
				   System.out.println("Opération de suppression echouée");
				  }
			   if(outFile.renameTo(ffile)){
				   System.out.println("Le fichier a été renommé avec succès");
				 }else{
				   System.out.println("Impossible de renommer le fichier");
				 }
			  
   }

}
