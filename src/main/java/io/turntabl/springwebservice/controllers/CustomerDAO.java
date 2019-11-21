package io.turntabl.springwebservice.controllers;

import io.turntabl.springwebservice.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CustomerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    List<Customer> getAllCustomers(){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers WHERE active = 'yes'",
                BeanPropertyRowMapper.newInstance(Customer.class));
        return customers;
    }

    List<Customer> getCustomerByName(String name){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers WHERE active = 'yes' AND LOWER(name) LIKE ?" ,
                new Object []{ "%" + name.toLowerCase() + "%"},
                BeanPropertyRowMapper.newInstance(Customer.class));
        return customers;
    }

    Customer getCustomerById(Long id){
        Customer customers = jdbcTemplate.queryForObject("SELECT * FROM customers WHERE active = 'yes' AND id = ?" ,
                new Object []{ id },
                BeanPropertyRowMapper.newInstance(Customer.class));
        return customers;
    }

    Customer addNewCustomer(Customer customer){
         int status = jdbcTemplate.update("INSERT INTO customers( name, address, telephoneNumber, email) VALUES ( ?, ?, ?, ?)",
                customer.getName(), customer.getAddress(), customer.getTelephoneNumber(), customer.getEmail());
        return customer;
    }

    Customer updateCustomer(Customer customerById) {
        int status = jdbcTemplate.update("UPDATE customers SET name = ?, address = ?, telephoneNumber = ?, email = ? " +
                " WHERE active = 'yes' AND id = ? ",
                customerById.getName(), customerById.getAddress(), customerById.getTelephoneNumber(),
                customerById.getEmail(), customerById.getId());
        return customerById;
    }

     Customer deleteCustomer(long id) {
        Customer customer = getCustomerById(id);
        jdbcTemplate.update("UPDATE customers SET active = 'no' WHERE active = 'yes' AND id = ?", id);
        return customer;
    }

     Customer retrieveDeletedCustomer(long id) {
        Customer customer = getCustomerById(id);
        jdbcTemplate.update("UPDATE customers SET active = 'yes' WHERE active = 'no' AND id = ?", id);
        return customer;
    }
}
