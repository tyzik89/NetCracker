package ru.sukmanov.generators;

import ru.sukmanov.entities.Customer;
import ru.sukmanov.entities.Paymaster;

import java.util.Queue;
import java.util.Random;

public class CustomerGenerator extends Thread {

    private int count;
    private Paymaster[] paymasters;
    private int feedForRandom = -1;
    private int timeBetweenGeneration = 500;

    public CustomerGenerator() {}

    public CustomerGenerator(int count, Paymaster[] paymasters, int timeBetweenGeneration) {
        this.count = count;
        this.paymasters = paymasters;
        this.timeBetweenGeneration = timeBetweenGeneration;
    }

    public CustomerGenerator(int count, Paymaster[] paymasters, int timeBetweenGeneration, int feedForRandom) {
        this.count = count;
        this.paymasters = paymasters;
        this.feedForRandom = feedForRandom;
        this.timeBetweenGeneration = timeBetweenGeneration;
    }

    @Override
    public void run() {
        Random random = (feedForRandom < 0) ? new Random() : new Random(feedForRandom);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                int indPaymaster = 0;
                Customer customer = new Customer(random.nextBoolean(), random.nextInt(40000), random.nextInt(10000));
                for (int j = 0; j < paymasters.length - 1; j++) {
                    int currPaymaster = paymasters[j].countCurrentCustomers();
                    int nextPaymaster = paymasters[j + 1].countCurrentCustomers();
                    if (currPaymaster > nextPaymaster) {
                        indPaymaster = j + 1;
                    }
                }
                System.out.printf("\nПришёл клиент: %s и стал в очередь к %s\n", customer.toString(), paymasters[indPaymaster].getName());
                paymasters[indPaymaster].offerCustomer(customer);
                try {
                    sleep(random.nextInt(timeBetweenGeneration));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Customer finishWork = new Customer();
        finishWork.setId(0);
        System.out.println("\n==========================================Рабочий день окончен - больше клиентов не будет!=======================================\n");
        for (Paymaster paymaster : paymasters) {
            paymaster.disable();
            paymaster.offerCustomer(finishWork);
        }

    }
}
