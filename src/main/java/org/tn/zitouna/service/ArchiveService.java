package org.tn.zitouna.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tn.zitouna.dao.*;
import org.tn.zitouna.entities.*;


@Service
public class ArchiveService {
	private ArchiveRapportODRepository archiveRapportODRepository;
	private ArchiveRapportPPRepository archiveRapportPPRepository;
	private ArchiveRapportPMRepository archiveRapportPMRepository;
	private RapportOperationDeviseRepository rapportOperationDeviseRepository;
	private RapportPMRepository rapportPMRepository;
	private RapportPPRepository rapportPPRepository;

	@Autowired
	public ArchiveService(ArchiveRapportODRepository archiveRapportODRepository,
			ArchiveRapportPPRepository archiveRapportPPRepository,
			ArchiveRapportPMRepository archiveRapportPMRepository,
			RapportOperationDeviseRepository rapportOperationDeviseRepository, RapportPMRepository rapportPMRepository,
			RapportPPRepository rapportPPRepository) {
		this.archiveRapportODRepository = archiveRapportODRepository;
		this.archiveRapportPPRepository = archiveRapportPPRepository;
		this.archiveRapportPMRepository = archiveRapportPMRepository;
		this.rapportOperationDeviseRepository = rapportOperationDeviseRepository;
		this.rapportPMRepository = rapportPMRepository;
		this.rapportPPRepository = rapportPPRepository;
	}

	// archive
	public List<ArchiveRapportOD> getAllOD() {
		return archiveRapportODRepository.findAll();
	}

	public ArchiveRapportOD archiveOD(ArchiveRapportOD archiveOD) {
		return archiveRapportODRepository.insert(archiveOD);
	}

	public void deletearchiveOD(String id) {
		archiveRapportODRepository.deleteById(id);
	}

	public List<ArchiveRapportPP> getAllPP() {
		return archiveRapportPPRepository.findAll();
	}

	public ArchiveRapportPP archivePP(ArchiveRapportPP archivePP) {
		return archiveRapportPPRepository.insert(archivePP);
	}

	public void deletearchivePP(String id) {
		archiveRapportPPRepository.deleteById(id);
	}

	public List<ArchiveRapportPM> getAllPM() {
		return archiveRapportPMRepository.findAll();
	}

	public ArchiveRapportPM archivePM(ArchiveRapportPM archivePM) {
		return archiveRapportPMRepository.insert(archivePM);
	}

	public void deletearchivePM(String id) {
		archiveRapportPMRepository.deleteById(id);
	}

	// ************
	public ArchiveRapportOD archiverOD(Long numDeclaration) throws ParseException {
		ArchiveRapportOD a = new ArchiveRapportOD();
		RapportOperationDevise r = rapportOperationDeviseRepository.findById(numDeclaration).get();
		a.setCodeAgenceDeclarant(r.getCodeAgenceDeclarant());
		a.setCodeDevise(r.getCodeDevise());
		a.setCodeEnregistrement(r.getCodeEnregistrement());
		a.setCodeErreurRapports(r.getCodeErreurRapports());
		a.setCodeOperation(r.getCodeOperation());
		a.setCodePaysExpediteurDesFonds(r.getCodePaysExpediteurDesFonds());
		a.setCodeTypeOperation(r.getCodeTypeOperation());
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date today = new Date();

		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		a.setDateArchive(todayWithZeroTime);
		a.setDateDeclarationDouane(r.getDateDeclarationDouane());
		a.setDateOperation(r.getDateOperation());
		a.setEntete(r.getEntete());
		a.setFin(r.getFin());
		a.setIdentifiantInterneOperation(r.getIdentifiantInterneOperation());
		a.setMontantDeclareEnDouane(r.getMontantDeclareEnDouane());
		a.setMontantEnDevise(r.getMontantEnDevise());
		a.setMontantEnDinar(r.getMontantEnDinar());
		a.setNumDeclarationDouane(r.getNumDeclarationDouane());
		a.setNumeroDeclaration(r.getNumeroDeclaration());
		a.setNumIdentifiantBeneficiairePM(r.getNumIdentifiantBeneficiairePM());
		a.setNumIdentifiantBeneficiairePP(r.getNumIdentifiantBeneficiairePP());
		a.setNumIdentifiantClient(r.getNumIdentifiantClient());
		a.setRibBeneficiaire(r.getRibBeneficiaire());
		a.setTypeCompteBeneficiaire(r.getTypeCompteBeneficiaire());
		a.setTypeIdentifiantBeneficiairePM(r.getTypeIdentifiantBeneficiairePM());
		a.setTypeIdentifiantBeneficiairePP(r.getTypeIdentifiantBeneficiairePP());
		a.setTypeIdentifiantClient(r.getTypeIdentifiantClient());
		a.setCodeTypeOperation(r.getCodeTypeOperation());
		rapportOperationDeviseRepository.deleteById(numDeclaration);
		return this.archiveOD(a);
		
	}

