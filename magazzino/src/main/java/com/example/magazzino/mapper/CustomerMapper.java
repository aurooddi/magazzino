package com.example.magazzino.mapper;

import com.example.magazzino.model.dto.CustomerDto;
import com.example.magazzino.model.entity.CustomerEntity;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public static CustomerDto mapCustomerToDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setIdCustomer(customerEntity.getId());
        customerDto.setFullName(customerEntity.getFullName());
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setAddress(customerEntity.getAddress());
        return customerDto;
    }

    public static CustomerEntity mapCustomerToEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerDto.getIdCustomer());
        customerEntity.setFullName(customerDto.getFullName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setAddress(customerDto.getAddress());
        return customerEntity;
    }

    public static List<CustomerDto> mapCustomerToDto(List<CustomerEntity> customerEntity) {
        List<CustomerDto> customerDtoList = new ArrayList<>();

        for(CustomerEntity customer : customerEntity) {

            CustomerDto customerDto = new CustomerDto();
            customerDto = mapCustomerToDto(customer);
            customerDtoList.add(customerDto);
        }

        return customerDtoList;
    }

    public static List<CustomerEntity> mapCustomerToEntity(List<CustomerDto> customerDto) {
        List<CustomerEntity> customerEntityList = new ArrayList<>();

        for(CustomerDto customer : customerDto) {

            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity = mapCustomerToEntity(customer);
            customerEntityList.add(customerEntity);
        }
        return customerEntityList;
    }


}
