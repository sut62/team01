package com.sut62.team01;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team01.entity.Bed;
import com.sut62.team01.repository.BedRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BedTests {

    private Validator validator;

    @Autowired
    private BedRepository bedRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6010201_testInsertDataBedOK() {

        Bed bed = new Bed("เตียง B (กลางห้อง)");
        bed = bedRepository.saveAndFlush(bed);
        Optional<Bed> found = bedRepository.findById(bed.getId());
        assertEquals(bed , found.get());

    }

    @Test
    void b6010201_testRoomsMustNotBeNull() {

        Bed bed = new Bed(null);

        Set<ConstraintViolation<Bed>> result = validator.validate(bed);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("bed", result.iterator().next().getPropertyPath().toString());

    }
}
