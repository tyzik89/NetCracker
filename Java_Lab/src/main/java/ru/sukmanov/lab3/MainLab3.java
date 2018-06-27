package ru.sukmanov.lab3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import ru.sukmanov.lab3.common.AbstractRepository;
import ru.sukmanov.lab3.object.Car;
import ru.sukmanov.lab3.object.Person;
import ru.sukmanov.lab3.repositories.CarRepository;
import ru.sukmanov.lab3.repositories.PersonRepository;
import ru.sukmanov.lab3.xml.JaxbParser;

public class MainLab3 {
    private static Logger log = LogManager.getLogger(MainLab3.class);

	public static void main(String[] args) {

        AbstractRepository<Person> personRepository = new PersonRepository();
        personRepository.addElem(new Person("Petrov", "Petr",  LocalDate.parse("1976-12-10")));
        personRepository.addElem(new Person("Sidorov","Sidr", LocalDate.parse("2005-05-15")));
        personRepository.addElem(new Person("Balalaikin","Vova", LocalDate.parse("1990-05-15")));
        personRepository.showRepository();

        AbstractRepository<Car> carRepository = new CarRepository();
        carRepository.addElem(new Car("Москвич", "Вкрапинку", "о315го", LocalDate.parse("2016")));
        carRepository.addElem(new Car("Камаз", "Розовый", "о777ёё", LocalDate.parse("2018")));
        carRepository.showRepository();

        JaxbParser.converObjectToXml(personRepository, "src/main/resources/lab3/PersonRepository.xml");
        JaxbParser.converObjectToXml(carRepository, "src/main/resources/lab3/CarRepository.xml");

        System.out.println("\nИзвлечение из XML\n");
        AbstractRepository pr = JaxbParser.converXmlToObject("src/main/resources/lab3/PersonRepository.xml");
        AbstractRepository cr = JaxbParser.converXmlToObject("src/main/resources/lab3/CarRepository.xml");
        pr.showRepository();
        cr.showRepository();

        //С сортировкой
        /*PersonRepository personRepository = new PersonRepository();
        personRepository.addElem(new Person("Petrov", "Petr",  LocalDate.parse("1976-12-10")));
        personRepository.addElem(new Person("Sidorov","Sidr", LocalDate.parse("2005-05-15")));
        personRepository.addElem(new Person("Balalaikin","Vova", LocalDate.parse("1990-05-15")));
        personRepository.showRepository();

        personRepository.bubleSortByFio();
        System.out.println();
        personRepository.showRepository();
        System.out.println();
        JaxbParser.converObjectToXml(personRepository, "src/main/resources/lab3/PersonRepository.xml");*/

    }
}