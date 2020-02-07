package com.sut62.team01.entity.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class newEnrollVehiclePayload {
    @NotBlank
    private String licensePlate;

    @NotBlank
    private String brandName;

    @NotBlank
    private String otherDetails;

    @NotBlank
    private Long vehicleTypeId;

    @NotBlank
    private Long staffId;
    
    @NotBlank
    private Long roomBookingId;
}