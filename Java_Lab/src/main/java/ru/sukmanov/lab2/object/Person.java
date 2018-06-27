package ru.sukmanov.lab2.object;

import org.joda.time.LocalDate;

public class Person {
    private static int sIdInc = 1;
    private int Id;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;

    /**
     * <p>Конструктор класса Person</p>
     * @param lastName
     * @param firstName
     * @param dateOfBirth
     */
    public Person(String lastName, String firstName, LocalDate dateOfBirth) {
        this.Id = sIdInc++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * <p>Вычисление возраста человека</p>
     * @return age of the person
     */
    public int getAge() {
        LocalDate currDate = LocalDate.now();
        return currDate.getYear() - dateOfBirth.getYear();
    }

    public int getId() {
        return Id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Id=" + Id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (Id != person.Id) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        return dateOfBirth != null ? dateOfBirth.equals(person.dateOfBirth) : person.dateOfBirth == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }
}
