package ru.sukmanov.lab1;

import java.util.Scanner;

import org.joda.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		Repository rep = new Repository();

		rep.addPerson(new Person("Иванов","Иван",LocalDate.parse("1989-01-01")));
		rep.addPerson(new Person("Петров", "Петр",LocalDate.parse("1980-11-09")));
		rep.addPerson(new Person("Сидоров", "Сидр",LocalDate.parse("2000-11-09")));
		rep.addPerson(new Person("Иванов","Иван",LocalDate.parse("1999-01-01")));
		rep.addPerson(new Person("Петров", "Петр",LocalDate.parse("1999-11-09")));
		rep.addPerson(new Person("Сидоров", "Сидр",LocalDate.parse("2010-11-09")));
		
		/*
		Comparator<Person> pc = new PersonAgeComparator();
		int a = pc.compare(new Person("������","����",LocalDate.parse("1989-01-01")), 
				new Person("������","����",LocalDate.parse("1000-01-01")));
		System.out.println(a);*/
		
	
		label1:
		while (true) {
			System.out.println("\nВыберите пункт меню: ");
			System.out.println("1 - Добавить запись");
			System.out.println("2 - Удалить запись");
			System.out.println("3 - Изменить запись");
			System.out.println("4 - Показать все записи");
			System.out.println("5 - Найти запись");
			System.out.println("6 - Сортировать");
			System.out.println("0 - Выход");
			System.out.println();
			int item = Integer.parseInt(sc.next());
			switch (item) {
				case 1 :
					sc.nextLine();
					System.out.print("\nВведите фамилию: ");
					String lname = sc.next();
					System.out.print("\nВведите имя: ");
					String fname = sc.next();
					System.out.print("\nВведите дату рождения в формате yyyy-mm-dd: ");
					String bdate = sc.next();
					rep.addPerson(new Person(lname, fname, LocalDate.parse(bdate)));
					break;
				case 2 :
					sc.nextLine();
					System.out.print("\nВведите ID удаляемой записи: ");
					int delId = Integer.parseInt(sc.next());
					//String delId = sc.next();
					rep.removePerson(delId);
					break;
				case 3 :
					System.out.print("\nВведите ID записи для изменения: ");
					int changeId = Integer.parseInt(sc.next());
					sc.nextLine();
					System.out.print("\nВведите фамилию: ");
					String changeLName = sc.next();
					System.out.print("\nВведите имя: ");
					String changeFName = sc.next();
					System.out.print("\nВведите дату рождения в формате yyyy-mm-dd: ");
					String changeDate = sc.next();
					if(rep.changePerson(changeId, changeLName, changeFName, LocalDate.parse(changeDate)))
						System.out.println("Изменено!");
					else
						System.out.println("Ошибка! Такого Id не существует!");
					break;
				case 4 :
					System.out.println(rep.showRep());
					break;
				case 5 :
					System.out.print("\nВведите фамилию, для поиска: ");
					String findLName = sc.next();
					rep.find(findLName);
					break;
				case 6:
					System.out.print("\nКакую сортировку осуществить? (по возрасту - 0, по фамилиям - 1): ");
					int types = Integer.parseInt(sc.next());
					if (types == 0) {
						rep.qSortByAge(); // Сортировка Хоара
					}
					else if (types == 1) {
						rep.bubleSortByFio();
					}
					break;
				case 0 :
					break label1;
				default : System.out.println("\nТакого пункта нет");

			}
		}
		
	}

}
