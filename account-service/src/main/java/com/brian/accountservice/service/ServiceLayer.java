package com.brian.accountservice.service;


import com.brian.accountservice.dao.AccountDao;
import com.brian.accountservice.dto.Account;
import com.brian.accountservice.exception.AccountError;
import com.brian.accountservice.viewModels.AccountView;
import com.brian.accountservice.viewModels.CreditView;
import com.brian.accountservice.viewModels.WithdrawView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    AccountDao accountDao;

    @Autowired
    public ServiceLayer(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    // credit account
    public CreditView creditAccount(CreditView creditView) {

        Account account = accountDao.getAccount(creditView.getAccountId());

        int newBalance = account.getBalance() + creditView.getCredit();

        creditView.setBalance(newBalance);
        creditView.setCustomerId(account.getCustomerId());
        // update new balance in db
        account.setBalance(newBalance);
        accountDao.updateAccount(account);


        return creditView;
    }


    // withdraw from account
    public WithdrawView withdrawFromAccount(WithdrawView withdrawView) {

        Account account = accountDao.getAccount(withdrawView.getAccountId());

        if ( account.getBalance() >= withdrawView.getWithdraw() ) {
            int newBalance =  account.getBalance() - withdrawView.getWithdraw();
            withdrawView.setBalance(newBalance);
            withdrawView.setCustomerId(account.getCustomerId());

            // update new balance in db
            account.setBalance(newBalance);
            accountDao.updateAccount(account);
            return withdrawView;
        }
        throw new AccountError("Amount requested excedes the current balance");
//        return null;
    }

    // get balance
    public AccountView getBalance(int id) {
        return toAccountView(accountDao.getAccount(id));
    }

    // get balance by customer
    public List<AccountView> getBalanceByCustomer(int id) {
        List<AccountView> returnList = new ArrayList<>();

        List<Account> accountList = accountDao.getAccountByCustomer(id);

        for (Account each: accountList) {
            returnList.add(toAccountView(each));
        }

        return returnList;
    }

    // create account
    public AccountView createNewAccount(AccountView accountView) {
        if ( accountView.getAccountId() <=0  && accountView.getCustomerId() >=0) {
            return toAccountView(accountDao.addAccount(toAccount(accountView)));
        }
        throw new AccountError("no account id required or no customer id included");

    }

    // delete account
    public String deleteAccount(int id) {
        accountDao.deleteAccount(id);
        return "account # " + id + " successfully deleted";
    }


    // get all accounts
    public List<AccountView> getAllAccounts() {
        List<AccountView> accountList = new ArrayList<>();

        for ( Account each: accountDao.getAllAccounts() ) {
            accountList.add(toAccountView(each));
        }

        return accountList;
    }





    // object conversion methods

    // AccountView to Account
    public Account toAccount( AccountView accountView ) {
        Account account = new Account();
        account.setBalance(accountView.getBalance());
        account.setCustomerId(accountView.getCustomerId());
        return account;
    }

    // Account to AccountView
    public AccountView toAccountView ( Account account ) {
        AccountView accountView = new AccountView();
        accountView.setAccountId(account.getAccountId());
        accountView.setBalance(account.getBalance());
        accountView.setCustomerId(account.getCustomerId());
        return accountView;
    }







}
