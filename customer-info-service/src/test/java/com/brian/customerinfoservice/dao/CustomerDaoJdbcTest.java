package com.brian.customerinfoservice.dao;

import com.brian.customerinfoservice.dto.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoJdbcTest {


    @Autowired
    CustomerDao customerDao;


    @Before
    public void setUp() throws Exception {

        List<Customer> invoiceItems = customerDao.getAllCustomers();
        for (Customer each : invoiceItems) {
            customerDao.deleteCustomer(each.getCustomerId());
        }

    }

    @Test
    public void addGetCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("brian");
        customer.setLastName("smith");

        customer = customerDao.addCustomer(customer);

        Customer fromService = customerDao.getCustomer(customer.getCustomerId());

        assertEquals(customer, fromService);


    }


    @Test
    public void getAllCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("brian");
        customer.setLastName("smith");
        customerDao.addCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setFirstName("tom");
        customer1.setLastName("petty");
        customerDao.addCustomer(customer);

        List<Customer> fromService = customerDao.getAllCustomers();

        assertEquals(2, fromService.size());


    }












}