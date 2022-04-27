package org.tn.zitouna.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tn.zitouna.entities.*;
import org.tn.zitouna.service.CodeErreurBCTService;
import org.tn.zitouna.service.RapportRejetService;
import org.tn.zitouna.service.ReponseService;

@RestController
@RequestMapping("/rejets")
public class ReponseController {

	private ReponseService reponseService;
	private	RapportRejetService rapportRejetService;
	private CodeErreurBCTService codeErreurBCTService;
	@Autowired
	public ReponseController(ReponseService reponseService, RapportRejetService rapportRejetService,CodeErreurBCTService codeErreurBCTService) {
		this.reponseService = reponseService;
		this.rapportRejetService = rapportRejetService;
		this.codeErreurBCTService = codeErreurBCTService;
	}
	
	@GetMapping("/codeErreur")
	public List<CodeErreurBCT> addCodeErreur() throws Exception {
	
		return codeErreurBCTService.generateCodeErreurBCTFromFile();
	}
	
	@GetMapping("/generateRapportRejetsOD")
	public List<RapportOperationDevise> test() throws Exception {
	
		return reponseService.getRapportsRejetsODFromFile();
	}
	
	@GetMapping("/re")
	public List<RapportOperationDevise> ttt() throws Exception {
	
		return reponseService.getRapportsRejetsODFromFile();
	}
	@GetMapping("/rr")
	public List<RapportOperationDevise> teet() throws Exception {
	
		return reponseService.getRapportsRejetsODFromFile();
	}
	
	@GetMapping("/rapportsOD")
	public List<RapportOperationDevise> getRapportsOperationDevise() {
	
		return rapportRejetService.getRapportOperationDeviseRejets();
	}
	

	@GetMapping("/rapportOD/{id}")
	public RapportOperationDevise getRapportOperationDeviseByID(@PathVariable Long id) {
	
		return rapportRejetService.getRapportOperationDeviseRejetsById(id);
	}
	

	

	@GetMapping("/addrapporttest")
	public RapportPP addRapporttest() throws Exception {
	
		return reponseService.addRapportTest();
	}
}
