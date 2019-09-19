package com.brian.frontservice.feign;

import com.brian.frontservice.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customer-info-service")
public interface CustomerServiceClient {


    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Customer newCustomer(@RequestBody Customer customer);

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("id") int id);

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers();

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public void updateCustomer(@RequestBody Customer customer);

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable("id") int id);


}