	// ********
	public ArchiveRapportPP archiverPP(Long numDeclaration) throws ParseException {
		RapportPP r = rapportPPRepository.findById(numDeclaration).get();
		ArchiveRapportPP pp = new ArchiveRapportPP();
		pp.setEntete(r.getEntete());
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date today = new Date();

		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		pp.setDateArchive(todayWithZeroTime);
		pp.setNumeroDeclaration(r.getNumeroDeclaration());
		pp.setCodeEnregistrement(r.getCodeEnregistrement());
		pp.setCodeOperation(r.getCodeOperation());
		pp.setTypeDocument(r.getTypeDocument());
		pp.setNumeroDocumentIdentification(r.getNumeroDocumentIdentification());

		pp.setDateDebutDocumentIdentification(r.getDateDebutDocumentIdentification());
		pp.setLieuDelivranceDocumentIdentification(r.getLieuDelivranceDocumentIdentification());

		pp.setDateFinValiditeDocumentIdentification(r.getDateDebutDocumentIdentification());

		pp.setNomPersonPhysique(r.getNomPersonPhysique());
		pp.setPrenomPersonnePhysique(r.getPrenomPersonnePhysique());
		pp.setSexePersonnePhysique(r.getSexePersonnePhysique());
		pp.setDateNaissance(r.getDateNaissance());
		pp.setLieuNaissance(r.getLieuNaissance());
		pp.setQualitePersonne(r.getQualitePersonne());
		pp.setNationalite(r.getNationalite());
		pp.setProfession(r.getProfession());
		pp.setEtatCivil(r.getEtatCivil());
		pp.setSituationJuridique(r.getSituationJuridique());
		pp.setDateSituationJuridique(r.getDateSituationJuridique());
		pp.setNumeroTelephone1(r.getNumeroTelephone1());
		pp.setNumeroTelephone2(r.getNumeroTelephone2());
		pp.setNumeroFax(r.getNumeroFax());
		pp.setNumeroTelex(r.getNumeroTelex());
		pp.setAdresseEmail(r.getAdresseEmail());
		pp.setFin(r.getFin());
		pp.setCodeErreurRapports(r.getCodeErreurRapports());
		rapportPPRepository.deleteById(numDeclaration);
		return this.archivePP(pp);

	}

	// ********
	public ArchiveRapportPM archiverPM(Long numDeclaration) throws ParseException {
		RapportPM r = rapportPMRepository.findById(numDeclaration).get();
		ArchiveRapportPM pm = new ArchiveRapportPM();
		pm.setEntete(r.getEntete());
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date today = new Date();

		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		pm.setDateArchive(todayWithZeroTime);
		pm.setNumeroDeclaration(r.getNumeroDeclaration());
		pm.setCodeEnregistrement(r.getCodeEnregistrement());
		pm.setCodeOperation(r.getCodeOperation());

		pm.setTypeIdentifiantPersonne(r.getTypeIdentifiantPersonne());
		pm.setNumeroIdentifiantPersonne(r.getNumeroIdentifiantPersonne());
		pm.setNumeroRegistreCommerce(r.getNumeroRegistreCommerce());
		pm.setMatriculeFiscal(r.getMatriculeFiscal());
		pm.setRaisonSocialePM(r.getRaisonSocialePM());
		pm.setSiglePM(r.getSiglePM());
		pm.setQualitePM(r.getQualitePM());
		pm.setNationalitePM(r.getNationalitePM());
		pm.setCodeTypePM(r.getCodeTypePM());
		pm.setCodeFormeJuridique(r.getCodeFormeJuridique());
		pm.setCodeActivitePM(r.getCodeActivitePM());
		pm.setCodeRegime(r.getCodeRegime());
		pm.setCodeSituation(r.getCodeSituation());
		pm.setDateSituationJuridique(r.getDateSituationJuridique());
		pm.setReferenceJortSituationJuridique(r.getReferenceJortSituationJuridique());
		pm.setCreationPM(r.getCreationPM());
		pm.setReferenceJortPM(r.getReferenceJortPM());
		pm.setDateEntreExploitation(r.getDateEntreExploitation());
		pm.setMontantCapitalPM(r.getMontantCapitalPM());
		pm.setDateFixationCapitalSocial(r.getDateFixationCapitalSocial());

		pm.setNumeroTelephone1(r.getNumeroTelephone1());
		pm.setNumeroTelephone2(r.getNumeroTelephone2());
		pm.setNumeroFax(r.getNumeroFax());
		pm.setNumeroTelex(r.getNumeroTelex());
		pm.setAdresseEmail(r.getAdresseEmail());
		pm.setAdresseWebPM(r.getAdresseWebPM());
		pm.setFin(r.getFin());
		pm.setCodeErreurRapports(r.getCodeErreurRapports());
		rapportPMRepository.deleteById(numDeclaration);
		return this.archivePM(pm);

	}
}
