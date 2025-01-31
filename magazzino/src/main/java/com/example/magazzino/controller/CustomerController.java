package com.example.magazzino.controller;

import com.example.magazzino.model.dto.CustomerDto;
import com.example.magazzino.model.entity.CustomerEntity;
import com.example.magazzino.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "magazzino/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void registerNewCustomer (@RequestBody CustomerDto customer) {
        customerService.create(customer);
    }

    @DeleteMapping(path = "{idCustomer}")
    public void deleteCustomer(@PathVariable("idCustomer") Long idCustomer) {
        customerService.delete(idCustomer);
    }

    @PutMapping(path = "{idCustomer}")
    public void updateCustomer(
            @PathVariable("idCustomer") Long idCustomer,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address) {
        customerService.update(idCustomer, fullName, email, address);
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers() {
       return customerService.getAll();
    }

}
