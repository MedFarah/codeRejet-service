package org.tn.zitouna.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.tn.zitouna.entities.Entete;
import org.tn.zitouna.entities.Fin;
import org.tn.zitouna.entities.RapportOperationDevise;
import org.tn.zitouna.entities.RapportPM;
import org.tn.zitouna.entities.RapportPP;

@Service
public class GenerateRapportFromFile implements IGenerateRapportFromFile {

	@Override
	public RapportPP generateRapportPPCorpsFromFile(String line) throws ParseException {
		// TODO Auto-generated method stub
		RapportPP rpp = new RapportPP();
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		rpp.setNumeroDeclaration(Long.valueOf(line.substring(0, 7)));
		rpp.setCodeOperation(line.substring(10, 11));
		rpp.setTypeDocument(line.substring(11, 12));
		rpp.setNumeroDocumentIdentification(line.substring(12, 27));
		
		String dateDebutDocumentIdentification = line.substring(27, 35);
		dateDebutDocumentIdentification = this.addChar(dateDebutDocumentIdentification, '/', 2);
		dateDebutDocumentIdentification = this.addChar(dateDebutDocumentIdentification, '/', 5);
		Date date = sourceFormat.parse(dateDebutDocumentIdentification);
		rpp.setDateDebutDocumentIdentification(date);
		
		rpp.setLieuDelivranceDocumentIdentification(line.substring(35, 95));
		
		String dateFinValidteDocumentIdentification = line.substring(95, 103);
		dateFinValidteDocumentIdentification = this.addChar(dateFinValidteDocumentIdentification, '/', 2);
		dateFinValidteDocumentIdentification = this.addChar(dateFinValidteDocumentIdentification, '/', 5);
		Date dateFin = sourceFormat.parse(dateFinValidteDocumentIdentification);
		rpp.setDateFinValiditeDocumentIdentification(dateFin);
		
		rpp.setNomPersonPhysique(line.substring(103, 163));
		rpp.setPrenomPersonnePhysique(line.substring(163, 223));
		rpp.setSexePersonnePhysique(line.substring(223,224));
		
		String dateNaissance = line.substring(224, 232);
		dateNaissance = this.addChar(dateNaissance, '/', 2);
		dateNaissance = this.addChar(dateNaissance, '/', 5);
		Date dateN = sourceFormat.parse(dateNaissance);
		rpp.setDateNaissance(dateN);
		
		rpp.setLieuNaissance(line.substring(232, 292));
		rpp.setQualitePersonne(line.substring(292, 294));
		rpp.setNationalite(line.substring(294, 297));
		rpp.setProfession(line.substring(297, 301));
		rpp.setEtatCivil(line.substring(301, 302));
		rpp.setSituationJuridique(Integer.valueOf(line.substring(302, 304)));
		
		String dateSituationJuridique = line.substring(304, 312);
		dateSituationJuridique = this.addChar(dateSituationJuridique, '/', 2);
		dateSituationJuridique = this.addChar(dateSituationJuridique, '/', 5);
		Date dateSJ = sourceFormat.parse(dateSituationJuridique);
		rpp.setDateSituationJuridique(dateSJ);
		
		rpp.setNumeroTelephone1(line.substring(312, 327));
		rpp.setNumeroTelephone2(line.substring(327, 342));
		rpp.setNumeroFax(line.substring(342, 357));
		rpp.setNumeroTelex(line.substring(357, 372));
		rpp.setAdresseEmail(line.substring(372, 402));
		
		return rpp;
	}

	@Override
	public RapportPM generateRapportPMCorpsFromFile(String line) throws Exception {
		// TODO Auto-generated method stub
		RapportPM rpm = new RapportPM();
		rpm.setNumeroDeclaration(Long.valueOf(line.substring(0, 7)));
		rpm.setCodeOperation(line.substring(10, 11));
		rpm.setTypeIdentifiantPersonne(line.substring(11, 13));
		rpm.setNumeroIdentifiantPersonne(line.substring(13, 28));
		rpm.setNumeroRegistreCommerce(line.substring(28, 41));
		rpm.setMatriculeFiscal(line.substring(41, 51));
		rpm.setRaisonSocialePM(line.substring(51, 141));
		rpm.setSiglePM(line.substring(141, 171));
		rpm.setQualitePM(line.substring(171, 173));
		rpm.setNationalitePM(line.substring(173, 176));
		rpm.setCodeTypePM(Integer.valueOf(line.substring(176, 178)));
		rpm.setCodeFormeJuridique(line.substring(178, 184));
		rpm.setCodeActivitePM(line.substring(184, 191));
		rpm.setCodeRegime(Integer.valueOf(line.substring(191, 193)));
		rpm.setCodeSituation(Integer.valueOf(line.substring(193, 195)));
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateSituationJuridique = line.substring(195, 202);
		dateSituationJuridique = this.addChar(dateSituationJuridique, '/', 2);
		dateSituationJuridique = this.addChar(dateSituationJuridique, '/', 5);
		Date date = sourceFormat.parse(dateSituationJuridique);
		rpm.setDateSituationJuridique(date);
		rpm.setReferenceJortSituationJuridique(line.substring(202, 232));
		String dateCreationPM = line.substring(232, 240);
		dateCreationPM = this.addChar(dateCreationPM, '/', 2);
		dateCreationPM = this.addChar(dateCreationPM, '/', 5);
		Date dateC = sourceFormat.parse(dateCreationPM);
		rpm.setCreationPM(dateC);
		rpm.setReferenceJortPM(line.substring(240, 270));
		String dateEntree = line.substring(270, 278);
		dateEntree = this.addChar(dateEntree, '/', 2);
		dateEntree = this.addChar(dateEntree, '/', 5);
		Date dateE = sourceFormat.parse(dateEntree);
		rpm.setDateEntreExploitation(dateE);
		rpm.setMontantCapitalPM(Integer.valueOf(line.substring(278, 290)));
		String dateFixation = line.substring(290, 298);
		dateFixation = this.addChar(dateFixation, '/', 2);
		dateFixation = this.addChar(dateFixation, '/', 5);
		Date dateF = sourceFormat.parse(dateFixation);
		rpm.setDateFixationCapitalSocial(dateF);
		rpm.setNumeroTelephone1(line.substring(298, 313));
		rpm.setNumeroTelephone2(line.substring(313, 328));
		rpm.setNumeroFax(line.substring(328, 343));
		rpm.setNumeroTelex(line.substring(343, 358));
		rpm.setAdresseEmail(line.substring(358, 388));
		rpm.setAdresseWebPM(line.substring(388, 418));
		return rpm;
	}

