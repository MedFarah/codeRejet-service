package org.tn.zitouna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
public class StatistiqueDTO {
	private Long nbrRejetOD;
	private Long nbrRejetPP;
	private Long nbrRejetPM;

}
