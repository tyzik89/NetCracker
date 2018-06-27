package ru.sukmanov.lab3.sorting;

import ru.sukmanov.lab3.object.Car;

public class CarComparatorType implements ObjectComparator<Car> {
    public int compare(Car a, Car b) {
        return a.getType().compareTo(b.getType());
    }
}
