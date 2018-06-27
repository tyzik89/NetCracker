package ru.sukmanov.lab1;

import java.util.Arrays;
import org.joda.time.LocalDate;

public class Repository implements InterfaceRepository {

	private static int sSizeRep = 1;
	private Person[] rep = new Person[sSizeRep];
	private int mCurrSize = 0;
	
	//PersonAgeComparator pac = new PersonAgeComparator();
	//PersonLastNameComparator plc = new PersonLastNameComparator();
	private boolean sort;

	public Repository() {
		super();
	}

	/**
	 * <p>
	 * Добавить персону
	 * </p>
	 */
	public void addPerson(Person p) {
		if (mCurrSize >= sSizeRep) {
			sSizeRep++;
			Person[] tempRep = new Person[sSizeRep];
			System.arraycopy(rep, 0, tempRep, 0, mCurrSize);
			rep = tempRep;
		}
		rep[mCurrSize] = p;
		mCurrSize++;
	}

	public void removePerson() {
	}

	/**
	 * Удаление персон с определенной фамилией
	 * @param ln
	 */
	public void removePerson(String ln) {
		int i = 0;
		for (; i < rep.length; i++) {
			if (rep[i].lastName.equals(ln)) {
				for (int k = i; k < rep.length - 1; k++)
					rep[k] = rep[k + 1];
				sSizeRep--;
				mCurrSize--;
				Person[] tempRep = new Person[sSizeRep];
				System.arraycopy(rep, 0, tempRep, 0, mCurrSize);
				rep = tempRep;
			}
		}
	}
	/**
	 * <p>
	 * Удаление персоны по id
	 * </p>
	 * 
	 * @param id
	 */
	public void removePerson(int id) {
		int i = 0;
		for (; i < rep.length; i++) {
			if (rep[i].getId() == id) {
				for (int k = i; k < rep.length - 1; k++)
					rep[k] = rep[k + 1];
				sSizeRep--;
				mCurrSize--;
				Person[] tempRep = new Person[sSizeRep];
				System.arraycopy(rep, 0, tempRep, 0, mCurrSize);
				rep = tempRep;
			}
		}

	}

	public boolean changePerson() {
		return false;

	}

	/**
	 * <p>
	 * изменить персону
	 * </p>
	 * 
	 * @param id
	 * @param lName
	 * @param fName
	 * @param bdate
	 * @return true, если изменения удались
	 */
	public boolean changePerson(int id, String lName, String fName, LocalDate bdate) {
		// for(int i = 0; i < rep.length; i++) {
		for (Person p : rep) {
			if (p.getId() == id) {
				p.setLastName(lName);
				p.setFirstName(fName);
				p.setDateOfBirth(bdate);
				return true;
			}
		}
		return false;
	}

	public void find() {

	}

	/**
	 * <p>
	 * Печать репозитория
	 * </p>
	 */
	public String showRep() {
		return Arrays.toString(rep);
	}

	/**
	 * <p>
	 * Поиск
	 * </p>
	 *
	 * @param s
	 */
	public void find(String s) {
		s = s.toLowerCase();
		for (Person p : rep) {
			if (s.equals(p.lastName.toLowerCase())) {
				System.out.println(p.toString());
			}
		}
	}
	
	/**
	 * сортировка пузырьком
	 */
	private void bubleSorting(PersonComparator comparator) {
		Person temp;
		sort = true;

		for(int i = 0; i < rep.length - 1; i++ ) {
			if ((comparator.compare(rep[i + 1], rep[i]) < 0)) {
				temp = rep[i];
				rep[i] = rep[i + 1];
				rep[i + 1] = temp;
				sort = false;
			}
		}
		if (!sort) {
			bubleSorting(comparator);
		}
	}

	/**
	 * Сортировка пузырьком по фамилии
	 */
	public void bubleSortByFio() {
		bubleSorting(new PersonLastNameComparator());
	}

	/**
	 * Сортировка пузырьком по возрасту
	 */
	public void bubleSortByAge() {
		bubleSorting(new PersonAgeComparator());
	}
	
	/**
	 * Быстрая сортироовка (Хоара)
	 * @param start
	 * @param end
	 */
	private void doSort(PersonComparator comparator, int start, int end) {
		if(start >= end)
			return;
		int i = start, j = end;
		int cur = i - (i - j)/2;
		while (i < j) {
			while (i < cur && (comparator.compare(rep[i], rep[cur]) >= 0))
				i++;
			while (j > cur && (comparator.compare(rep[cur], rep[j]) >= 0))
				j--;
			if (i < j) {
				Person temp = rep[i];
				rep[i] = rep[j];
				rep[j] = temp;
				if (i == cur)
					cur = j;
				else if (j == cur)
					cur = i;				
			}
		}
		doSort(comparator, start, cur);
		doSort(comparator,cur+1, end);
	}
	
	/**
	 * Быстрая сортироовка (Хоара)
	 */
	private void qsort(PersonComparator comparator) {
		int startIndex = 0;
		int endIndex = rep.length - 1;
		doSort (comparator, startIndex, endIndex);
	}

    /**
     * быстрая сортировка по фамилии
     */
    public void qSortByFio() {
        qsort(new PersonLastNameComparator());
    }

    /**
     * быстрая сортировка по возрасту
     */
    public void qSortByAge() {
        qsort(new PersonAgeComparator());
    }

    /**
     * @return count elements of repository
     */
	public int lengthRep(){
        return rep.length;
    }

    public String lastnameRep(){
		return rep[0].lastName;
	}
}
