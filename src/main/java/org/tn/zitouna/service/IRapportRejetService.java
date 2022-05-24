package org.tn.zitouna.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.tn.zitouna.entities.*;
@Repository
public interface IRapportRejetService {
	public List<RapportPP> getRapportsPPRejets() ;
	public List<RapportPM> getRapportPMRejets();
	public List<RapportOperationDevise> getRapportOperationDeviseRejets();
	public RapportOperationDevise getRapportOperationDeviseRejetsById(Long id);
}
