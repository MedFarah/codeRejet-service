package org.tn.zitouna.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.tn.zitouna.dao.ArchiveRapportODRepository;
import org.tn.zitouna.dao.CodeErreurBCTRepository;
import org.tn.zitouna.dao.RapportOperationDeviseRepository;
import org.tn.zitouna.dao.RapportPMRepository;
import org.tn.zitouna.dao.RapportPPRepository;
import org.tn.zitouna.dto.StatistiqueDTO;
import org.tn.zitouna.entities.*;

@Service
public class ReponseService {
	private RapportOperationDeviseRepository rapportOperationDeviseRepository;
	private RapportPMRepository rapportPMRepository;
	private RapportPPRepository rapportPPRepository;
	private IGenerateRapportFromFile igenerateRapportFromFile;
	private CodeErreurBCTRepository codeErreurBCTRepository;

	private final Path root = Paths.get("uploads");

	@Autowired
	public ReponseService(RapportOperationDeviseRepository rapportOperationDeviseRepository,
			RapportPMRepository rapportPMRepository, RapportPPRepository rapportPPRepository,
			CodeErreurBCTRepository codeErreurBCTRepository, IGenerateRapportFromFile igenerateRapportFromFile) {
		this.rapportOperationDeviseRepository = rapportOperationDeviseRepository;
		this.rapportPMRepository = rapportPMRepository;
		this.rapportPPRepository = rapportPPRepository;
		this.igenerateRapportFromFile = igenerateRapportFromFile;
		this.codeErreurBCTRepository = codeErreurBCTRepository;

	}
	
	public StatistiqueDTO stats() {
		StatistiqueDTO stats = new StatistiqueDTO();
		stats.setNbrRejetOD(rapportOperationDeviseRepository.count());
		stats.setNbrRejetPM(rapportPMRepository.count());
		stats.setNbrRejetPP(rapportPPRepository.count());
		return stats;
	}
	
	
	// delete files from folder => delete folder
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	public List<RapportOperationDevise> getRapportsRejetsODFromFile(MultipartFile file) throws Exception {
		Entete e = new Entete();
		RapportOperationDevise rop = new RapportOperationDevise();
		Fin f = new Fin();
		CodeErreurBCT c = new CodeErreurBCT();
		List<CodeErreurRapport> CodeErreurRapports = new ArrayList<CodeErreurRapport>();
		List<CodeErreurRapport> CodeErreurRapportsEntete = new ArrayList<CodeErreurRapport>();
		List<CodeErreurRapport> CodeErreurRapportsFin = new ArrayList<CodeErreurRapport>();
		CodeErreurBCT codeBackup = new CodeErreurBCT("CodeNotFoundInDatabase", " le code d erreur n'existe pas ");
		CodeErreurBCT codeBackupForNullValue = new CodeErreurBCT("pas d'erreur", " success ");
		// codeErreurBCTRepository.findById("ENT00113").get();
		int i = 0;
		int j = 0;
		String ch = "";
		// ajouter le fichier dans uploads folder
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception ee) {
			throw new RuntimeException("Could not store the file. Error: " + ee.getMessage());
		}

