package com.sut62.team01.entity.payload;

import lombok.Data;

@Data
public class NewElectricalRegistrationPayload {
    
    private String details;
    
    private Long roomBookingId;

    private Long electricTypeId;

    private Long staffId;
    
}

