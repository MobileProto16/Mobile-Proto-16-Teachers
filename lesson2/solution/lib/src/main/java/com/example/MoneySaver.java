package com.example;


public class MoneySaver {

    private long myMoney;
    private Account myAccount;
    private String name;


    public MoneySaver(String name, long money) {
        this.name = name;
        myMoney = money;
    }

    public String toString() {
        return name + ", " + "My balance is: "+ myMoney;
    }

    public String getName() {
        return name;
    }

    public void signUpForChecking(int amount) {
        myAccount = new CheckingAccount(amount, this);
        myMoney -= amount;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public void deposit(int amount) {
        myAccount.deposit(amount);
        myMoney -= amount;
    }

    public void withdraw(int amount) {
        ((CheckingAccount) myAccount).withdraw(amount);
        myMoney += amount;
    }

    public static void main(String[] args) {
//        MoneySaver jim = new MoneySaver("Jim", 100);
//        Account a = new Account(100, jim);
//        System.out.println(a);
//        a.setAmount(20);
//        System.out.println("New amount: " + a.getAmount());
//        System.out.println(jim);
//        a.deposit(10);
//        System.out.println("New amount: " + a.getAmount());

//        MoneySaver jim = new MoneySaver("Jim", 100);
//        MoneySaver bob = new MoneySaver("Bob", 200);
//        Account small = new Account(20, jim);
//        Account big = new Account(30, bob);
//        System.out.println(Account.largerAccount(small, big));


        MoneySaver jim = new MoneySaver("Jim", 100);
        jim.signUpForChecking(30);
        CheckingAccount acc = (CheckingAccount) jim.getMyAccount();
        System.out.println(jim);
        System.out.println(acc);
        jim.deposit(40);
        System.out.println(jim);
        jim.withdraw(60);
        System.out.println(jim);
        System.out.println(acc);
    }
}
