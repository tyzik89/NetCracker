package ru.sukmanov.entities;

public class Customer {
    //id of new client
    private long id;
    //counter of id
    private static long idInc = 1;
    //Type of operations - true, if client puts money to deposit
    private boolean toDeposit;
    //Amount of money
    private int amount;
    //Service time
    private int serviceTime;

    public Customer(){}

    public Customer(boolean toDeposit, int amount, int serviceTime) {
        this.id = idInc++;
        this.toDeposit = toDeposit;
        this.amount = amount;
        this.serviceTime = serviceTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public boolean isToDeposit() {
        return toDeposit;
    }

    public void setToDeposit(boolean toDeposit) {
        this.toDeposit = toDeposit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", toDeposit=" + toDeposit +
                ", amount=" + amount +
                ", serviceTime=" + serviceTime +
                '}';
    }
}
