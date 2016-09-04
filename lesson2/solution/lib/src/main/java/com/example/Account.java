package com.example;


public class Account {

    private MoneySaver owner;
    private long amount;

    public Account(long amount, MoneySaver owner) {
        this.amount = amount;
        this.owner = owner;
    }

    public String toString() {
        return "Account owner: " + owner.getName() + ", Account Balance: $" + amount;
    }

    public void deposit(long money) {
        this.amount += money;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public static Account largerAccount(Account acc1, Account acc2) {
        if (acc1.amount > acc2.amount)
            return acc1;
        else
            return acc2;
    }

}
