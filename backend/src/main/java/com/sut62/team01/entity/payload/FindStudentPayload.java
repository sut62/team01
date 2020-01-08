package com.sut62.team01.entity.payload;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * FindStudentPayload
 */
@Data
public class FindStudentPayload {

    @NotNull
    private Long student_id;

}