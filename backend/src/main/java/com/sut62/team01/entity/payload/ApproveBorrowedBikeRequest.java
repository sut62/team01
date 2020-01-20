package com.sut62.team01.entity.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ApproveBorrowedBikeRequest {

    @NotBlank
    private Long borrowedBikeId;

    @NotBlank
    private Long bikeId;

    @NotBlank
    private Long staffId;
}
