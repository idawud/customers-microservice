package io.turntabl.springwebservice.controllers;

import io.turntabl.springwebservice.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CustomerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers(){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers",
                BeanPropertyRowMapper.newInstance(Customer.class));
        return customers;
    }

    public List<Customer> getCustomerByName(String name){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers WHERE LOWER(name) LIKE ?" ,
                new Object []{ "%" + name.toLowerCase() + "%"},
                BeanPropertyRowMapper.newInstance(Customer.class));
        return customers;
    }
}
