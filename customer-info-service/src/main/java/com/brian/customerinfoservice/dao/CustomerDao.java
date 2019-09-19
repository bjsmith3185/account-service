package com.brian.customerinfoservice.dao;



import com.brian.customerinfoservice.dto.Customer;

import java.util.List;

public interface CustomerDao {

    Customer addCustomer(Customer customer);

    Customer getCustomer(int id);

    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(int id);


}
