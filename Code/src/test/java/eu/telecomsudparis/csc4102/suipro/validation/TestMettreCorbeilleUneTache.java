// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.suipro.Tache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;


class TestMettreCobeilleDeveloppeur {
	private SuiPro suiPro;
	private String intituleTache;
	private String intituleActivite;
    
	@BeforeEach
	void setUp() {
		suiPro = new SuiPro("Projet suipro ");
		intituleActivite = "intituleActivite";
		intituleTache = "intituleTache";
		
	}

	@AfterEach
	void tearDown() {
		suiPro = null;
		intituleTache = null;
		intituleActivite = null;
	}

	@Test
	void mettreCorbeilleUneTacheTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUneTache(intituleActivite,""));
	}
	@Test
	void mettreCorbeilleUneTacheTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUneTache(intituleActivite,null));
	}
	@Test
	void mettreCorbeilleUneTacheTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUneTache(intituleActivite,"Cette tache n'existe pas"));
	}
	@Test
	void mettreCorbeilleUneTacheTest3Jeu1() throws Exception {
		suiPro.ajouterUneActivite(intituleActivite, intituleActivite);
		suiPro.ajouterUneTache(intituleActivite, intituleTache, intituleActivite);
		suiPro.mettreCorbeilleUneTache(intituleActivite, intituleTache);
		Tache tache = suiPro.getActivites().get(intituleActivite).getTaches().get(intituleTache);

		Assertions.assertTrue(tache.getCorbeille());

	}

}
