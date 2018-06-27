package ru.sukmanov.lab2.repositories;

import ru.sukmanov.lab2.common.AbstractRepository;
import ru.sukmanov.lab2.object.Car;
import ru.sukmanov.lab2.sorting.CarComparatorType;
import ru.sukmanov.lab2.sorting.CarComparatorAge;
import ru.sukmanov.lab2.sorting.CarComparatorNumber;

public class CarRepository extends AbstractRepository<Car> {

    public void bubleSortByType(){
        this.bubleSorting(new CarComparatorType());
    }

    public void bubleSortByAge(){
        this.bubleSorting(new CarComparatorAge());
    }

    public void quickSortByNumber(){
        qsort(new CarComparatorNumber());
    }

    public void quickSortByAge(){
        qsort(new CarComparatorAge());
    }

    @Override
    protected AbstractRepository<Car> getRepositoryInstance() {
        return new CarRepository();
    }
}
