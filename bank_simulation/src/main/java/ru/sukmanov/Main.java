package ru.sukmanov;

import ru.sukmanov.entities.BankCashDesc;
import ru.sukmanov.entities.Customer;
import ru.sukmanov.entities.Paymaster;
import ru.sukmanov.generators.CustomerGenerator;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        BankCashDesc bankCashDesc = new BankCashDesc(50000);
        System.out.println(bankCashDesc.toString());

        Paymaster[] paymasters = {
                new Paymaster<Customer>("Марь Иванна", bankCashDesc),
                new Paymaster<Customer>("Петровна", bankCashDesc),
                new Paymaster<Customer>("Василий Алибабаевич", bankCashDesc),
                new Paymaster<Customer>("Работник месяца", bankCashDesc),
                /*new Paymaster<Customer>("Пупкин1", bankCashDesc),
                new Paymaster<Customer>("Пупкин2", bankCashDesc),
                new Paymaster<Customer>("Пупкин3", bankCashDesc),
                new Paymaster<Customer>("Пупкин4", bankCashDesc),
                new Paymaster<Customer>("Пупкин5", bankCashDesc),
                new Paymaster<Customer>("Пупкин6", bankCashDesc),*/
                new Paymaster<Customer>("Пупкин", bankCashDesc)
        };

        CustomerGenerator customerGenerator = new CustomerGenerator(11, paymasters, 100, 10);

        long start = System.nanoTime();
        for (Paymaster paymaster : paymasters) {
            paymaster.start();
        }

        customerGenerator.start();

        if (customerGenerator.isAlive()) {
            try {
                customerGenerator.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Paymaster paymaster : paymasters) {
            try {
                paymaster.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long finish = System.nanoTime();

        System.out.println("==================================");
        System.out.printf("Обработка операций завершена за время: %s нс\nБаланс счёта: %s", String.format("%,d",finish - start), bankCashDesc.toString());
    }
}
