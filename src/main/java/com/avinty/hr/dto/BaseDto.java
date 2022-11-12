package com.avinty.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto implements Serializable {

    protected int id;

    protected LocalDateTime createdAt;

    protected Integer createdBy;

    protected LocalDateTime updatedAt;

    protected Integer updatedBy;
}
