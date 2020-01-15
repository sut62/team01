package com.sut62.team01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.*;

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

    @Test
    void b6000783_testTypeMustBeGreaterEqual5(){
        DateType dateType1 = new DateType();
        dateType1.setType("1234");
        Set<ConstraintViolation<DateType>> result = validator.validate(dateType1);
        assertEquals(1,result.size());
        assertEquals("size must be between 5 and 20",result.iterator().next().getMessage());
        assertEquals("type",result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6000783_testTypeMustBeLessEqual20(){
        DateType dateType1 = new DateType();
        dateType1.setType("123456789012345678901");
        Set<ConstraintViolation<DateType>> result = validator.validate(dateType1);
        assertEquals(1,result.size());
        assertEquals("size must be between 5 and 20",result.iterator().next().getMessage());
        assertEquals("type",result.iterator().next().getPropertyPath().toString());
    }


}