package com.sut62.team01;

import com.sut62.team01.entity.ElectricType;
import com.sut62.team01.repository.ElectricTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ElectricTypeTests {

    private Validator validator;

    @Autowired
    private ElectricTypeRepository electricTypeRepository;

    @BeforeEach
    public void setup(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6003296_testInsertOK(){

        ElectricType electricType = new ElectricType();
        electricType.setName("พัดลม");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        ElectricType found = electricTypeRepository.findById(electricType.getId());
        assertEquals("พัดลม", found.getName());

    }

    @Test
    void b6003296_testNameMustNotBeNull(){

        ElectricType electricType = new ElectricType();
        electricType.setName(null);

        Set<ConstraintViolation<ElectricType>> result = validator.validate(electricType);


        assertEquals(1, result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("name",result.iterator().next().getPropertyPath().toString());


    }
}
