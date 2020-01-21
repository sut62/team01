package com.sut62.team01.entity.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * BikePayload
 */
@Data
public class BikePayload {

    @NotBlank
    private Long bikeTypeId;
}