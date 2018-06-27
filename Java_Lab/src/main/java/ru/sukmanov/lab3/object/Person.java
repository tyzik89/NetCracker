package ru.sukmanov.lab3.object;

import org.joda.time.LocalDate;
import ru.sukmanov.lab3.xml.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = {"lastName", "firstName", "dateOfBirth"}, name = "Person")
public class Person {

    private static int sIdInc = 1;
    private int Id;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;

    public Person () {
    }

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

    @XmlAttribute(name = "id")
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @XmlElement(name = "Lastname")
    public String getLastName() {
        return lastName;
    }

    @XmlElement(name = "Fastname")
    public String getFirstName() {
        return firstName;
    }

    @XmlElement(name = "DateOfBirthday")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
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
