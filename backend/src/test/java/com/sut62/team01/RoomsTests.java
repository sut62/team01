package com.sut62.team01;


import com.sut62.team01.entity.Rooms;
import com.sut62.team01.repository.RoomsRepository;

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
public class RoomsTests {

    private Validator validator;

    @Autowired
    private RoomsRepository roomsRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6010201_testInsertDataRoomsOK() {

        Rooms rooms = new Rooms("7127");
        rooms = roomsRepository.saveAndFlush(rooms);
        Optional<Rooms> found = roomsRepository.findById(rooms.getId());
        assertEquals(rooms , found.get());

    }

    @Test
    void b6010201_testRoomsMustNotBeNull() {

        Rooms rooms = new Rooms(null);

        Set<ConstraintViolation<Rooms>> result = validator.validate(rooms);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("roomId", result.iterator().next().getPropertyPath().toString());

    }
}
