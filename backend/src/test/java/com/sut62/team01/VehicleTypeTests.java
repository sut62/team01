package com.sut62.team01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team01.entity.VehicleType;
import com.sut62.team01.repository.VehicleTypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class VehicleTypeTests {
    private Validator validator;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6025502_testInsertDatatoVehicleTypeIsWork() {
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);

        VehicleType found = vehicleTypeRepository.findById(v_type.getId());
        assertEquals("รถยนต์", found.getType());
    }

    @Test
    void b6025502_testTypeMustNotBeNull() {
        VehicleType v_type = new VehicleType();
        v_type.setType(null);

        Set<ConstraintViolation<VehicleType>> result = validator.validate(v_type);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<VehicleType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }
}