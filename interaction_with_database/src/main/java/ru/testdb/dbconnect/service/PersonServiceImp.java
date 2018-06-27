package ru.testdb.dbconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testdb.dbconnect.dao.PersonDao;
import ru.testdb.dbconnect.entities.Person;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService{

    @Autowired
    private PersonDao userDao;

    @Transactional
    @Override
    public void add(Person person) {
        userDao.add(person);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> listPersons() {
        return userDao.listPersons();
    }
}
