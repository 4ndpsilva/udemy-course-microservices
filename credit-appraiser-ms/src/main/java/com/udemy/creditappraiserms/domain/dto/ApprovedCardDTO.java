package com.udemy.creditappraiserms.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCardDTO {
    private String card;
    private String flag;
    private BigDecimal limit;
}