package com.brian.accountservice.dao;

import com.brian.accountservice.dto.Account;


import java.util.List;

public interface AccountDao {

    Account addAccount(Account account);

    Account getAccount(int id);

    List<Account> getAllAccounts();

    void updateAccount(Account account);

    void deleteAccount(int id);

    List<Account> getAccountByCustomer(int id);

}
