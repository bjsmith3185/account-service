package com.brian.accountservice.viewModels;

import java.util.Objects;

public class WithdrawView {

    private int accountId;
    private int balance;
    private int withdraw;

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

    public int getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(int withdraw) {
        this.withdraw = withdraw;
    }


    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithdrawView that = (WithdrawView) o;
        return accountId == that.accountId &&
                balance == that.balance &&
                withdraw == that.withdraw;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, withdraw);
    }

    // to string


    @Override
    public String toString() {
        return "WithdrawView{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", withdraw=" + withdraw +
                '}';
    }
}
