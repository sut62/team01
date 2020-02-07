package com.sut62.team01.entity.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ConfirmPackagePayload {
    @NotBlank
    private Long packageManagementId;
    @NotBlank
    private Long staffId;

}
