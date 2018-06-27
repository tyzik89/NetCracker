package ru.sukmanov.lab2;

import org.joda.time.LocalDate;
import ru.sukmanov.lab2.common.AbstractRepository;
import ru.sukmanov.lab2.repositories.PersonRepository;
import ru.sukmanov.lab2.object.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;

public class Main {
    private static Logger log = LogManager.getLogger(Main.class);

	public static void main(String[] args) {

		PersonRepository personRepository = new PersonRepository();
        //PersonRepository<Person> personRepository = new PersonRepository<Person>();

		Person p1 = new Person("Ivanov","Ivan", LocalDate.parse("1989-01-01"));
		log.info("Создание Иванова");
		log.warn("Передупреждение");

        System.out.println("Возраст " + p1.toString() + "\n" + p1.getAge() + " лет\n");
		personRepository.addElem(p1);
		System.out.println("\nПечать репозитория после добавления Иванова");
		personRepository.showRepository();
		personRepository.addElem(new Person("Petrov", "Petr",  LocalDate.parse("1976-12-10")));
		personRepository.addElem(new Person("Sidorov","Sidr", LocalDate.parse("2005-05-15")));
        personRepository.addElem(new Person("Balalaikin","Vova", LocalDate.parse("1990-05-15")));
        personRepository.addElem(new Person("Balalaikin","Dima", LocalDate.parse("1979-08-25")));

        log.warn("Добавление других записий в репозиторий");

        System.out.println("\nПечать репозитория после доабвления Петрова, Сидорова, Балалайкина");
		personRepository.showRepository();

		personRepository.bubleSortByAge();
        System.out.println("\nПечать сортировки пузырьком по возрасту по возрастанию");
        personRepository.showRepository();
        personRepository.bubleSortByFio();
        System.out.println("\nПечать сортировки по фамилии");
        personRepository.showRepository();
        personRepository.quickSortByAge();
        System.out.println("\nПечать сортировки Хоара по возрасту по убыванию");
        personRepository.showRepository();

        System.out.println("\nПоиск всех Балалайкиных");
        PersonRepository findPerson = (PersonRepository) personRepository.findElem(Person -> {return Person.getLastName().equals("Balalaikin");});
        findPerson.showRepository();
        //Person[] findPerson = personRepository.findElem(Person -> {return Person.getLastName().equals("Balalaikin");});
        //System.out.println(personRepository.findElem(person -> {return person.getLastName().equals("Balalaikin");}));
        //for(Object o : findPerson){
        //    System.out.println(o);
        //}

		/*personRepository.delElem(1);
        System.out.println("\nПечать репозитория после удаление Иванова");
        personRepository.showRepository();
        personRepository.delElem(0);
        System.out.println("\nПечать репозитория после удаление Петрова");
        personRepository.showRepository();
        personRepository.delElem(0);
        personRepository.delElem(0);
        System.out.println("\nПечать репозитория после удаление всех элементов");
        personRepository.showRepository();*/


		/* Для класса MyRepository
		//Объект репозитория персон
		MyRepository<Person> repPerson = new MyRepository<Person>();
		//Объект репозитория автобобилей
		MyRepository<Car> repCar = new MyRepository<Car>();

		Person p1 = new Person("Иванов","Иван", LocalDate.parse("1989-01-01"));
		repPerson.addElem(p1);
		repPerson.addElem(new Person("Петров","Петр", LocalDate.parse("1990-12-10")));
		repPerson.addElem(new Person("Сидоров","Сидр", LocalDate.parse("2000-05-15")));
		repPerson.showRepository();
		System.out.println(p1.lastName + " возраст: " + p1.getAge());

		repCar.addElem(new Car("Москвич", "Белый", "о315го", LocalDate.parse("1980")));
		repCar.addElem(new Car("Лада", "Зелёный", "у572гу", LocalDate.parse("2000")));
		repCar.showRepository();

		System.out.println("\nУдаление элементов: " + repPerson.getElem(1).toString()
		        + " " + repCar.getElem(0).toString() + "\n");
		repPerson.delElem(1);
		repCar.delElem(0);
		repCar.showRepository();
		repPerson.showRepository();*/
	}
}