	@Override
	public RapportOperationDevise generateRapportOperationDeviseCorpsFromFile(String line) throws ParseException {
		// TODO Auto-generated method stub
		RapportOperationDevise rop = new RapportOperationDevise();
		Long numDeclaration = Long.valueOf(line.substring(0, 7));
		rop.setNumeroDeclaration(numDeclaration);
		rop.setCodeOperation(line.substring(9, 10));
		rop.setTypeIdentifiantClient(line.substring(10, 11));
		rop.setNumIdentifiantClient(line.substring(19, 34));
		rop.setCodeAgenceDeclarant(Integer.valueOf(line.substring(34, 37)));
		rop.setIdentifiantInterneOperation(line.substring(37, 44));
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateDeclaration = line.substring(45, 53);
		System.out.println("date*//*/"+dateDeclaration);
		dateDeclaration = this.addChar(dateDeclaration, '/', 2);
		dateDeclaration = this.addChar(dateDeclaration, '/', 5);
		Date dateD = sourceFormat.parse(dateDeclaration);
		rop.setDateOperation(dateD);
		rop.setCodeTypeOperation(line.substring(53, 55));
		rop.setCodeDevise(line.substring(55, 58));
		rop.setNumDeclarationDouane(line.substring(58, 76));
		return rop;
	}

	@Override
	public Entete addEnteteFromFile(String ch) throws ParseException {
		// TODO Auto-generated method stub
		Entete e = new Entete();
		// String.format("%07d", val); add leading zeros to a number 07=> length i exp:
		// i=111 res=> 0000111
		int numDeclaration = Integer.valueOf(ch.substring(0, 7));
		e.setNDECL(numDeclaration);
		System.out.println("num declaration  = " + numDeclaration);
		int ktenr = Integer.valueOf(ch.substring(7, 10));
		e.setKTENR(ktenr);
		int kte = Integer.valueOf(ch.substring(10, 16));
		e.setKTE(kte);
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateDeclaration = ch.substring(16, 24);
		dateDeclaration = this.addChar(dateDeclaration, '/', 2);
		dateDeclaration = this.addChar(dateDeclaration, '/', 5);
		Date dateD = sourceFormat.parse(dateDeclaration);
		System.out.println("num declaration  = " + dateD);
		e.setDDECL(dateD);
		int kmdec = Integer.valueOf(ch.substring(24, 25));
		e.setKMDECL(kmdec);
		String demiss = ch.substring(25, 33);
		demiss = this.addChar(demiss, '/', 2);
		demiss = this.addChar(demiss, '/', 5);
		Date datee = sourceFormat.parse(demiss);
		e.setDEMISS(datee);
		return e;
	}

	@Override
	public Fin addLigneFinFromFile(String line) {
		// TODO Auto-generated method stub
		Fin f = new Fin();
		int numDeclaration = Integer.valueOf(line.substring(0, 7));
		f.setNDECL(numDeclaration);
		int ktenr = Integer.valueOf(line.substring(7, 10));
		f.setKTENR(ktenr);
		int tot_mvt = Integer.valueOf(line.substring(10, 24));
		f.setTOT_MVT(tot_mvt);
		int v_cpt2 = Integer.valueOf(line.substring(24, 38));
		f.setV_CPT2(v_cpt2);
		int v_cpt3 = Integer.valueOf(line.substring(38, 52));
		f.setV_CPT3(v_cpt3);
		return f;
	}

	public String addChar(String str, char ch, int position) {
		StringBuilder sb = new StringBuilder(str);
		sb.insert(position, ch);
		return sb.toString();
	}
}
