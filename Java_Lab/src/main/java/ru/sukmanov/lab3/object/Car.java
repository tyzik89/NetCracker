package ru.sukmanov.lab3.object;

import org.joda.time.LocalDate;
import ru.sukmanov.lab3.xml.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = {"type", "color", "number", "dateOfRelease"}, name = "Car")
public class Car{
    private static int IdInc = 1;
    private int Id;
    private String type;
    private String color;
    private String number;
    private LocalDate dateOfRelease;

    public Car() {}
    /**
     * <p>Конструктор класса Car</p>
     * @param type
     * @param color
     * @param number
     * @param dateOfRelease
     */
    public Car(String type, String color, String number, LocalDate dateOfRelease) {
        this.Id = IdInc++;
        this.type = type;
        this.color = color;
        this.number = number;
        this.dateOfRelease = dateOfRelease;
    }

    /**
     * <p>Возврат времени эксплутации автомобиля</p>
     * @return age of the car
     */
    public int getCarAge() {
        LocalDate currDate = LocalDate.now();
        return currDate.getYear() - dateOfRelease.getYear();
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @XmlElement(name = "Type")
    public String getType() {
        return type;
    }

    @XmlElement(name = "Color")
    public String getColor() {
        return color;
    }

    @XmlElement(name = "Number")
    public String getNumber() {
        return number;
    }

    @XmlElement(name = "DateOfRelease")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Id=" + Id +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", number=" + number +
                ", dateOfRelease=" + dateOfRelease +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (Id != car.Id) return false;
        if (number != car.number) return false;
        if (type != null ? !type.equals(car.type) : car.type != null) return false;
        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        return dateOfRelease != null ? dateOfRelease.equals(car.dateOfRelease) : car.dateOfRelease == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (dateOfRelease != null ? dateOfRelease.hashCode() : 0);
        return result;
    }
}
