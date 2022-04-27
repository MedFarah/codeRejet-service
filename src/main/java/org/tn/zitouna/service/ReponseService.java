package org.tn.zitouna.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tn.zitouna.dao.RapportOperationDeviseRepository;
import org.tn.zitouna.dao.RapportPMRepository;
import org.tn.zitouna.dao.RapportPPRepository;
import org.tn.zitouna.entities.*;

@Service
public class ReponseService  {
	private RapportPPRepository rr;
	private RapportOperationDeviseRepository rapportOperationDeviseRepository;
	private RapportPMRepository rapportPMRepository;
	private RapportPPRepository rapportPPRepository;
	private IGenerateRapportFromFile igenerateRapportFromFile;

	@Autowired
	public ReponseService(RapportPPRepository rr, RapportOperationDeviseRepository rapportOperationDeviseRepository,
			RapportPMRepository rapportPMRepository, RapportPPRepository rapportPPRepository,
			IGenerateRapportFromFile igenerateRapportFromFile) {
		this.rr = rr;
		this.rapportOperationDeviseRepository = rapportOperationDeviseRepository;
		this.rapportPMRepository = rapportPMRepository;
		this.rapportPPRepository = rapportPPRepository;
		this.igenerateRapportFromFile = igenerateRapportFromFile;
	}


