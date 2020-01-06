package com.din.dinentity.entity.payload;

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
}