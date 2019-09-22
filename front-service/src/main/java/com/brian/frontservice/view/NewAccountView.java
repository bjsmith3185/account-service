package com.brian.frontservice.view;

import com.brian.frontservice.dto.Account;
import com.brian.frontservice.dto.Customer;

import java.util.Objects;

public class NewAccountView {

    private Customer customer;
    private Account account;


    // getters / setters

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewAccountView that = (NewAccountView) o;
        return customer.equals(that.customer) &&
                account.equals(that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, account);
    }


    // to string


    @Override
    public String toString() {
        return "NewAccountView{" +
                "customer=" + customer +
                ", account=" + account +
                '}';
    }
}
