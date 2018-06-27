package ru.testdb.dbconnect.dao;

import ru.testdb.dbconnect.entities.Person;

import java.util.List;

public interface PersonDao {

    void add(Person person);

    List<Person> listPersons();
}
