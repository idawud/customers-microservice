package io.turntabl.springwebservice.controllers;

import com.fasterxml.jackson.databind.BeanProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.springwebservice.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
public class CustomerController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ApiOperation("Get all customers in record")
    @GetMapping("/customer")
    public List<Customer> getCustomer(){
        List<Customer> query = jdbcTemplate.query("select * from customers",
                BeanPropertyRowMapper.newInstance(Customer.class));
        return query;
    }
}
