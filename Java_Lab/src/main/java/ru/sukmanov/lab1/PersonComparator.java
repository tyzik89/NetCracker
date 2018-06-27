package ru.sukmanov.lab1;

import java.util.Comparator;

public interface PersonComparator extends Comparator<Person>{
	public int compare(Person a, Person b);
}