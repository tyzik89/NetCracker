package ru.sukmanov.lab3.repositories;

import ru.sukmanov.lab3.common.AbstractRepository;
import ru.sukmanov.lab3.object.Car;
import ru.sukmanov.lab3.sorting.CarComparatorAge;
import ru.sukmanov.lab3.sorting.CarComparatorNumber;
import ru.sukmanov.lab3.sorting.CarComparatorType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "CarRepository")
@XmlSeeAlso({Car.class})
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
