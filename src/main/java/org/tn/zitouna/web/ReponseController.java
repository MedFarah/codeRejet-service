package org.tn.zitouna.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tn.zitouna.dao.CodeErreurBCTRepository;
import org.tn.zitouna.entities.*;
import org.tn.zitouna.service.CodeErreurBCTService;
import org.tn.zitouna.service.RapportRejetService;
import org.tn.zitouna.service.ReponseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rejets")
@CrossOrigin("*")
public class ReponseController {

	private ReponseService reponseService;
	private	RapportRejetService rapportRejetService;
	private CodeErreurBCTService codeErreurBCTService;
	private CodeErreurBCTRepository codeErreurBCTRepository;
	@Autowired
	public ReponseController(ReponseService reponseService, RapportRejetService rapportRejetService,CodeErreurBCTService codeErreurBCTService,CodeErreurBCTRepository codeErreurBCTRepository) {
		this.reponseService = reponseService;
		this.rapportRejetService = rapportRejetService;
		this.codeErreurBCTService = codeErreurBCTService;
		this.codeErreurBCTRepository = codeErreurBCTRepository;
	}
	
	@GetMapping("/codeErreur")
	public List<CodeErreurBCT> addCodeErreur() throws Exception {
	
		return codeErreurBCTService.generateCodeErreurBCTFromFile();
	}
	
	@GetMapping("/a")
	public String test() {
		reponseService.deleteAll();
		return "";
	}
	
	/*@GetMapping("/generateRapportRejetsOD")
	public List<RapportOperationDevise> test() throws Exception {
	
		return reponseService.getRapportsRejetsODFromFile();
	}*/
	@PostMapping("/te")
	public List<RapportOperationDevise> ok(@RequestParam MultipartFile file){
		try {
			return reponseService.getRapportsRejetsODFromFile(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*@GetMapping("/re")
	public List<RapportOperationDevise> ttt() throws Exception {
	
		return reponseService.getRapportsRejetsODFromFile();
	}
	@GetMapping("/rr")
	public List<RapportOperationDevise> teet() throws Exception {
	
		return reponseService.getRapportsRejetsODFromFile();
	}
	*/
	@GetMapping("/rapportsOD")
	public List<RapportOperationDevise> getRapportsOperationDevise() {
	
		return rapportRejetService.getRapportOperationDeviseRejets();
	}
	

	@GetMapping("/rapportOD/{id}")
	public RapportOperationDevise getRapportOperationDeviseByID(@PathVariable Long id) {
	
		return rapportRejetService.getRapportOperationDeviseRejetsById(id);
	}
	
	@GetMapping("/assign")
	public CodeErreurBCT tdest() {
	//	rapportRejetService.assignDescriptionToError();
		return codeErreurBCTRepository.findById("ENT00113").get();
	}
	

	
}
