package ru.sukmanov.lab1;

public class PersonAgeComparator implements PersonComparator{
	
	public int compare(Person a, Person b) {
		
		return a.getAge() - b.getAge();
	}

}
