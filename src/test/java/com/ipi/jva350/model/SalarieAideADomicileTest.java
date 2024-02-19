package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
}