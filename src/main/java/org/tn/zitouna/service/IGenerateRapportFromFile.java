package org.tn.zitouna.service;

import java.text.ParseException;

import org.springframework.stereotype.Repository;
import org.tn.zitouna.entities.Entete;
import org.tn.zitouna.entities.Fin;
import org.tn.zitouna.entities.RapportOperationDevise;
import org.tn.zitouna.entities.RapportPM;
import org.tn.zitouna.entities.RapportPP;
@Repository
public interface IGenerateRapportFromFile {
	public RapportPP generateRapportPPCorpsFromFile(String line) throws ParseException;
	public RapportPM generateRapportPMCorpsFromFile(String line) throws Exception;
	public RapportOperationDevise generateRapportOperationDeviseCorpsFromFile(String line) throws ParseException;
	public Entete addEnteteFromFile(String line) throws ParseException;
	public Fin addLigneFinFromFile(String line);

}
