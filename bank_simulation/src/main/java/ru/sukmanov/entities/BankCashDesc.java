package ru.sukmanov.entities;

public class BankCashDesc {
    //Start amount cash
    private int money;

    public BankCashDesc(){}

    public BankCashDesc(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "BankCashDesc{" +
                "money=" + money +
                '}';
    }

    /**
     * Put money to deposit
     * @param cash
     */
    public synchronized void depositMoney(int cash) {
        this.money += cash;
    }

    /**
     * Withdraw money from deposit
     * @param cash
     * @return true, if client withdraw money from deposit
     */
    public synchronized void withdrawMoney(int cash) {
            this.money -= cash;
    }

    public synchronized boolean isOperationAviable(int cash){
        if (this.money < cash) {
            return false;
        } else {
            return true;
        }
    }
}
