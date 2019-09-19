package com.brian.customerinfoservice.controller;


import com.brian.customerinfoservice.dao.CustomerDao;
import com.brian.customerinfoservice.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class CustomerController {

    @Autowired
    CustomerDao customerDao;


    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer newCustomer(@RequestBody Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable("id") int id) {
        return customerDao.getCustomer(id);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomers() {
        return customerDao.getAllCustomers();
    }


    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@RequestBody Customer customer) {
        customerDao.updateCustomer(customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id") int id) {
        customerDao.deleteCustomer(id);
    }

}
