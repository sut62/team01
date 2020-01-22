package com.sut62.team01;

import com.sut62.team01.entity.Bike;
import com.sut62.team01.entity.BikeType;
import com.sut62.team01.repository.BikeRepository;
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
public class BikeTests {

    Validator validator;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private BikeTypeRepository bikeTypeRepository;
    @BeforeEach
    public void setup(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6000783_testInsertDataOK(){
//        necessary object for Bike
        BikeType bikeType = new BikeType("ประเภทจักรยาน");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);

        Bike bike = new Bike();
        bike.setBikeType(bikeType);
        bike.setName("Bike1234");
        bike = bikeRepository.saveAndFlush(bike);
        Optional<Bike> found = bikeRepository.findById(bike.getId());
        assertEquals(bike,found.get());
    }

    @Test
//    test name must be match a pattern Bikexxxx | x is number
    void b6000783_testNameMustMatchAPatternBikexxxx(){
        //        necessary object for Bike
        BikeType bikeType = new BikeType("ประเภทจักรยาน");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);

        Bike bike = new Bike();
        bike.setBikeType(bikeType);
        bike.setName("Bikeabcd");
        Set<ConstraintViolation<Bike>> result = validator.validate(bike);

        assertEquals(1,result.size());
        assertEquals("must match \"Bike+\\d{4}\"",result.iterator().next().getMessage());
        assertEquals("name",result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6000783_testNameMustNotBeNull(){
        BikeType bikeType = new BikeType("ประเภทจักรยาน");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);

        Bike bike = new Bike();
        bike.setBikeType(bikeType);
        bike.setName(null);
        Set<ConstraintViolation<Bike>> result = validator.validate(bike);

        assertEquals(1,result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("name",result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6000783_testBikeTypeMustNotBeNull(){
        //        necessary object for Bike
        BikeType bikeType = new BikeType("ประเภทจักรยาน");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);

        Bike bike = new Bike();
        bike.setBikeType(null);
        bike.setName("Bike1234");
        Set<ConstraintViolation<Bike>> result = validator.validate(bike);

        assertEquals(1,result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("bikeType",result.iterator().next().getPropertyPath().toString());
    }
}
