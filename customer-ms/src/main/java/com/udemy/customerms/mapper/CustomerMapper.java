package com.udemy.customerms.mapper;

import com.udemy.customerms.domain.Customer;
import com.udemy.customerms.domain.dto.CustomerRequestDTO;
import com.udemy.customerms.domain.dto.CustomerResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer map(CustomerRequestDTO requestDTO);

    CustomerResponseDTO toDTO(Customer entity);

    List<CustomerResponseDTO> toListDTO(List<Customer> entities);
}