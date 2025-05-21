package com.example.magazzino.service;

import com.example.magazzino.mapper.CustomerMapper;
import com.example.magazzino.model.dto.CustomerDto;
import com.example.magazzino.model.entity.CustomerEntity;
import com.example.magazzino.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // RIFATTA
    public CustomerDto create(CustomerDto customerDto) {
        CustomerEntity customer = CustomerMapper.mapCustomerToEntity(customerDto);

        Optional<CustomerEntity> customerEntityOptional = customerRepository
                .searchByEmail(customer.getEmail());
        if(customerEntityOptional.isPresent()) {
            throw new IllegalStateException("Email already exists");
        }
        return CustomerMapper.mapCustomerToDto(customerRepository.save(customer));
    }

    public void update(Long idCustomer, String fullName, String email, String address) {
        CustomerEntity customer = customerRepository.findById(idCustomer)
                .orElseThrow(() -> new IllegalStateException(
                        "Customer with id " + idCustomer + " does not exists")
                );
        if(fullName != null && !fullName.isEmpty() && !Objects.equals(customer.getFullName(), fullName))   {
            customer.setFullName(fullName);
        }

        if(email != null && !email.isEmpty() && !Objects.equals(customer.getEmail(), email)) {
            Optional<CustomerEntity> customerEntity = customerRepository.searchByEmail(email);
            if (customerEntity.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            customer.setEmail(email);
        }

        if(customer.getAddress() != null && !Objects.equals(customer.getAddress(), address)) {
            customer.setAddress(address);
        }
        customerRepository.save(customer);
    }

    public void delete(Long idCustomer) {
        boolean exists = customerRepository.existsById(idCustomer);
        if(!exists) {
            throw new IllegalStateException("Customer " + idCustomer + " does not exists");
        }
        customerRepository.deleteById(idCustomer);
    }

    public List<CustomerDto> getAll() {
        return CustomerMapper.mapCustomerToDto(customerRepository.findAll());
    }
}
