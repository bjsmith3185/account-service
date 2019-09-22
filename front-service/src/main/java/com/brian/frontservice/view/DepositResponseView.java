package com.brian.frontservice.view;

import com.brian.frontservice.dto.Customer;

import java.util.Objects;

public class DepositResponseView {


    private Customer customer;
    private int accountNumber;
    private int depositAmount;
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

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    // equals/hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositResponseView that = (DepositResponseView) o;
        return accountNumber == that.accountNumber &&
                depositAmount == that.depositAmount &&
                currentBalance == that.currentBalance &&
                customer.equals(that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, accountNumber, depositAmount, currentBalance);
    }

    // to string


    @Override
    public String toString() {
        return "DepositResponseView{" +
                "customer=" + customer +
                ", accountNumber=" + accountNumber +
                ", depositAmount=" + depositAmount +
                ", currentBalance=" + currentBalance +
                '}';
    }
}
