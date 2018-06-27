package ru.testdb.dbconnect.service;

import ru.testdb.dbconnect.entities.Person;

import java.util.List;

public interface PersonService {

    void add(Person person);

    List<Person> listPersons();

}
