package io.turntabl.springwebservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.springwebservice.Services.CustomerService;
import io.turntabl.springwebservice.models.Customer;
import io.turntabl.springwebservice.pubsub.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
public class CustomerController {
    @Autowired
    private CustomerService dao;

    @ApiOperation("Get all customers in record")
    @GetMapping("/customer")
    public List<Customer> getCustomer(){
        Publisher.publish("all customers [ACCESS]");
        return dao.getAllCustomers();
    }

    @ApiOperation("get customers by name")
    @GetMapping("/customer/search")
    public List<Customer> getCustomerByName(
            String name
    ){
        Publisher.publish(String.format("customer with name= %s [ACCESS]", name));
        return dao.getCustomerByName(name);
    }

    @ApiOperation("get customers by id")
    @GetMapping("/customer/{id}")
    public Customer getCustomerById(
            @PathVariable("id") long id
    ){
        Publisher.publish(String.format("customer with id= %s [ACCESS]", id));
        return dao.getCustomerById(id);
    }

    @ApiOperation("add new customer")
    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public Customer addNewCustomer(
            @RequestBody Customer customer
    ){
        Publisher.publish(String.format("customer with name= %s, email= %s added[UPDATE]", customer.getEmail(), customer.getName()));
        return dao.addNewCustomer(customer);
    }

    @ApiOperation("update record of an existing customer")
    @PutMapping(value = "/customer/{id}", consumes = "application/json", produces = "application/json")
    public Customer updateCustomer(
            @PathVariable("id") long id,
            @RequestBody Customer customer
    ){
        Customer customerById = dao.getCustomerById(id);

        customerById.setEmail(customer.getEmail());
        customerById.setAddress(customer.getAddress());
        customerById.setTelephoneNumber(customer.getTelephoneNumber());
        customerById.setName(customer.getName());

        Publisher.publish(String.format("customer with id= %d, name= %s, email= %s update[UPDATE]", id, customer.getEmail(), customer.getName()));
        return dao.updateCustomer(customerById);
    }

    @ApiOperation("delete record of an existing customer")
    @DeleteMapping(value = "/customer/{id}")
    public Customer deleteCustomer(
            @PathVariable("id") long id
    ){
        Publisher.publish(String.format("customer with id= %d deleted[DELETE]",id));
        return dao.deleteCustomer(id);
    }

    @ApiOperation("retrieve record of an deleted customer")
    @GetMapping(value = "/customer/retrieve/{id}")
    public Customer retrieveCustomer(
            @PathVariable("id") long id
    ){
        Publisher.publish(String.format("customer with id= %d deleted[RETRIEVE]",id));
        return dao.retrieveDeletedCustomer(id);
    }
}
