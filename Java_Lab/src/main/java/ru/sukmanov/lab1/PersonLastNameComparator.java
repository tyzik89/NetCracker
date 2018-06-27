package ru.sukmanov.lab1;

public class PersonLastNameComparator implements PersonComparator{
		
	public int compare(Person a, Person b) {
		return a.getLastName().compareTo(b.getLastName());
	}

}
