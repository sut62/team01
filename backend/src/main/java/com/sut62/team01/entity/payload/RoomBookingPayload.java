package com.sut62.team01.entity.payload;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * RoomBookingPayload
 */
@Data
public class RoomBookingPayload {

    
    private Long student_id;

    
    private Long bed_id;

    
    private Long room_id;


    private String email;
}