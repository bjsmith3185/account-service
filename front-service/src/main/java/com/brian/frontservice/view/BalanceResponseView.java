package com.brian.frontservice.view;

import com.brian.frontservice.dto.Customer;

import java.util.Objects;

public class BalanceResponseView {

    private Customer customer;
    private int accountNumber;
    private int currentBalance;

    // getters / setters

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceResponseView that = (BalanceResponseView) o;
        return accountNumber == that.accountNumber &&
                currentBalance == that.currentBalance &&
                customer.equals(that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, accountNumber, currentBalance);
    }


    // to string


    @Override
    public String toString() {
        return "BalanceResponseView{" +
                "customer=" + customer +
                ", accountNumber=" + accountNumber +
                ", currentBalance=" + currentBalance +
                '}';
    }
}
