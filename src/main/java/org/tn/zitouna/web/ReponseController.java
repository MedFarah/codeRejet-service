package org.tn.zitouna.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tn.zitouna.dto.StatistiqueDTO;
import org.tn.zitouna.entities.*;
import org.tn.zitouna.service.CodeErreurBCTService;
import org.tn.zitouna.service.RapportRejetService;
import org.tn.zitouna.service.ReponseService;


@RestController
@RequestMapping("/rejets")
public class ReponseController {

	private ReponseService reponseService;
	private RapportRejetService rapportRejetService;
	private CodeErreurBCTService codeErreurBCTService;

	@Autowired
	public ReponseController(ReponseService reponseService, RapportRejetService rapportRejetService,
			CodeErreurBCTService codeErreurBCTService) {
		this.reponseService = reponseService;
		this.rapportRejetService = rapportRejetService;
		this.codeErreurBCTService = codeErreurBCTService;
	}
	
	@GetMapping("/stats")
	public StatistiqueDTO statisc() {
	return reponseService.stats();	
	}
	// *****************************************************************************************************

	@PostMapping("/injectCodeErreur")
	public List<CodeErreurBCT> addCodeErreurFromFile(@RequestParam MultipartFile file) throws Exception {

		return codeErreurBCTService.generateCodeErreurBCTFromFile(file);
	}

	@GetMapping("/CodeErreurs")
	public List<CodeErreurBCT> getCodeErreurd() throws Exception {

		return codeErreurBCTService.getallCodeErreursBCT();
	}

	@GetMapping("/CodeErreurs/{codeErreur}")
	public CodeErreurBCT getCodeErreurByID(@PathVariable String codeErreur) {
		return codeErreurBCTService.getCodeErreurBCTByID(codeErreur);
	}

	@PostMapping("/CodeErreurs")
	public CodeErreurBCT addCodeErreur(@RequestBody CodeErreurBCT codeErreurBCT) {
		return codeErreurBCTService.ajouterCodeErreur(codeErreurBCT);
	}

	@PutMapping("/CodeErreurs")
	public CodeErreurBCT updateCodeErreur(@RequestBody CodeErreurBCT codeErreurBCT) {
		return codeErreurBCTService.modifierCodeErreur(codeErreurBCT);
	}

	@DeleteMapping("/CodeErreurs/{codeErreur}")
	public void deleteCodeErreurByID(@PathVariable String codeErreur) {
		codeErreurBCTService.supprimerCodeErreur(codeErreur);
	}

	// *****************************************************************************************************
	@GetMapping("/deleteFiles")
	public String test() {
		reponseService.deleteAll();
		return "";
	}

	// *****************************************************************************************************
	@PostMapping("/injectRapportOD")
	public List<RapportOperationDevise> injectFichierRejet(@RequestParam MultipartFile file) {
		try {
			return reponseService.getRapportsRejetsODFromFile(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("File exist or something went wrong..." + e.getMessage());
		}
		return rapportRejetService.getRapportOperationDeviseRejets();
	}

	@GetMapping("/rapportsOD")
	public List<RapportOperationDevise> getRapportsOperationDevise() {

		return rapportRejetService.getRapportOperationDeviseRejets();
	}

	@GetMapping("/rapportOD/{id}")
	public RapportOperationDevise getRapportOperationDeviseByID(@PathVariable Long id) {

		return rapportRejetService.getRapportOperationDeviseRejetsById(id);
	}

	// *****************************************************************************************************
	@PostMapping("/injectRapportPM")
	public List<RapportPM> injectFichierRejetPM(@RequestParam MultipartFile file) {
		try {
			return reponseService.getRapportsRejetsPMFromFile(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("File exist or something went wrong..." + e.getMessage());
		}
		return rapportRejetService.getRapportPMRejets();
	}

	@GetMapping("/rapportsPM")
	public List<RapportPM> getRapportsPM() {

		return rapportRejetService.getRapportPMRejets();
	}

	// *****************************************************************************************************
	@PostMapping("/injectRapportPP")
	public List<RapportPP> injectFichierRejetPP(@RequestParam MultipartFile file) {
		try {
			return reponseService.getRapportsRejetsPPFromFile(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("File exist or something went wrong..." + e.getMessage());
		}
		return rapportRejetService.getRapportsPPRejets();
	}

	@GetMapping("/rapportsPP")
	public List<RapportPP> getRapportsPP() {

		return rapportRejetService.getRapportsPPRejets();
	}

}
