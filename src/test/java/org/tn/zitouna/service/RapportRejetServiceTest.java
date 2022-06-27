package org.tn.zitouna.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.tn.zitouna.dao.RapportOperationDeviseRepository;
import org.tn.zitouna.dao.RapportPMRepository;
import org.tn.zitouna.dao.RapportPPRepository;
import org.tn.zitouna.entities.RapportOperationDevise;
import org.tn.zitouna.entities.RapportPM;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RapportRejetServiceTest {
	@Mock
	private RapportOperationDeviseRepository rapportOperationDeviseRepository;
	@Mock
	private RapportPPRepository rapportPPRepository;
	@Mock
	private RapportPMRepository rapportPMRepository;
	@InjectMocks
	RapportRejetService rapportRejetService;
	RapportOperationDevise rapportOperationDevise;
	@BeforeEach
	void setUp() throws Exception {
		rapportOperationDevise = new RapportOperationDevise();
		rapportOperationDevise.setNumeroDeclaration(7L);
	}

	@Test
	void testGetRapportsPPRejets() {
		Mockito.when(rapportPPRepository.findAll()).thenReturn(null);
		assertEquals(null,rapportRejetService.getRapportsPPRejets());
	}

	@Test
	void testGetRapportPMRejets() {
		when(rapportPMRepository.findAll()).thenReturn(null);
		assertEquals(null,rapportRejetService.getRapportPMRejets());
	}

	@Test
	void testGetRapportOperationDeviseRejets() {
		when(rapportOperationDeviseRepository.findAll()).thenReturn(null);
		assertEquals(null,rapportRejetService.getRapportOperationDeviseRejets());
	}

	@Test
	void testGetRapportOperationDeviseRejetsById() {
		when(rapportOperationDeviseRepository.findById(rapportOperationDevise.getNumeroDeclaration())).thenReturn(Optional.of(rapportOperationDevise));
		assertEquals(rapportOperationDevise, rapportRejetService.getRapportOperationDeviseRejetsById(rapportOperationDevise.getNumeroDeclaration()));
	}

}
