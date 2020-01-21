package com.sut62.team01;

import com.sut62.team01.entity.Students;
import com.sut62.team01.repository.StudentsRepository;

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
public class StudentsTest {

    private Validator validator;

    @Autowired
    private StudentsRepository studentsRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6010201_testInsertDataOK() {

        Students students = new Students("นายปกรณ์ หาญนิโรจน์รัมย์","B6010201","france060","1234");
        // students.setFullName("นายปกรณ์ หาญนิโรจน์รัมย์");
        // students.setStudentId("B6010201");
        // students.setUsername("france060");
        // students.setPassword("1234");
        students = studentsRepository.saveAndFlush(students);
        Optional<Students> found = studentsRepository.findById(students.getId());
        assertEquals(students , found.get());

    }

    @Test
    void b6010201_testfullNameMustNotBeNull() {

        Students students = new Students(null,"B6010201","france060","1234");

        Set<ConstraintViolation<Students>> result = validator.validate(students);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("fullName", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6010201_testStudentIdMustNotBeNull() {

        Students students = new Students("นายปกรณ์ หาญนิโรจน์รัมย์",null,"france060","1234");

        Set<ConstraintViolation<Students>> result = validator.validate(students);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("studentId", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6010201_testusernameMustNotBeNull() {

        Students students = new Students("นายปกรณ์ หาญนิโรจน์รัมย์","B6010201",null,"1234");

        Set<ConstraintViolation<Students>> result = validator.validate(students);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("username", result.iterator().next().getPropertyPath().toString());

    }
    @Test
    void b6018474_testpasswordMustNotBeNull() {

        Students students = new Students("นายปกรณ์ หาญนิโรจน์รัมย์","B6010201","france060",null);

        Set<ConstraintViolation<Students>> result = validator.validate(students);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("password", result.iterator().next().getPropertyPath().toString());

    }
}
