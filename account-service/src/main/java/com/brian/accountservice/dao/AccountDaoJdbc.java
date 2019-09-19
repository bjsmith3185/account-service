package com.brian.accountservice.dao;


import com.brian.accountservice.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountDaoJdbc implements AccountDao {


    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_ACCOUNT_SQL =
            "insert into account (balance, customer_id) values (?, ?)";

    private static final String SELECT_ACCOUNT_SQL =
            "select * from account where account_id = ?";

    private static final String SELECT_ALL_ACCOUNTS_SQL =
            "select * from account";

    private static final String UPDATE_ACCOUNT_SQL =
            "update account set balance = ?, customer_id = ? where account_id = ?";

    private static final String DELETE_ACCOUNT =
            "delete from account where account_id = ?";

    private static final String SELECT_ACCOUNT_BY_CUSTOMER_SQL =
            "select * from account where customer_id = ?";


    @Autowired
    public AccountDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    @Transactional
    public Account addAccount(Account account) {
        jdbcTemplate.update(
                INSERT_ACCOUNT_SQL,
                account.getBalance(),
                account.getCustomerId()
                 );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        account.setAccountId(id);

        return account;
    }

    @Override
    public Account getAccount(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_ACCOUNT_SQL, this::mapRowToAccount, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this
            return null;
        }
    }



    @Override
    public List<Account> getAllAccounts() {
        return jdbcTemplate.query(SELECT_ALL_ACCOUNTS_SQL, this::mapRowToAccount);
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update(
                UPDATE_ACCOUNT_SQL,
                account.getBalance(),
                account.getCustomerId(),
                account.getAccountId());
    }

    @Override
    public void deleteAccount(int id) {
        jdbcTemplate.update(DELETE_ACCOUNT, id);
    }

    @Override
    public List<Account> getAccountByCustomer(int id) {
        return jdbcTemplate.query(SELECT_ACCOUNT_BY_CUSTOMER_SQL, this::mapRowToAccount, id);
    }

    private Account mapRowToAccount(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setBalance(rs.getInt("balance"));
        account.setCustomerId(rs.getInt("customer_id"));
        return account;
    }


}
