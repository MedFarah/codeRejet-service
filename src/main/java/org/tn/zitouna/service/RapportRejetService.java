package org.tn.zitouna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tn.zitouna.dao.CodeErreurBCTRepository;
import org.tn.zitouna.dao.RapportOperationDeviseRepository;
import org.tn.zitouna.dao.RapportPMRepository;
import org.tn.zitouna.dao.RapportPPRepository;
import org.tn.zitouna.entities.CodeErreurBCT;
import org.tn.zitouna.entities.RapportOperationDevise;
import org.tn.zitouna.entities.RapportPM;
import org.tn.zitouna.entities.RapportPP;

@Service
public class RapportRejetService implements IRapportRejetService{
	private RapportOperationDeviseRepository rapportOperationDeviseRepository;
	private RapportPPRepository rapportPPRepository;
	private RapportPMRepository rapportPMRepository;
	private CodeErreurBCTRepository codeErreurBCTRepository;
	
	@Autowired
	public RapportRejetService(RapportOperationDeviseRepository rapportOperationDeviseRepository,
			RapportPPRepository rapportPPRepository, RapportPMRepository rapportPMRepository, CodeErreurBCTRepository codeErreurBCTRepository) {
		this.rapportOperationDeviseRepository = rapportOperationDeviseRepository;
		this.rapportPPRepository = rapportPPRepository;
		this.rapportPMRepository = rapportPMRepository;
		this.codeErreurBCTRepository = codeErreurBCTRepository;
	}

	@Override
	public List<RapportPP> getRapportsPPRejets() {
		// TODO Auto-generated method stub
		return rapportPPRepository.findAll();
	}

	@Override
	public List<RapportPM> getRapportPMRejets() {
		// TODO Auto-generated method stub
		return rapportPMRepository.findAll();
	}

	@Override
	public List<RapportOperationDevise> getRapportOperationDeviseRejets() {
		// TODO Auto-generated method stub
		return rapportOperationDeviseRepository.findAll();
	}

	@Override
	public RapportOperationDevise getRapportOperationDeviseRejetsById(Long id) {
		// TODO Auto-generated method stub
		return rapportOperationDeviseRepository.findById(id).get();
	}
	
	
}
