package com.sut62.team01.entity.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * BorrowedBikeRequest
 */
@Data

public class BorrowedBikeRequest {

    @NotBlank
    private Long bikeType_id;

    @NotBlank
    private Long student_id;

    @NotBlank
    private Long dateType_id;
}