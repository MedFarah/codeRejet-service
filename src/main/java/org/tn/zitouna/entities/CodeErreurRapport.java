package org.tn.zitouna.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CodeErreurRapport {
 private String codeErreur;
 private String motif;
public CodeErreurRapport(String codeErreur) {
	this.codeErreur = codeErreur;
}
 
}