	public List<RapportOperationDevise> getRapportsRejetsODFromFile() throws Exception {
		Entete e = new Entete();
		RapportOperationDevise rop = new RapportOperationDevise();
			Fin f = new Fin();
		List<CodeErreurRapport> CodeErreurRapports = new ArrayList<CodeErreurRapport>();
		int i = 0;
		int j=0;
		
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ASUS\\Downloads\\BZ\\resources\\PFE\\REJET\\CTAF_100005_67_20220214_110415.DAT"))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        // process the line.
		    	System.out.println(line);
		    	String cc=line.trim();
		    	
		    	
		    	if(i==0) {
		    		if(line.lastIndexOf("R")>0) {
		    			j=cc.lastIndexOf("R");
		    			
		    			CodeErreurRapports.add(new CodeErreurRapport(cc.substring(j+1).replaceAll(" ", "")));
		    		}
		    		e=igenerateRapportFromFile.addEnteteFromFile(line);
		    		i+=1;
		    	}
		    	else if(i==1) {
		    	
		    		rop = igenerateRapportFromFile.generateRapportOperationDeviseCorpsFromFile(line);
		    		rop.setEntete(e);
		    		if(line.lastIndexOf("R")>0) {
		    			j=cc.lastIndexOf("R");
		    			System.out.println("iciiiiiiiiiiiiiiii"+cc.substring(j+1).strip());
		    			CodeErreurRapports.add(new CodeErreurRapport(cc.substring(j+1).replaceAll(" ", "")));
		    		}
		    		i+=1;
		    	}else if(i==2) {
		    		f=igenerateRapportFromFile.addLigneFinFromFile(line);
		    		if(line.lastIndexOf("R")>0) {
		    			j=cc.lastIndexOf("R");
		    			CodeErreurRapports.add(new CodeErreurRapport(cc.substring(j+1).replaceAll(" ", "")));
		    		}
		    		rop.setFin(f);
		    		rop.setCodeErreurRapports(CodeErreurRapports);
		    		rapportOperationDeviseRepository.save(rop);
		    		CodeErreurRapports.clear();
		    		i=0;
		    	}
		    }
		    // line is not visible here.
		}
		return rapportOperationDeviseRepository.findAll();
	}

	
	public List<RapportPM> getRapportsRejetsPMFromFile(String typeRapport) throws Exception {
		Entete e = new Entete();
		RapportPM rpm = new RapportPM();
		
		Fin f = new Fin();
		List<CodeErreurRapport> CodeErreurRapports = new ArrayList<CodeErreurRapport>();
		int i = 0;
		int j=0;
		
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ASUS\\Downloads\\BZ\\resources\\PFE\\REJET\\CTAF_100005_67_20220214_110415.DAT"))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        // process the line.
		    	System.out.println(line);
		    	String cc=line.trim();
		    	
		    	
		    	if(i==0) {
		    		if(line.lastIndexOf("R")>0) {
		    			j=cc.lastIndexOf("R");
		    			CodeErreurRapports.add(new CodeErreurRapport(cc.substring(j+1).replaceAll(" ", "")));
		    		}
		    		e=igenerateRapportFromFile.addEnteteFromFile(line);
		    		i+=1;
		    	}
		    	else if(i==1) {
		    		//c=line.strip().trim();
		    	
		    		rpm = igenerateRapportFromFile.generateRapportPMCorpsFromFile(line);
		    		rpm.setEntete(e);
		    		if(line.lastIndexOf("R")>0) {
		    			j=cc.lastIndexOf("R");
		    			CodeErreurRapports.add(new CodeErreurRapport(cc.substring(j+1).replaceAll(" ", "")));
		    		}
		    		System.out.println("taille*****************"+line.length());
		    		i+=1;
		    	}else if(i==2) {
		    		f=igenerateRapportFromFile.addLigneFinFromFile(line);
		    		if(line.lastIndexOf("R")>0) {
		    			j=cc.lastIndexOf("R");
		    			CodeErreurRapports.add(new CodeErreurRapport(cc.substring(j+1).replaceAll(" ", "")));
		    		}
		    		rpm.setFin(f);
		    		rpm.setCodeErreurRapports(CodeErreurRapports);
		    		rapportPMRepository.save(rpm);
		    		i=0;
		    	}
		    }
		    // line is not visible here.
		}
		return rapportPMRepository.findAll();
	}


	public List<RapportPP> getRapportsRejetsPPFromFile(String typeRapport) throws Exception {
		Entete e = new Entete();
		RapportPP rpp = new RapportPP();
		
		Fin f = new Fin();
		List<CodeErreurRapport> CodeErreurRapports = new ArrayList<CodeErreurRapport>();
		int i = 0;
		int j=0;
		
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ASUS\\Downloads\\BZ\\resources\\PFE\\REJET\\CTAF_100005_67_20220214_110415.DAT"))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        // process the line.
		    	System.out.println(line);
		    	String cc=line.trim();
		    	
		    	
		    	if(i==0) {
		    		if(line.lastIndexOf("R")>0) {
		    			j=cc.lastIndexOf("R");
		    			CodeErreurRapports.add(new CodeErreurRapport(cc.substring(j+1).replaceAll(" ", "")));
		    		}
		    		e=igenerateRapportFromFile.addEnteteFromFile(line);
		    		i+=1;
		    	}
		    	else if(i==1) {
		    		//c=line.strip().trim();
		    	
		    		rpp = igenerateRapportFromFile.generateRapportPPCorpsFromFile(line);
		    		rpp.setEntete(e);
		    		if(line.lastIndexOf("R")>0) {
		    			j=cc.lastIndexOf("R");
		    			CodeErreurRapports.add(new CodeErreurRapport(cc.substring(j+1).replaceAll(" ", "")));
		    		}
		    		System.out.println("taille*****************"+line.length());
		    		i+=1;
		    	}else if(i==2) {
		    		f=igenerateRapportFromFile.addLigneFinFromFile(line);
		    		if(line.lastIndexOf("R")>0) {
		    			j=cc.lastIndexOf("R");
		    			CodeErreurRapports.add(new CodeErreurRapport(cc.substring(j+1).replaceAll(" ", "")));
		    		}
		    		rpp.setFin(f);
		    		rpp.setCodeErreurRapports(CodeErreurRapports);
		    		rapportPPRepository.save(rpp);
		    		i=0;
		    	}
		    }
		    // line is not visible here.
		}
		return rapportPPRepository.findAll();
	}
	
	public RapportPP addRapportTest() {
		Entete e = new Entete();
		e.setNDECL(1);
		Fin f = new Fin();
		f.setNDECL(1);
		RapportPP rp = new RapportPP();
		rp.setNumeroDeclaration(1L);
		rp.setAdresseEmail("test@zitouna");
		rp.setEntete(e);
		rp.setFin(f);
		return rr.save(rp);
	}
}
