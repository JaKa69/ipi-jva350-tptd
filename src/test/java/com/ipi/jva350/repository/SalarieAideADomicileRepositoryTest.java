package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class SalarieAideADomicileRepositoryTest {
    @Autowired
    private SalarieAideADomicileRepository salarieAideADomicileRepository;

    @Test
    void findByNomFound() {
        String testNom = "test";
        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile();
        salarieAideADomicile.setNom(testNom);
        salarieAideADomicileRepository.save(salarieAideADomicile);
        SalarieAideADomicile res = salarieAideADomicileRepository.findByNom(testNom);
        Assertions.assertEquals(testNom, res.getNom());
    }

    @Test
    void findByNomNotFound() {
        SalarieAideADomicile res = salarieAideADomicileRepository.findByNom("Ruben");
        Assertions.assertNull(res);
    }
}