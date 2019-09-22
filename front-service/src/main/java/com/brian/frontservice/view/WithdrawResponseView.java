package com.brian.frontservice.view;

import com.brian.frontservice.dto.Customer;

import java.util.Objects;

public class WithdrawResponseView {

    private Customer customer;
    private int accountNumber;
    private int withdrawAmount;
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

    public int getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(int withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
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
        WithdrawResponseView that = (WithdrawResponseView) o;
        return accountNumber == that.accountNumber &&
                withdrawAmount == that.withdrawAmount &&
                currentBalance == that.currentBalance &&
                customer.equals(that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, accountNumber, withdrawAmount, currentBalance);
    }


    // to string


    @Override
    public String toString() {
        return "WithdrawResponseView{" +
                "customer=" + customer +
                ", accountNumber=" + accountNumber +
                ", withdrawAmount=" + withdrawAmount +
                ", currentBalance=" + currentBalance +
                '}';
    }
}
