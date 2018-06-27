package ru.sukmanov.lab1;

import org.joda.time.LocalDate;

public class Person{
	private int mId;
	private static int sIdInc = 1;
	public String lastName;
	public String firstName;
	public LocalDate dateOfBirth;
	
	public Person(String lastName, String firstName, LocalDate dateOfBirth) {
		super();
		this.mId = sIdInc++;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return this.mId;
	}
		
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**<p>Get human age</p>*/
	public int getAge() {
		LocalDate currDate = LocalDate.now();
		return currDate.getYear() - dateOfBirth.getYear();
	}

	@Override
	public String toString() {
		return "Id: " + mId + "\nФИО: " + lastName + " " + firstName +
				"\nДата рождения: " + dateOfBirth.toString() +
				"\nВозраст: " + getAge() +
				"\n==============\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + mId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mId != other.mId)
			return false;
		return true;
	}
	
}
