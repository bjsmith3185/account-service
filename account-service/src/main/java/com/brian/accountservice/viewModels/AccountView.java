package com.brian.accountservice.viewModels;

import java.util.Objects;

public class AccountView {

    private int accountId;
    private int balance;
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
        AccountView that = (AccountView) o;
        return accountId == that.accountId &&
                balance == that.balance &&
                customerId == that.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, customerId);
    }


    // to string


    @Override
    public String toString() {
        return "AccountView{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", customerId=" + customerId +
                '}';
    }
}
