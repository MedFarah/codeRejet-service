package org.tn.zitouna.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
public class Entete {

	private int NDECL;
	private int KTENR;
	private int KTE;
	private Date DDECL;
	private int KMDECL;
	private Date DEMISS;
	@Field(name = "R")
	private List<CodeErreurRapport> codeErreurRapports;
}
