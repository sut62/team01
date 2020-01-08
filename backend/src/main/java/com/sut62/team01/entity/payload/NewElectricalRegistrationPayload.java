package com.SUTDominatory.Entity.Payload;

import lombok.Data;

@Data
public class NewElectricalRegistrationPayload {
    
    private String details;
    
    private Long roomBookingId;

    private Long electricTypeId;

    private Long staffId;
    
}

