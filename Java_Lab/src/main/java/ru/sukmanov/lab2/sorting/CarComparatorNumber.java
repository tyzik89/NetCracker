package ru.sukmanov.lab2.sorting;

import ru.sukmanov.lab2.object.Car;

public class CarComparatorNumber implements ObjectComparator<Car> {
    public int compare(Car a, Car b) {
        return a.getNumber().compareTo(b.getNumber());
    }
}
