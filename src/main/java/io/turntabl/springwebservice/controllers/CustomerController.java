package io.turntabl.springwebservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.springwebservice.models.Customer;
import io.turntabl.springwebservice.pubsub.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
public class CustomerController {
    @Autowired
    private CustomerDAO dao;

    @Autowired
    private Publisher redisMessagePublisher;

    @ApiOperation("Get all customers in record")
    @GetMapping("/customer")
    public List<Customer> getCustomer(){
        return dao.getAllCustomers();
    }

    @ApiOperation("get customers by name")
    @GetMapping("/customer/search")
    public List<Customer> getCustomerByName(
            @RequestParam(name = "name", defaultValue = "")
            String name
    ){
        return dao.getCustomerByName(name);
    }

    @ApiOperation("get customers by id")
    @GetMapping("/customer/search/{id}")
    public Customer getCustomerById(
            @PathVariable("id") long id
    ){
        return dao.getCustomerById(id);
    }

    @ApiOperation("add new customer")
    @PostMapping(value = "/customer/create", consumes = "application/json", produces = "application/json")
    public Customer addNewCustomer(
            @RequestBody Customer customer
    ){
        redisMessagePublisher.publish(customer);
        return dao.addNewCustomer(customer);
    }

    @ApiOperation("update record of an existing customer")
    @PutMapping(value = "/customer/update/{id}", consumes = "application/json", produces = "application/json")
    public Customer updateCustomer(
            @PathVariable("id") long id,
            @RequestBody Customer customer
    ){
        Customer customerById = dao.getCustomerById(id);

        customerById.setEmail(customer.getEmail());
        customerById.setAddress(customer.getAddress());
        customerById.setTelephoneNumber(customer.getTelephoneNumber());
        customerById.setName(customer.getName());

        return dao.updateCustomer(customerById);
    }

    @ApiOperation("delete record of an existing customer")
    @DeleteMapping(value = "/customer/delete/{id}")
    public Customer deleteCustomer(
            @PathVariable("id") long id
    ){
        return dao.deleteCustomer(id);
    }

    @ApiOperation("delete record of an existing customer")
    @PutMapping(value = "/customer/retrieve/{id}")
    public Customer retrieveCustomer(
            @PathVariable("id") long id
    ){
        return dao.retrieveDeletedCustomer(id);
    }
}
