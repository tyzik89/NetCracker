package ru.sukmanov.lab2.common;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sukmanov.lab2.object.*;
import ru.sukmanov.lab2.repositories.CarRepository;
import ru.sukmanov.lab2.repositories.MyRepository;
import ru.sukmanov.lab2.repositories.PersonRepository;

import static org.junit.Assert.*;

public class AbstractRepositoryTest {
    //private MyRepository<Person> repPerson = new MyRepository<Person>();
    private PersonRepository repPerson = new PersonRepository();
    private CarRepository repCar = new CarRepository();
    private Person p1, p2, p3;
    private Car c1, c2 ,c3;

    @Before
    public void initRepository(){
        p1 = new Person("Ivanov","Ivan", LocalDate.parse("1980-01-01"));
        p2 = new Person("Sidorov","Petr", LocalDate.parse("1990-12-05"));
        p3 = new Person("Petrov","Sidr", LocalDate.parse("2000-02-01"));
        c1 = new Car("A", "White", "o315go", LocalDate.parse("1980"));
        c2 = new Car("B", "Black", "0315oo", LocalDate.parse("1990"));
        c3 = new Car("C", "Red", "y749yy", LocalDate.parse("2000"));
    }

    @After
    public void releaseRepository(){
        repPerson = null;
        repCar = null;
        p1 = null;
        p2 = null;
        p3 = null;
        c1 = null;
        c2 = null;
        c3 = null;
    }

    /**
     * <p>Unit-test add item</p>
     */
    @Test
    public void addElem() {
        repPerson.addElem(p1);
        assertEquals(1, repPerson.size());
        repPerson.addElem(p2);
        assertEquals(2, repPerson.size());
        repPerson.addElem(p3);
        assertEquals(3, repPerson.size());
        assertEquals(p1, repPerson.getElem(0));
        assertEquals(p2, repPerson.getElem(1));
        assertEquals(p3, repPerson.getElem(2));
    }

    /**
     * <p>Unit-test delete item</p>
     */
    @Test
    public void delElem() {
        repPerson.addElem(p1);
        repPerson.addElem(p2);
        repPerson.addElem(p3);
        repPerson.delElem(1);
        assertEquals(2, repPerson.size());
        assertEquals(p1, repPerson.getElem(0));
        assertEquals(p3, repPerson.getElem(1));
        repPerson.delElem(0);
        repPerson.delElem(0);
        assertEquals(0, repPerson.size());
    }

    /**
     * <p>Unit-test search item</p>
     */
    @Test
    public void findElem() {
        repPerson.addElem(p1);
        repPerson.addElem(p2);
        repPerson.addElem(p3);
        //Person pf = repPerson.findElem(person -> {return person.getLastName().equals("Petrov");});
        //assertEquals(p3, pf);
    }

    /**
     * <p>Unit-test getting item</p>
     */
    @Test
    public void getElem() {
        repPerson.addElem(p1);
        repPerson.addElem(p2);
        assertEquals(p1, repPerson.getElem(0));
    }

    /**
     * <p>Unit-test buble sort by type car</p>
     */
    @Test
    public void bubleSorting() {
        repCar.addElem(c2);
        repCar.addElem(c3);
        repCar.addElem(c1);
        repCar.bubleSortByType();
        assertEquals(c1, repCar.getElem(0));
    }

    /**
     * <p>Unit-test quick sort by age car</p>
     */
    @Test
    public void qsort() {
        repCar.addElem(c2);
        repCar.addElem(c3);
        repCar.addElem(c1);
        repCar.quickSortByAge();
        assertEquals(c1, repCar.getElem(0));
    }
}