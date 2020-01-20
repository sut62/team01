package com.sut62.team01;

import com.sut62.team01.entity.DeviceName;
import com.sut62.team01.repository.DeviceNameRepository;
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

public class DeviceNameTests {

    private Validator validator;

    @Autowired
    private DeviceNameRepository deviceNameRepository;

    @BeforeEach
    public void setup(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test

    void b6004897_testinsertOK(){
            DeviceName deviceName = new DeviceName();
            deviceName.setName("หลอดไฟ");
            deviceName = deviceNameRepository.saveAndFlush(deviceName);
            Optional<DeviceName> found = Optional.ofNullable(deviceNameRepository.findById(deviceName.getId()));
            assertEquals("หลอดไฟ", found.get().getName());
    }

    @Test
    void b6004897_testNameMustNotBeNull(){

        DeviceName deviceName = new DeviceName();
        deviceName.setName(null);

        Set<ConstraintViolation<DeviceName>> result = validator.validate(deviceName);


        assertEquals(1, result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("name",result.iterator().next().getPropertyPath().toString());


    }
}