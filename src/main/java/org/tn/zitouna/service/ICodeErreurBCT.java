package org.tn.zitouna.service;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.tn.zitouna.entities.*;
@Repository
public interface ICodeErreurBCT {
	public List<CodeErreurBCT> generateCodeErreurBCTFromFile(MultipartFile file) throws Exception;
	public List<CodeErreurBCT> getallCodeErreursBCT();
	public CodeErreurBCT getCodeErreurBCTByID(String codeErreur);
	public CodeErreurBCT ajouterCodeErreur(CodeErreurBCT codeErreurBCT);
	public CodeErreurBCT modifierCodeErreur(CodeErreurBCT codeErreurBCT);
	public void supprimerCodeErreur(String codeErreur);

}
