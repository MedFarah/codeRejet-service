package org.tn.zitouna.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document @Data @NoArgsConstructor @AllArgsConstructor
public class CodeErreurBCT {
	@Id
	private String codeErreur;
	private String description;

}
