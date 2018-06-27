package ru.sukmanov.lab3.sorting;

import ru.sukmanov.lab3.object.Person;

public class PersonComparatorAge implements ObjectComparator<Person> {

    public int compare(Person a, Person b) {
        return a.getAge() - b.getAge();
    }
}
