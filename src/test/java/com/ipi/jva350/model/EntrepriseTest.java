package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EntrepriseTest {

    @Test
    public void testDateDansLaPlage() {
        //GIVEN
        LocalDate d = LocalDate.of(2022, 3, 15);
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = LocalDate.of(2022, 3, 31);
        //WHEN
        boolean estDansPlage = Entreprise.estDansPlage(d, debut, fin);
        //THEN
        assertTrue(estDansPlage);
    }

    @Test
    public void testDateAvantLaPlage() {
        //GIVEN
        LocalDate d = LocalDate.of(2022, 2, 28);
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = LocalDate.of(2022, 3, 31);
        //WHEN
        boolean estDansPlage = Entreprise.estDansPlage(d, debut, fin);
        //THEN
        assertFalse(estDansPlage);
    }

    @Test
    public void testDateApresLaPlage() {
        //GIVEN
        LocalDate d = LocalDate.of(2022, 4, 1);
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = LocalDate.of(2022, 3, 31);
        //WHEN
        boolean estDansPlage = Entreprise.estDansPlage(d, debut, fin);
        //THEN
        assertFalse(estDansPlage);
    }

    @Test()
    public void testDateNulle() {
        //GIVEN
        LocalDate d = null;
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = LocalDate.of(2022, 3, 31);
        //WHEN and THEN
        assertThrows(IllegalArgumentException.class, () -> Entreprise.estDansPlage(d, debut, fin));
    }

    @Test()
    public void testDebutNulle() {
        //GIVEN
        LocalDate d = LocalDate.of(2022, 3, 1);
        LocalDate debut = null;
        LocalDate fin = LocalDate.of(2022, 3, 31);
        //WHEN and THEN
        assertThrows(IllegalArgumentException.class, () -> Entreprise.estDansPlage(d, debut, fin));
    }

    @Test()
    public void testFinNulle() {
        //GIVEN
        LocalDate d = LocalDate.of(2022, 3, 5);
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = null;
        //WHEN and THEN
        assertThrows(IllegalArgumentException.class, () -> Entreprise.estDansPlage(d, debut, fin));
    }
    //tests get premier jour annee de conges
    @Test
    public void testDateNull() {
        // GIVEN
        LocalDate date = null;

        // WHEN
        LocalDate premierJourAnneeDeConges = Entreprise.getPremierJourAnneeDeConges(date);

        // THEN
        assertNull(premierJourAnneeDeConges);
    }

    @Test
    public void testDateApresPremierJuin() {
        // GIVEN
        LocalDate date = LocalDate.of(2022, 6, 15); // Un exemple de date après le 1er juin

        // WHEN
        LocalDate premierJourAnneeDeConges = Entreprise.getPremierJourAnneeDeConges(date);

        // THEN
        assertEquals(LocalDate.of(2022, 6, 1), premierJourAnneeDeConges);
    }

    @Test
    public void testDateAvantPremierJuin() {
        // GIVEN
        LocalDate date = LocalDate.of(2022, 5, 31); // Un exemple de date juste avant le 1er juin

        // WHEN
        LocalDate premierJourAnneeDeConges = Entreprise.getPremierJourAnneeDeConges(date);

        // THEN
        assertEquals(LocalDate.of(2021, 6, 1), premierJourAnneeDeConges);
    }

    //tests est jour ferié
    @Test
    public void testJourFerie() {
        // GIVEN
        LocalDate jourFerieConnu = LocalDate.of(2022, 1, 1);

        // WHEN
        boolean estFerie = Entreprise.estJourFerie(jourFerieConnu);

        // THEN
        assertTrue(estFerie);
    }

    @Test
    public void testJourNonFerie() {
        // GIVEN
        LocalDate jourOrdinaire = LocalDate.of(2022, 2, 28);

        // WHEN
        boolean estFerie = Entreprise.estJourFerie(jourOrdinaire);

        // THEN
        assertFalse(estFerie);
    }
}