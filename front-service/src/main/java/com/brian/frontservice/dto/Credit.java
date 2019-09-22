package com.brian.frontservice.dto;

import java.util.Objects;

public class Credit {

    private int accountId;
    private int balance;
    private int credit;
    private int customerId;


    // getters / setters

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit that = (Credit) o;
        return accountId == that.accountId &&
                balance == that.balance &&
                credit == that.credit &&
                customerId == that.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, credit, customerId);
    }

    // to string


    @Override
    public String toString() {
        return "Credit{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", credit=" + credit +
                ", customerId=" + customerId +
                '}';
    }
}
