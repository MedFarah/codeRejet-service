package org.tn.zitouna.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
@Document(collection = "rapport_operation_devise")
public class RapportOperationDevise {
	private Entete entete;
	@NonNull @Id
	private Long numeroDeclaration;
	private  int codeEnregistrement = 910;
	private String codeOperation ;
	@Field(name = "TYPDOC")
	private String typeIdentifiantClient;
	private String numIdentifiantClient;
	private int codeAgenceDeclarant;
	@Field(name = "NINTOPER")
	private String identifiantInterneOperation;
	private Date dateOperation;
	private String codeTypeOperation;
	private String codeDevise;
	private float montantEnDevise;
	private float montantEnDinar;
	private String NumDeclarationDouane;
	private Date dateDeclarationDouane;
	private float montantDeclareEnDouane;
	private String typeIdentifiantBeneficiairePP;
	private int numIdentifiantBeneficiairePP;
	private String typeIdentifiantBeneficiairePM;
	private int numIdentifiantBeneficiairePM;
	@Field(name = "KRIB")
	private String ribBeneficiaire;
	@Field(name = "KTYPCPT")
	private String typeCompteBeneficiaire;
	@Field(name = "KPAYS")
	private String codePaysExpediteurDesFonds;
	
	private Fin fin;
	@Field(name = "R")
	private List<CodeErreurRapport> codeErreurRapports;
}
