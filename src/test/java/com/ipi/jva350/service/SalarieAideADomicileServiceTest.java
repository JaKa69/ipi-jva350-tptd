package com.ipi.jva350.service;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalarieAideADomicileServiceTest {
    @Mock
    private SalarieAideADomicileRepository salarieAideADomicileRepository;
    @InjectMocks
    private SalarieAideADomicileService salarieAideADomicileService;
    @Test
    void ajouteConge() throws SalarieException {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                LocalDate.of(2022, 6, 28),
                LocalDate.of(2023, 11, 1),
                9, 2.5,
                80, 20, 8);
        // When :
        salarieAideADomicileService.ajouteConge(monSalarie, LocalDate.of(2023, 12, 17),
                LocalDate.of(2023, 12, 18));
        // Then :
        ArgumentCaptor<SalarieAideADomicile> salarieAideADomicileCaptor = ArgumentCaptor.forClass(SalarieAideADomicile.class);
        Mockito.verify(salarieAideADomicileRepository, times(1)).save(salarieAideADomicileCaptor.capture()); // arg capture !
        assertEquals(1L, salarieAideADomicileCaptor.getValue().getCongesPayesPrisAnneeNMoins1());
    }

    @Test
    void testCalculeLimiteEntrepriseCongesPermis() {
        // Given
        LocalDate moisEnCours = LocalDate.of(2022, 3, 1);
        double congesPayesAcquisAnneeNMoins1 = 20;
        LocalDate moisDebutContrat = LocalDate.of(2020, 1, 1);
        LocalDate premierJourDeConge = LocalDate.of(2022, 7, 1);
        LocalDate dernierJourDeConge = LocalDate.of(2022, 7, 10);
        when(salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(0.5);
        // When
        long limiteConges = this.salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(moisEnCours, congesPayesAcquisAnneeNMoins1, moisDebutContrat, premierJourDeConge, dernierJourDeConge);
        // Then
        long expectedLimiteConges = 5;
        assertEquals(expectedLimiteConges, limiteConges);
    }

    @Test
    void creerSalarieAideADomicile() throws SalarieException, EntityExistsException {
        //GIVEN
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                LocalDate.of(2022, 6, 28),
                LocalDate.of(2023, 11, 1),
                9, 2.5,
                80, 20, 8);
        //WHEN
        salarieAideADomicileService.creerSalarieAideADomicile(monSalarie);
        //THEN
        ArgumentCaptor<SalarieAideADomicile> captor = ArgumentCaptor.forClass(SalarieAideADomicile.class);
        Mockito.verify(salarieAideADomicileRepository).save(captor.capture());
        assertEquals(captor.getValue(), monSalarie);
    }

    @Test
    void creerSalarieAideADomicile_NullEchec() {
        // GIVEN
        SalarieAideADomicile monSalarie = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            salarieAideADomicileService.creerSalarieAideADomicile(monSalarie);
        });
    }

    @Test
    void creerSalarieAideADomicileIdNotNull() {
        // GIVEN
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(1L, "Paul");

        // WHEN & THEN
        assertThrows(SalarieException.class, () -> {
            salarieAideADomicileService.creerSalarieAideADomicile(monSalarie);
        });
    }
}