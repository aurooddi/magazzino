package com.example.magazzino.controller;

import com.example.magazzino.model.dto.CustomerDto;
import com.example.magazzino.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "magazzino/customer")
@Tag(name = "Customer API", description = "Gestione degli utenti")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Crea l'utente", description = "Metedo che crea un nuovo cliente")
    public CustomerDto registerNewCustomer (@RequestBody CustomerDto customer) {
        return customerService.create(customer);
    }

    @DeleteMapping(path = "{idCustomer}")
    @Operation(summary = "Elimina utente", description = "Metodo che elimina un utente")
    public void deleteCustomer(@PathVariable("idCustomer") Long idCustomer) {
        customerService.delete(idCustomer);
    }

    @PutMapping(path = "{idCustomer}")
    @Operation(summary = "Aggiorna i clienti", description = "Metodo che aggiorna un cliente")
    public void updateCustomer(
            @PathVariable("idCustomer") Long idCustomer,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address) {
        customerService.update(idCustomer, fullName, email, address);
    }

    @GetMapping
    @Operation(summary = "Lista completa", description = "Ritorna la lista di tutti i clienti")
    public List<CustomerDto> getAllCustomers() {
       return customerService.getAll();
    }

}
