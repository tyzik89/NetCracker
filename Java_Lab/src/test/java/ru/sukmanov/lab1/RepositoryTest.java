package ru.sukmanov.lab1;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositoryTest {
    Repository repository;

    @Before
    public void initRepository() {
        repository = new Repository();
    }

    @After
    public void afterTest() {
        repository = null;
    }

    @Test
    public void addPerson() {
        repository.addPerson(new Person("Петров", "Петр", LocalDate.parse("1999-11-09")));
        assertEquals(1, repository.lengthRep());
        assertEquals("Петров", repository.lastnameRep());
    }

    @Test
    public void removePerson() {
        repository.addPerson(new Person("Петров", "Петр", LocalDate.parse("1999-11-09")));
        repository.removePerson("Петров");
        assertEquals(0, repository.lengthRep());
    }
}