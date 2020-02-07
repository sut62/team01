package com.sut62.team01.entity.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PackageManagementPayload {
    @NotBlank
    private String details;
    @NotBlank
    private Long roomBookingId;
    @NotBlank
    private Long packageTypeId;
    @NotBlank
    private Long staffId;

}
