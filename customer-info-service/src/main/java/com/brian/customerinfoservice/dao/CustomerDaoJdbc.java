package com.brian.customerinfoservice.dao;


import com.brian.customerinfoservice.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoJdbc implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_CUSTOMER_SQL =
            "insert into customer (first_name, last_name) values (?,?)";

    private static final String SELECT_CUSTOMER_SQL =
            "select * from customer where customer_id = ?";

    private static final String SELECT_ALL_CUSTOMERS_SQL =
            "select * from customer";

    private static final String UPDATE_CUSTOMER_SQL =
            "update customer set first_name = ?, last_name = ? where customer_id = ?";

    private static final String DELETE_CUSTOMER =
            "delete from customer where customer_id = ?";

    @Autowired
    public CustomerDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }





    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        jdbcTemplate.update(
                INSERT_CUSTOMER_SQL,
                customer.getFirstName(),
                customer.getLastName()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        customer.setCustomerId(id);

        return customer;
    }

    @Override
    public Customer getCustomer(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_SQL, this::mapRowToCustomer, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS_SQL, this::mapRowToCustomer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(
                UPDATE_CUSTOMER_SQL,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getCustomerId()
                );
    }

    @Override
    public void deleteCustomer(int id) {
        jdbcTemplate.update(DELETE_CUSTOMER, id);
    }



    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
         return customer;
    }
}
