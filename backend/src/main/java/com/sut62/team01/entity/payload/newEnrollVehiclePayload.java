package com.sut62.team01.entity.payload;

import lombok.Data;

@Data
public class newEnrollVehiclePayload {

    // private Data enrollDate;

    private String licensePlate;

    private String brandName;

    private String otherDetails;

    private Long vehicleTypeId;

    private Long staffId;

    private Long roomBookingId;
}