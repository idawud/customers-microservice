package io.turntabl.springwebservice.Services;

import io.turntabl.springwebservice.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers(){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers WHERE active = 'yes'",
                BeanPropertyRowMapper.newInstance(Customer.class));
        if ( customers.size() > 0) {
            return customers;
        }
        return new ArrayList<>();
    }

    public List<Customer> getCustomerByName(String name){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers WHERE active = 'yes' AND LOWER(name) LIKE ?" ,
                new Object []{ "%" + name.toLowerCase() + "%"},
                BeanPropertyRowMapper.newInstance(Customer.class));
        if ( customers.size() > 0) {
            return customers;
        }
        return new ArrayList<>();
    }

    public Customer getCustomerById(Long id){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers WHERE active = 'yes' AND id = ?" ,
                new Object []{ id },
                BeanPropertyRowMapper.newInstance(Customer.class));
        if ( customers.size() > 0){
            return customers.get(0);
        }
        return new Customer();
    }

    public Customer addNewCustomer(Customer customer){
         int status = jdbcTemplate.update("INSERT INTO customers( name, address, telephoneNumber, email) VALUES ( ?, ?, ?, ?)",
                customer.getName(), customer.getAddress(), customer.getTelephoneNumber(), customer.getEmail());
        return customer;
    }

    public Customer updateCustomer(Customer customerById) {
        int status = jdbcTemplate.update("UPDATE customers SET name = ?, address = ?, telephoneNumber = ?, email = ? " +
                " WHERE active = 'yes' AND id = ? ",
                customerById.getName(), customerById.getAddress(), customerById.getTelephoneNumber(),
                customerById.getEmail(), customerById.getId());
        return customerById;
    }

     public Customer deleteCustomer(long id) {
        Customer customer = getCustomerById(id);
        jdbcTemplate.update("UPDATE customers SET active = 'no' WHERE active = 'yes' AND id = ?", id);
        return customer;
    }

     public Customer retrieveDeletedCustomer(long id) {
         List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers WHERE active = 'no' AND id = ?" ,
                 new Object []{ id },
                 BeanPropertyRowMapper.newInstance(Customer.class));
         if ( customers.size() > 0){
             jdbcTemplate.update("UPDATE customers SET active = 'yes' WHERE active = 'no' AND id = ?", id);
             return customers.get(0);
         }
         return new Customer();
    }

    public List<Customer> getDeletedCustomerByName(String name) {
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers WHERE active = 'no' AND LOWER(name) LIKE ?" ,
                new Object []{ "%" + name.toLowerCase() + "%"},
                BeanPropertyRowMapper.newInstance(Customer.class));
        if ( customers.size() > 0) {
            return customers;
        }
        return new ArrayList<>();
    }
}