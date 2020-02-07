package com.sut62.team01.entity.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * RoomBookingPayload
 */
@Data
public class RoomBookingPayload {

    @NotBlank
    private Long student_id;

    @NotBlank
    private Long bed_id;

    @NotBlank
    private Long room_id;

    @NotBlank
    private String email;
}