package com.sut62.team01;

import com.sut62.team01.entity.BikeType;
import com.sut62.team01.repository.BikeTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BikeTypeTests {

    private Validator validator;

    @Autowired
    private BikeTypeRepository bikeTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6000783_testInsertBikeTypeOK() {
        BikeType bikeType = new BikeType();
        bikeType.setType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        Optional<BikeType> found = bikeTypeRepository.findById(bikeType.getId());
        assertEquals(bikeType.getType(), found.get().getType());
    }

    @Test
    void b6000783_testTypeMustNotBeNull() {
        BikeType bikeType = new BikeType();
        bikeType.setType(null);
        Set<ConstraintViolation<BikeType>> result = validator.validate(bikeType);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("type", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6000783_testTypeMustBeGreaterEqual5() {
        BikeType bikeType = new BikeType();
        bikeType.setType("1234");
        Set<ConstraintViolation<BikeType>> result = validator.validate(bikeType);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 30", result.iterator().next().getMessage());
        assertEquals("type", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6000783_testTypeMustBeLessEqual30() {
        BikeType bikeType = new BikeType();
        bikeType.setType("1234567890123456789012345678901");
        Set<ConstraintViolation<BikeType>> result = validator.validate(bikeType);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 30", result.iterator().next().getMessage());
        assertEquals("type", result.iterator().next().getPropertyPath().toString());
    }
}
