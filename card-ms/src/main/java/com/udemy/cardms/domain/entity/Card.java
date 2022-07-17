package com.udemy.cardms.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_CARD")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FLAG")
    @Enumerated(EnumType.STRING)
    private Flag flag;

    @Column(name = "INCOME")
    private BigDecimal income;

    @Column(name = "BASIC_LIMIT")
    private BigDecimal basicLimit;

    public Card(String name, Flag flag, BigDecimal income, BigDecimal basicLimit){
        this.name = name;
        this.flag = flag;
        this.income = income;
        this.basicLimit = basicLimit;
    }
}