package org.tn.zitouna.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
@Document
public class ArchiveRapportPP {

	@Id
	private String id;
	private Date dateArchive;
	private Entete entete;
	private Long numeroDeclaration;
	private  int codeEnregistrement = 105;
	private String codeOperation = "N";
	private String typeDocument;
	private String numeroDocumentIdentification;

	private Date dateDebutDocumentIdentification;
	private String lieuDelivranceDocumentIdentification;

	private Date dateFinValiditeDocumentIdentification;

	private String nomPersonPhysique;
	private String prenomPersonnePhysique;
	private String sexePersonnePhysique;
	private Date dateNaissance;
	private String lieuNaissance;
	private String qualitePersonne;
	private String nationalite;
	private String profession;
	private String etatCivil;
	private int situationJuridique;
	private Date dateSituationJuridique;
	private String numeroTelephone1;
	private String numeroTelephone2;
	private String numeroFax;
	private String numeroTelex;
	private String adresseEmail;
	private Fin fin;
	@Field(name = "R")
	private List<CodeErreurRapport> CodeErreurRapports;
}
