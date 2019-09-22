package com.brian.frontservice.view;


import java.util.Objects;

public class AccoutQueryView {

    private int accountNumber;
    private int transactionAmount;
    private String transactionType;


    // getters /setters

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }


    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccoutQueryView that = (AccoutQueryView) o;
        return accountNumber == that.accountNumber &&
                transactionAmount == that.transactionAmount &&
                transactionType.equals(that.transactionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, transactionAmount, transactionType);
    }


    // to string


    @Override
    public String toString() {
        return "AccoutQueryView{" +
                "accountNumber=" + accountNumber +
                ", transactionAmount=" + transactionAmount +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
