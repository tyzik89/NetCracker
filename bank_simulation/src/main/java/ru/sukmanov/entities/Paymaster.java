package ru.sukmanov.entities;

import java.util.LinkedList;
import java.util.Queue;

public class Paymaster<T extends Customer> extends Thread {

    private BankCashDesc bankCashDesc;
    private Queue<T> customersQueue = new LinkedList<T>();
    private boolean isActive;
    private int completedWork = 0;

    public Paymaster(){}

    public Paymaster(String name, BankCashDesc bankCashDesc) {
        super(name);
        this.bankCashDesc = bankCashDesc;
        isActive = true;
    }

    public String getCompletedWork() {
        return String.format("\n**********Операционист %s обработал(а) %s клиента(ов)**********\n", currentThread().getName(), completedWork);
    }

    public void disable() {
        isActive = false;
    }

    public int countCurrentCustomers() {
        return customersQueue.size();
    }

    public void offerCustomer(T t) {
        synchronized (customersQueue) {
            customersQueue.offer(t);
            customersQueue.notifyAll();
        }

    }

    public T peekCustomer() {
        synchronized(customersQueue) {
            if (customersQueue.isEmpty()) {
                try {
                    System.out.printf("\nОперационистка %s <<<БЕЗДЕЛЬЕ>>>\n", currentThread().getName());
                    customersQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return customersQueue.peek();
    }

    @Override
    public void run() {
        while (!customersQueue.isEmpty() || isActive) {
            /*while (customersQueue.isEmpty()) {
                Thread.yield();
            }*/
            T client = peekCustomer();
            if (client.getId() == 0) {
                break;
            }
            System.out.printf("\nОперационистка %s <<<ОБРАБОТКА>>> клиента %s\n", currentThread().getName(), client.getId());
            int money = client.getAmount();

            try {
                sleep(client.getServiceTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (client.isToDeposit()) {
                bankCashDesc.depositMoney(money);
                System.out.printf(
                        "\n%s >>> Клиент %s пополнил баланс на %s. Итого %s\n",
                        currentThread().getName(),
                        client.getId(),
                        client.getAmount(),
                        bankCashDesc.toString());
            } else {
                if (bankCashDesc.isOperationAviable(money)) {
                    bankCashDesc.withdrawMoney(money);
                    System.out.printf(
                            "\n%s >>> Клиент %s уменьшил баланс на %s. Итого %s\n",
                            currentThread().getName(),
                            client.getId(),
                            client.getAmount(),
                            bankCashDesc.toString());
                } else {
                    System.out.printf(
                            "\n%s говорит клиенту №%s - БАЛАНС УЙДЁТ В МИНУС! ПРОВАЛИВАЙ!\n",
                            currentThread().getName(),
                            client.getId());
                }

            }
            System.out.printf(
                    "%s >>> Работа с клиентом %s завершена за %s мсек\n",
                    currentThread().getName(),
                    client.getId(),
                    client.getServiceTime());
            completedWork++;
            customersQueue.poll();
        }
        System.out.println(getCompletedWork());
        currentThread().interrupt();
    }
}
