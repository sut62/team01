package com.sut62.team01;

import com.sut62.team01.entity.Person;
import com.sut62.team01.entity.Student;
import com.sut62.team01.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class PersonTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testPersonIdOKMatch13Digits(){
        Person person = new Person();
        person.setPersonId("1234567890123");

        person = personRepository.saveAndFlush(person);

        Optional<Person> found = personRepository.findById(person.getId());
        assertEquals("1234567890123",found.get().getPersonId());

    }


    @Test
    void testPersonIdMustNotBeNull() {
        Person person = new Person();
        person.setPersonId(null);

        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("personId", v.getPropertyPath().toString());
    }

    @Test
    void testPersonIdMustNotBe12Digits() {
        Person person = new Person();
        person.setPersonId("123456789012"); // 12 digits

        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("personId", v.getPropertyPath().toString());

    }


    @Test
    void testPersonIdMustNotBe14Digits() {
        Person person = new Person();
        person.setPersonId("12345678901234"); // 14 digits

        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("personId", v.getPropertyPath().toString());
    }

    @Test
    void testPersonIdMustBeUnique() {
        // สร้าง person object
        Person p1 = new Person();
        p1.setPersonId("1234567890123");
        personRepository.saveAndFlush(p1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง person object ตัวที่ 2
            Person p2 = new Person();
            p2.setPersonId("1234567890123");
            personRepository.saveAndFlush(p2);
        });
    }
}
