package ru.sukmanov.lab3.repositories;

import ru.sukmanov.lab3.common.AbstractRepository;
import ru.sukmanov.lab3.object.Person;
import ru.sukmanov.lab3.sorting.PersonComparatorAge;
import ru.sukmanov.lab3.sorting.PersonComparatorLastName;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "PersonRepository")
@XmlSeeAlso({Person.class})
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
