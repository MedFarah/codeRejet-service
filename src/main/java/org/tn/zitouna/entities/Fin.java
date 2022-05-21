package org.tn.zitouna.entities;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Fin {

	private int NDECL;
	private int KTENR ;
	private int TOT_MVT;
	private int V_CPT2;
	private int V_CPT3;
	@Field(name = "R")
	private List<CodeErreurRapport> codeErreurRapports;
}
