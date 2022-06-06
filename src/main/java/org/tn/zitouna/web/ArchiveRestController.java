package org.tn.zitouna.web;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tn.zitouna.service.ArchiveService;
import org.tn.zitouna.entities.*;

@RestController
public class ArchiveRestController {
	private ArchiveService archiveService;

	public ArchiveRestController(ArchiveService archiveService) {
		this.archiveService = archiveService;
	}
	
	@GetMapping(path = "/archiveOD")
	public List<ArchiveRapportOD> getAllArchiveOD() {
		return archiveService.getAllOD();
	}
	
	@GetMapping(path = "/archiveOD/{numDeclaration}")
	public ArchiveRapportOD ArchiverOD(@PathVariable Long numDeclaration) throws ParseException {
		return archiveService.archiverOD(numDeclaration);
	}
	
	@PostMapping("/archiveOD")
	public ArchiveRapportOD insertOD(@RequestBody ArchiveRapportOD r) {
		return archiveService.archiveOD(r);
	}
	
	@DeleteMapping("/archiveOD/{id}")
	public void deleteOD(@PathVariable String id) {
		archiveService.deletearchiveOD(id);
	}
	
	@GetMapping(path = "/archivePP")
	public List<ArchiveRapportPP> getAllArchivePP() {
		return archiveService.getAllPP();
	}
	
	@GetMapping(path = "/archivePP/{numDeclaration}")
	public ArchiveRapportPP ArchiverPP(@PathVariable Long numDeclaration) throws ParseException {
		return archiveService.archiverPP(numDeclaration);
	}
	
	@PostMapping("/archivePP")
	public ArchiveRapportPP insertPP(@RequestBody ArchiveRapportPP r) {
		return archiveService.archivePP(r);
	}
	
	@DeleteMapping("/archivePP/{id}")
	public void deletePP(@PathVariable String id) {
		archiveService.deletearchivePP(id);
	}
	
	
	@GetMapping(path = "/archivePM")
	public List<ArchiveRapportPM> getAllArchivePM() {
		return archiveService.getAllPM();
	}
	

	@GetMapping(path = "/archivePM/{numDeclaration}")
	public ArchiveRapportPM ArchiverPM(@PathVariable Long numDeclaration) throws ParseException {
		return archiveService.archiverPM(numDeclaration);
	}
	
	@PostMapping("/archivePM")
	public ArchiveRapportPM insertOD(@RequestBody ArchiveRapportPM r) {
		return archiveService.archivePM(r);
	}
	
	@DeleteMapping("/archivePM/{id}")
	public void deletePM(@PathVariable String id) {
		archiveService.deletearchivePM(id);
	}

}
