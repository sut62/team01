package com.sut62.team01;

import com.sut62.team01.entity.PackageType;
import com.sut62.team01.repository.PackageTypeRepository;
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
public class PackageTypeTest {

    private Validator validator;

    @Autowired
    private PackageTypeRepository packageTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6018474_testInsertOK() {

        PackageType packageType = new PackageType();
        packageType.setPackageType("กล่องพัสดุ");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Optional<PackageType> found = Optional.ofNullable(packageTypeRepository.findById(packageType.getId()));
        assertEquals("กล่องพัสดุ", found.get().getPackageType());

    }

    @Test
    void b6018474_testPackageTypeMustNotBeNull() {

        PackageType packageType = new PackageType();
        packageType.setPackageType(null);

        Set<ConstraintViolation<PackageType>> result = validator.validate(packageType);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("packageType", result.iterator().next().getPropertyPath().toString());

    }
}
