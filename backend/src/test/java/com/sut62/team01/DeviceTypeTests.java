package com.sut62.team01;

import com.sut62.team01.entity.DeviceType;
import com.sut62.team01.repository.DeviceTypeRepository;
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
public class DeviceTypeTests {

    private Validator validator;

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6004897_testInsertOK() {

        DeviceType deviceType = new DeviceType();
        deviceType.setType("อุปกรณ์ไฟฟ้า");
        deviceType = deviceTypeRepository.saveAndFlush(deviceType);
        Optional<DeviceType> found = Optional.ofNullable(deviceTypeRepository.findById(deviceType.getId()));
        assertEquals("อุปกรณ์ไฟฟ้า", found.get().getType());

    }

    @Test
    void b6004897_testDeviceTypeMustNotBeNull() {

        DeviceType deviceType = new DeviceType();
        deviceType.setType(null);

        Set<ConstraintViolation<DeviceType>> result = validator.validate(deviceType);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("type", result.iterator().next().getPropertyPath().toString());

    }
}
