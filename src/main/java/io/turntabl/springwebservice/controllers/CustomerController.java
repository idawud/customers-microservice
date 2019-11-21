package io.turntabl.springwebservice.controllers;

import com.fasterxml.jackson.databind.BeanProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.springwebservice.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
public class CustomerController {
    @Autowired
    private CustomerDAO dao;

    @ApiOperation("Get all customers in record")
    @GetMapping("/customer")
    public List<Customer> getCustomer(){
        return dao.getAllCustomers();
    }

    @ApiOperation("get customers by name")
    @GetMapping("/customer/search/name")
    public List<Customer> getCustomerByName(
            @RequestParam(name = "name", defaultValue = "")
            String name
    ){
        return dao.getCustomerByName(name);
    }

    @ApiOperation("get customers by id")
    @GetMapping("/customer/search/id")
    public Customer getCustomerById(
            @RequestParam(name = "name", defaultValue = "")
                    Integer id
    ){
        return dao.getCustomerById(id);
    }

    @ApiOperation()
}
