package com.sut62.team01.entity.payload;

import lombok.Data;

@Data
public class PackageManagementPayload {

    private String details;

    private Long roomBookingId;

    private Long packageTypeId;

    private Long staffId;

}
