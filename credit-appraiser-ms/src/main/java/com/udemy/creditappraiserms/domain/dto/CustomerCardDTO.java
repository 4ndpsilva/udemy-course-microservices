package com.udemy.creditappraiserms.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerCardDTO {
    private String name;
    private String flag;
    private BigDecimal freeLimit;
}