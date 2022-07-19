package com.udemy.creditappraiserms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEvaluationReturnDTO {
    private List<ApprovedCardDTO> cards;
}