package com.example.CRUD_spring.controller;

import com.example.CRUD_spring.entity.Customer;
import com.example.CRUD_spring.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class customerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public Customer postCustomer(@RequestBody Customer customer){
        return customerService.postCustomer(customer);
    }

    @GetMapping("/customers")
    private List<Customer> getAllCustomer(){
        return customerService.getAllCutomer();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);

        if(customer == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        Customer existingCustomer = customerService.getCustomerById(id);

        if(existingCustomer == null)
            return ResponseEntity.notFound().build();

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        Customer updateCustomer = customerService.updateCustomer(existingCustomer);
        return ResponseEntity.ok(updateCustomer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        Customer existingCustomer = customerService.getCustomerById(id);
        if(existingCustomer == null)
            return ResponseEntity.notFound().build();

        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
