package com.udemy.creditappraiserms.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomerCard {
    private String name;
    private String flag;
    private BigDecimal freeLimit;
}