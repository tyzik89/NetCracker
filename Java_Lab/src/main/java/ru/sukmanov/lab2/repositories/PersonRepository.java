package ru.sukmanov.lab2.repositories;

import org.joda.time.LocalDate;
import ru.sukmanov.lab2.common.AbstractRepository;
import ru.sukmanov.lab2.object.Person;
import ru.sukmanov.lab2.sorting.PersonComparatorLastName;
import ru.sukmanov.lab2.sorting.PersonComparatorAge;

public class PersonRepository extends AbstractRepository<Person> {

    public void bubleSortByFio(){
        this.bubleSorting(new PersonComparatorLastName());
    }

    public void bubleSortByAge(){
        this.bubleSorting(new PersonComparatorAge());
    }

    public void quickSortByFio(){
        qsort(new PersonComparatorLastName());
    }

    public void quickSortByAge(){
        qsort(new PersonComparatorAge());
    }

    @Override
    protected AbstractRepository<Person> getRepositoryInstance() {
        return new PersonRepository();
    }
}
