package com.sut62.team01.entity.payload;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * RoomBookingPayload
 */
@Data
public class RoomBookingPayload {

    @NotNull
    private Long student_id;

    @NotNull
    private Long branche_id;

    @NotNull
    private Long room_id;
}