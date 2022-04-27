package org.tn.zitouna.entities;

import java.util.Date;

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
}
