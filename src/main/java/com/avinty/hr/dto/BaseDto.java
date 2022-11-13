package com.avinty.hr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdAt;

    protected Integer createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updatedAt;

    protected Integer updatedBy;
}
