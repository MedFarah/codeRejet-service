package org.tn.zitouna.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tn.zitouna.dao.CodeErreurBCTRepository;
import org.tn.zitouna.entities.CodeErreurBCT;

@Service
public class CodeErreurBCTService implements ICodeErreurBCT {

	@Autowired
	private CodeErreurBCTRepository codeErreurBCTRepository;
	private final Path root = Paths.get("uploads");
	
	@Override
	public List<CodeErreurBCT> generateCodeErreurBCTFromFile(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		 CodeErreurBCT codeErreurBCT= new CodeErreurBCT();
		
		 try {
				Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			} catch (Exception ee) {
				System.out.println("Could not store the file. Error: " + ee.getMessage());
			}
		 
		try(BufferedReader br = new BufferedReader(
				new FileReader(root.toAbsolutePath().toString() + "\\" + file.getOriginalFilename()))) {
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
	@Override
	public List<CodeErreurBCT> getallCodeErreursBCT() {
		// TODO Auto-generated method stub
		return codeErreurBCTRepository.findAll();
	}
	@Override
	public CodeErreurBCT getCodeErreurBCTByID(String codeErreur) {
		// TODO Auto-generated method stub
		return codeErreurBCTRepository.findById(codeErreur).get();
	}
	@Override
	public CodeErreurBCT ajouterCodeErreur(CodeErreurBCT codeErreurBCT) {
		// TODO Auto-generated method stub
		return codeErreurBCTRepository.insert(codeErreurBCT);
	}
	@Override
	public CodeErreurBCT modifierCodeErreur(CodeErreurBCT codeErreurBCT) {
		// TODO Auto-generated method stub
		return codeErreurBCTRepository.save(codeErreurBCT);
	}
	@Override
	public void supprimerCodeErreur(String codeErreur) {
		// TODO Auto-generated method stub
		codeErreurBCTRepository.deleteById(codeErreur);
	}

}
