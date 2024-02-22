package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

class SalarieAideADomicileTest {

    @Test
    void aLegalementDroitADesCongesPayes() {
        //given
        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile(
                "test",
                LocalDate.now(),
                LocalDate.now(),
                80,
                10,
                10,
                20,
                5);
        //when
        boolean result =salarieAideADomicile.aLegalementDroitADesCongesPayes();
        //then
        Assertions.assertTrue(result);
    }

    @Test
    void aLegalementDroitADesCongesPayesFalse() {
        //given
        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile(
                "test",
                LocalDate.now(),
                LocalDate.now(),
                80,
                10,
                9,
                20,
                5);
        //when
        boolean result =salarieAideADomicile.aLegalementDroitADesCongesPayes();
        //then
        Assertions.assertFalse(result);
    }


    @Test
    void estHabituellementTravaille() {
        //given
        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile(
                "test",
                LocalDate.now(),
                LocalDate.now(),
                80,
                10,
                50,
                20,
                5);
        //when
        boolean result =salarieAideADomicile.estHabituellementTravaille(salarieAideADomicile.getMoisEnCours());
        //then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({
            "'2023-12-17', 2023-12-28, 9",
            "'2023-12-30', 2023-12-28, 0"
    })
    void calculeJoursDeCongeDecomptesPourPlage(String dateDebut, String dateFin, int expectedNb) {
        //GIVEN
        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile("Paul",
                LocalDate.of(2023, 6, 28),
                LocalDate.now(),
                20, 3,
                9, 1,
                8);
        //WHEN
        LinkedHashSet<LocalDate> resNb = salarieAideADomicile.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.parse(dateDebut),
                LocalDate.parse(dateFin));
        //THEN
        Assertions.assertEquals(expectedNb, resNb.size());
    }
}