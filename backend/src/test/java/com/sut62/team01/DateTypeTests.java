package com.sut62.team01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team01.entity.DateType;
import com.sut62.team01.repository.DateTypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * DateTypeTests
 */
@DataJpaTest
public class DateTypeTests {

    private Validator validator;

    @Autowired
    private DateTypeRepository dateTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6000783_testInsertDataOk() {
        // สร้าง
        DateType dateType = new DateType();
        // เซ็ตค่าต่างๆ
        dateType.setType("ยืม 1 วัน");
        dateType = dateTypeRepository.saveAndFlush(dateType);

        Optional<DateType> found = dateTypeRepository.findById(dateType.getId());
        assertEquals(dateType, found.get());

    }

    @Test
    void b6000783_testTypeMustNotBeNull(){
        // สร้าง
        DateType dateType = new DateType();
        // ลองใส่ null
        dateType.setType(null);
        Set<ConstraintViolation<DateType>> result = validator.validate(dateType);

        // ต้องมี 1 error
        assertEquals(1,result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("type",result.iterator().next().getPropertyPath().toString());
    }

}