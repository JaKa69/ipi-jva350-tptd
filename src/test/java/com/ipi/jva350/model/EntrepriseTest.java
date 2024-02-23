package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EntrepriseTest {

    @Test
    public void testDateDansLaPlage() {
        LocalDate d = LocalDate.of(2022, 3, 15);
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = LocalDate.of(2022, 3, 31);
        assertTrue(Entreprise.estDansPlage(d, debut, fin));
    }

    @Test
    public void testDateAvantLaPlage() {
        LocalDate d = LocalDate.of(2022, 2, 28);
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = LocalDate.of(2022, 3, 31);
        assertFalse(Entreprise.estDansPlage(d, debut, fin));
    }

    @Test
    public void testDateApresLaPlage() {
        LocalDate d = LocalDate.of(2022, 4, 1);
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = LocalDate.of(2022, 3, 31);
        assertFalse(Entreprise.estDansPlage(d, debut, fin));
    }

    @Test()
    public void testDateNulle() {
        LocalDate d = null;
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = LocalDate.of(2022, 3, 31);
        assertThrows(IllegalArgumentException.class, () -> Entreprise.estDansPlage(d, debut, fin));
    }

    @Test()
    public void testDebutNulle() {
        LocalDate d = LocalDate.of(2022, 3, 1);
        LocalDate debut = null;
        LocalDate fin = LocalDate.of(2022, 3, 31);
        assertThrows(IllegalArgumentException.class, () -> Entreprise.estDansPlage(d, debut, fin));
    }

    @Test()
    public void testFinNulle() {
        LocalDate d = LocalDate.of(2022, 3, 5);
        LocalDate debut = LocalDate.of(2022, 3, 1);
        LocalDate fin = null;
        assertThrows(IllegalArgumentException.class, () -> Entreprise.estDansPlage(d, debut, fin));
    }
}