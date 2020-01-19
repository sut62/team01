package com.sut62.team01;

import com.sut62.team01.entity.ElectricType;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.repository.StaffRepository;
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
public class StaffTests{

    private Validator validator ;

    @Autowired
    private StaffRepository staffRepository;

    @BeforeEach
    public void setup(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6000783_testInsertStaffOK() {
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);
        Optional<Staff> found = Optional.ofNullable(staffRepository.findById(staff.getId()));
        assertEquals(staff.getName(), found.get().getName());
        assertEquals(staff.getUsername(), found.get().getUsername());
        assertEquals(staff.getPassword(), found.get().getPassword());
    }

    @Test
    void b6003296_testNameMustnotBeNull(){

        Staff staff = new Staff(null,"asdas","qwe");

        Set<ConstraintViolation<Staff>> result = validator.validate(staff);


        assertEquals(1, result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("name",result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6003296_testUserNameMustnotBeNull(){

        Staff staff = new Staff("asdas",null,"qwe");

        Set<ConstraintViolation<Staff>> result = validator.validate(staff);


        assertEquals(1, result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("username",result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6003296_testPassWordMustnotBeNull(){

        Staff staff = new Staff("asdas","asdas",null);

        Set<ConstraintViolation<Staff>> result = validator.validate(staff);


        assertEquals(1, result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("password",result.iterator().next().getPropertyPath().toString());

    }

}
