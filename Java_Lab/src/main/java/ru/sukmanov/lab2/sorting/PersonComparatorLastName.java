package ru.sukmanov.lab2.sorting;

import ru.sukmanov.lab2.object.Person;

public class PersonComparatorLastName implements ObjectComparator<Person> {
    public int compare(Person a, Person b) {
        return a.getLastName().compareTo(b.getLastName());
    }
}
