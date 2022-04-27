package org.tn.zitouna.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tn.zitouna.dao.CodeErreurBCTRepository;
import org.tn.zitouna.entities.CodeErreurBCT;

@Service
public class CodeErreurBCTService implements ICodeErreurBCT {

	@Autowired
	private CodeErreurBCTRepository codeErreurBCTRepository;
	@Override
	public List<CodeErreurBCT> generateCodeErreurBCTFromFile() {
		// TODO Auto-generated method stub
		 CodeErreurBCT codeErreurBCT= new CodeErreurBCT();
		
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ASUS\\Downloads\\BZ\\resources\\zitounaDocuments\\Codes Erreur BCT 105-110-910.txt"))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        // process the line.
		    	codeErreurBCT.setCodeErreur(line.substring(0, 10).replaceAll(" ", ""));
		    	codeErreurBCT.setDescription(line.substring(10).replaceAll("   ", "").strip());
		    	codeErreurBCTRepository.save(codeErreurBCT);
		    }
		    // line is not visible here.
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Import code erreurs from external file...");
			System.out.println("erreur message : "+e.getMessage());
		} 
		return codeErreurBCTRepository.findAll();
	}

}
