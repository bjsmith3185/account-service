package com.brian.frontservice.service;

import com.brian.frontservice.dto.Account;
import com.brian.frontservice.dto.Credit;
import com.brian.frontservice.dto.Customer;
import com.brian.frontservice.dto.Withdraw;
import com.brian.frontservice.feign.AccountServiceClient;
import com.brian.frontservice.feign.CustomerServiceClient;
import com.brian.frontservice.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private AccountServiceClient accountServiceClient;



    public Customer getCustomerById(int id) {

        return customerServiceClient.getCustomer(id);
    }


    public DepositResponseView makeDeposit(AccoutQueryView accoutQueryView) {

        // send request to account-service to adjust the balance on the account
        Credit credit = toCreditView(accoutQueryView);
        credit = accountServiceClient.creditAccount(credit);
        System.out.println();
        System.out.println();
        System.out.println(credit.toString());

        // create DepositResponseView object
        DepositResponseView depositResponseView = new DepositResponseView();

        // send request to customer-service to get the customers info
        depositResponseView.setCustomer(customerServiceClient.getCustomer(credit.getCustomerId()));
        // set transer data to depositResponseView
        depositResponseView.setCurrentBalance(credit.getBalance());
        depositResponseView.setDepositAmount(accoutQueryView.getTransactionAmount());
        depositResponseView.setAccountNumber(accoutQueryView.getAccountNumber());

        return depositResponseView;
    }

    public WithdrawResponseView makeWithdraw(AccoutQueryView accoutQueryView) {

        Withdraw withdraw = toWithdrawView(accoutQueryView);
        // send request to account-service to adjust the balance on the account
        withdraw = accountServiceClient.withdrawFromAccount(withdraw);

        // create WithdrawResponseView
        WithdrawResponseView withdrawResponseView = new WithdrawResponseView();
        // send request to customer-service to get the customers info
        withdrawResponseView.setCustomer(customerServiceClient.getCustomer(withdraw.getCustomerId()));
        // set transer data to WithdrawResponseView
        withdrawResponseView.setCurrentBalance(withdraw.getBalance());
        withdrawResponseView.setWithdrawAmount(accoutQueryView.getTransactionAmount());
        withdrawResponseView.setAccountNumber(accoutQueryView.getAccountNumber());

        return withdrawResponseView;
    }



    public BalanceResponseView getAccountBalalce(int id) {
        // get account info
        Account account = accountServiceClient.getBalance(id);
        // create BalanceResponseView
        BalanceResponseView balanceResponseView = new BalanceResponseView();
        balanceResponseView.setAccountNumber(account.getAccountId());
        balanceResponseView.setCurrentBalance(account.getBalance());
        balanceResponseView.setCustomer(customerServiceClient.getCustomer(account.getCustomerId()));

        return balanceResponseView;
    }

    public NewAccountView newAccount(NewAccountView newAccountView) {

        // create a new customer
        Customer customer = customerServiceClient.newCustomer(toCustomer(newAccountView));

        // create new account
        Account account = new Account();
        account.setBalance(newAccountView.getAccount().getBalance());
        account.setCustomerId(customer.getCustomerId());

        account = accountServiceClient.newAccount(account);

        // populate return view
        newAccountView.setCustomer(customer);
        newAccountView.setAccount(account);

        return newAccountView;
    }



// helper methods

    // convert from AccoutQueryView to Credit
    public Credit toCreditView(AccoutQueryView accoutQueryView) {
        Credit credit = new Credit();
        credit.setAccountId(accoutQueryView.getAccountNumber());
        credit.setCredit(accoutQueryView.getTransactionAmount());

        return credit;
    }

    // convert from AccoutQueryView to Withdraw
    public Withdraw toWithdrawView(AccoutQueryView accoutQueryView) {
        Withdraw withdraw = new Withdraw();
        withdraw.setAccountId(accoutQueryView.getAccountNumber());
        withdraw.setWithdraw(accoutQueryView.getTransactionAmount());

        return withdraw;
    }

    // convert from NewAccountView to Customer
    public Customer toCustomer(NewAccountView newAccountView) {
        Customer customer = new Customer();
        customer.setFirstName(newAccountView.getCustomer().getFirstName());
        customer.setLastName(newAccountView.getCustomer().getLastName());
        return customer;
    }





}