		System.out.println("file path : " + root.toAbsolutePath().toString() + file.getOriginalFilename());
		try (BufferedReader br = new BufferedReader(
				new FileReader(root.toAbsolutePath().toString() + "\\" + file.getOriginalFilename()))) {
			for (String line; (line = br.readLine()) != null;) {
				// process the line.
				String cc = line.trim();

				if (i == 0) {
					if (line.lastIndexOf("R") > 0) {
						j = cc.lastIndexOf("R");
						// test length : if length > 1 ajoutih fi liste : ken fama erreur n'ajoutiwha
						// sinon khali list null fi blaset ""
						ch = cc.substring(j + 1).replaceAll(" ", "");
						if (ch.length() > 1) {
							c = codeErreurBCTRepository.findById(cc.substring(j + 1).replaceAll(" ", ""))
									.orElse(codeBackup);

							CodeErreurRapportsEntete.add(new CodeErreurRapport(c.getCodeErreur(), c.getDescription()));
						} else {
							CodeErreurRapportsEntete.add(new CodeErreurRapport(codeBackupForNullValue.getCodeErreur(),
									codeBackupForNullValue.getDescription()));
						}

					}
					e = igenerateRapportFromFile.addEnteteFromFile(line);
					e.setCodeErreurRapports(CodeErreurRapportsEntete);
					i += 1;
				} else if (i == 1) {

					rop = igenerateRapportFromFile.generateRapportOperationDeviseCorpsFromFile(line);
					rop.setEntete(e);
					if (line.lastIndexOf("R") > 0) {
						j = cc.lastIndexOf("R");
						ch = cc.substring(j + 1).replaceAll(" ", "");

						if (ch.length() > 1) {
							c = codeErreurBCTRepository.findById(cc.substring(j + 1).replaceAll(" ", ""))
									.orElse(codeBackup);
							CodeErreurRapports.add(new CodeErreurRapport(c.getCodeErreur(), c.getDescription()));
						} else {
							CodeErreurRapportsEntete.add(new CodeErreurRapport(codeBackupForNullValue.getCodeErreur(),
									codeBackupForNullValue.getDescription()));
						}
					}
					i += 1;
				} else if (i == 2) {
					f = igenerateRapportFromFile.addLigneFinFromFile(line);
					if (line.lastIndexOf("R") > 0) {
						j = cc.lastIndexOf("R");
						ch = cc.substring(j + 1).replaceAll(" ", "");
						if (ch.length() > 1) {
							c = codeErreurBCTRepository.findById(cc.substring(j + 1).replaceAll(" ", ""))
									.orElse(codeBackup);
							CodeErreurRapportsFin.add(new CodeErreurRapport(c.getCodeErreur(), c.getDescription()));
						} else {
							CodeErreurRapportsEntete.add(new CodeErreurRapport(codeBackupForNullValue.getCodeErreur(),
									codeBackupForNullValue.getDescription()));
						}
					}
					f.setCodeErreurRapports(CodeErreurRapportsFin);
					rop.setFin(f);
					rop.setCodeErreurRapports(CodeErreurRapports);
					rapportOperationDeviseRepository.save(rop);
					CodeErreurRapportsEntete.clear();
					CodeErreurRapports.clear();
					CodeErreurRapportsFin.clear();
					i = 0;
				}
			}
			// line is not visible here.
		}
		return rapportOperationDeviseRepository.findAll();
	}

	public List<RapportPM> getRapportsRejetsPMFromFile(MultipartFile file) throws Exception {
		Entete e = new Entete();
		RapportPM rpm = new RapportPM();

		Fin f = new Fin();
		List<CodeErreurRapport> CodeErreurRapports = new ArrayList<CodeErreurRapport>();
		List<CodeErreurRapport> CodeErreurRapportsEntete = new ArrayList<CodeErreurRapport>();
		List<CodeErreurRapport> CodeErreurRapportsFin = new ArrayList<CodeErreurRapport>();
		CodeErreurBCT c = new CodeErreurBCT();
		CodeErreurBCT codeBackupForNullValue = new CodeErreurBCT("pas d'erreur", " success ");
		CodeErreurBCT codeBackup = new CodeErreurBCT("CodeNotFoundInDatabase", " le code d erreur n'existe pas ");
		String ch = "";
		int i = 0;
		int j = 0;
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception ee) {
			throw new RuntimeException("Could not store the file. Error: " + ee.getMessage());
		}

		try (BufferedReader br = new BufferedReader(
				new FileReader(root.toAbsolutePath().toString() + "\\" + file.getOriginalFilename()))) {
			for (String line; (line = br.readLine()) != null;) {
				// process the line.
				String cc = line.trim();

				if (i == 0) {
					if (line.lastIndexOf("R") > 0) {
						j = cc.lastIndexOf("R");
						ch = cc.substring(j + 1).replaceAll(" ", "");
						if (ch.length() > 1) {
							c = codeErreurBCTRepository.findById(cc.substring(j + 1).replaceAll(" ", ""))
									.orElse(codeBackup);
							CodeErreurRapportsFin.add(new CodeErreurRapport(c.getCodeErreur(), c.getDescription()));
						} else {
							CodeErreurRapportsEntete.add(new CodeErreurRapport(codeBackupForNullValue.getCodeErreur(),
									codeBackupForNullValue.getDescription()));
						}
					}
					e = igenerateRapportFromFile.addEnteteFromFile(line);
					e.setCodeErreurRapports(CodeErreurRapportsEntete);
					i += 1;
				} else if (i == 1) {
					// c=line.strip().trim();

					rpm = igenerateRapportFromFile.generateRapportPMCorpsFromFile(line);
					rpm.setEntete(e);
					if (line.lastIndexOf("R") > 0) {
						j = cc.lastIndexOf("R");
						ch = cc.substring(j + 1).replaceAll(" ", "");

						if (ch.length() > 1) {
							c = codeErreurBCTRepository.findById(cc.substring(j + 1).replaceAll(" ", ""))
									.orElse(codeBackup);
							CodeErreurRapports.add(new CodeErreurRapport(c.getCodeErreur(), c.getDescription()));
						} else {
							CodeErreurRapportsEntete.add(new CodeErreurRapport(codeBackupForNullValue.getCodeErreur(),
									codeBackupForNullValue.getDescription()));
						}
					}
					System.out.println("taille*****************" + line.length());
					i += 1;
				} else if (i == 2) {
					f = igenerateRapportFromFile.addLigneFinFromFile(line);
					if (line.lastIndexOf("R") > 0) {
						j = cc.lastIndexOf("R");
						ch = cc.substring(j + 1).replaceAll(" ", "");
						if (ch.length() > 1) {
							c = codeErreurBCTRepository.findById(cc.substring(j + 1).replaceAll(" ", ""))
									.orElse(codeBackup);
							CodeErreurRapportsFin.add(new CodeErreurRapport(c.getCodeErreur(), c.getDescription()));
						} else {
							CodeErreurRapportsEntete.add(new CodeErreurRapport(codeBackupForNullValue.getCodeErreur(),
									codeBackupForNullValue.getDescription()));
						}
					}
					f.setCodeErreurRapports(CodeErreurRapportsFin);
					rpm.setFin(f);
					CodeErreurRapportsEntete.clear();
					CodeErreurRapports.clear();
					CodeErreurRapportsFin.clear();
					rapportPMRepository.save(rpm);
					i = 0;
				}
			}
			// line is not visible here.
		}
		return rapportPMRepository.findAll();
	}

	public List<RapportPP> getRapportsRejetsPPFromFile(MultipartFile file) throws Exception {
		Entete e = new Entete();
		RapportPP rpp = new RapportPP();

		Fin f = new Fin();
		List<CodeErreurRapport> CodeErreurRapports = new ArrayList<CodeErreurRapport>();
		List<CodeErreurRapport> CodeErreurRapportsEntete = new ArrayList<CodeErreurRapport>();
		List<CodeErreurRapport> CodeErreurRapportsFin = new ArrayList<CodeErreurRapport>();
		CodeErreurBCT codeBackup = new CodeErreurBCT("CodeNotFoundInDatabase", " le code d erreur n'existe pas ");
		CodeErreurBCT c = new CodeErreurBCT();
		CodeErreurBCT codeBackupForNullValue = new CodeErreurBCT("pas d'erreur", " success ");
		String ch = "";
		int i = 0;
		int j = 0;
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception ee) {
			throw new RuntimeException("Could not store the file. Error: " + ee.getMessage());
		}

		try (BufferedReader br = new BufferedReader(
				new FileReader(root.toAbsolutePath().toString() + "\\" + file.getOriginalFilename()))) {

			for (String line; (line = br.readLine()) != null;) {
				// process the line.
				String cc = line.trim();

				if (i == 0) {
					if (line.lastIndexOf("R") > 0) {
						j = cc.lastIndexOf("R");
						ch = cc.substring(j + 1).replaceAll(" ", "");
						if (ch.length() > 1) {
							c = codeErreurBCTRepository.findById(cc.substring(j + 1).replaceAll(" ", ""))
									.orElse(codeBackup);
							CodeErreurRapportsEntete.add(new CodeErreurRapport(c.getCodeErreur(), c.getDescription()));
						} else {
							CodeErreurRapportsEntete.add(new CodeErreurRapport(codeBackupForNullValue.getCodeErreur(),
									codeBackupForNullValue.getDescription()));
						}
					}
					e = igenerateRapportFromFile.addEnteteFromFile(line);
					e.setCodeErreurRapports(CodeErreurRapportsEntete);
					i += 1;
				} else if (i == 1) {

					rpp = igenerateRapportFromFile.generateRapportPPCorpsFromFile(line);
					rpp.setEntete(e);
					if (line.lastIndexOf("R") > 0) {
						j = cc.lastIndexOf("R");
						ch = cc.substring(j + 1).replaceAll(" ", "");

						if (ch.length() > 1) {
							c = codeErreurBCTRepository.findById(cc.substring(j + 1).replaceAll(" ", ""))
									.orElse(codeBackup);
							CodeErreurRapports.add(new CodeErreurRapport(c.getCodeErreur(), c.getDescription()));
						} else {
							CodeErreurRapportsEntete.add(new CodeErreurRapport(codeBackupForNullValue.getCodeErreur(),
									codeBackupForNullValue.getDescription()));
						}
					}
					i += 1;
				} else if (i == 2) {
					f = igenerateRapportFromFile.addLigneFinFromFile(line);
					if (line.lastIndexOf("R") > 0) {
						j = cc.lastIndexOf("R");
						if (ch.length() > 1) {
							c = codeErreurBCTRepository.findById(cc.substring(j + 1).replaceAll(" ", ""))
									.orElse(codeBackup);
							CodeErreurRapportsFin.add(new CodeErreurRapport(c.getCodeErreur(), c.getDescription()));
						} else {
							CodeErreurRapportsEntete.add(new CodeErreurRapport(codeBackupForNullValue.getCodeErreur(),
									codeBackupForNullValue.getDescription()));
						}
					}
					f.setCodeErreurRapports(CodeErreurRapportsFin);
					rpp.setFin(f);
					rpp.setCodeErreurRapports(CodeErreurRapports);
					rapportPPRepository.save(rpp);
					CodeErreurRapportsEntete.clear();
					CodeErreurRapports.clear();
					CodeErreurRapportsFin.clear();
					i = 0;
				}
			}
			// line is not visible here.
		}
		return rapportPPRepository.findAll();
	}

}
