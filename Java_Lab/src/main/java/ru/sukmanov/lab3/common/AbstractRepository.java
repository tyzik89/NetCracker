package ru.sukmanov.lab3.common;

import ru.sukmanov.lab3.object.Car;
import ru.sukmanov.lab3.object.Person;
import ru.sukmanov.lab3.repositories.CarRepository;
import ru.sukmanov.lab3.repositories.PersonRepository;
import ru.sukmanov.lab3.sorting.ObjectComparator;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
@XmlSeeAlso({PersonRepository.class, CarRepository.class})
public abstract class AbstractRepository<T> implements InterfaceRepository<T> {

    private List<T> list = new ArrayList<T>();

    public AbstractRepository () {}

    public void addElems(List<T> tList) {
        list.addAll(tList);
    }

    public void showRepository() {
        for (T t : list) {
            System.out.println(t.toString());
        }
    }

    @XmlElements({
            @XmlElement(name = "Person", type = Person.class),
            @XmlElement(name = "Car", type = Car.class)
    })
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public T getElem(int i) {
        return list.get(i);
    }

    @Override
    public void addElem(T t) {
        list.add(t);
    }

    @Override
    public void delElem(int i) {
        list.remove(i);
    }

    /*public AbstractRepository<T> findElem(Predicate<T> predicate){
        AbstractRepository<T> repFindElem = getRepositoryInstance();
        for (Object o : rep) {
            if ((o != null) &&(predicate.test((T) o))){
                repFindElem.addElem((T)o);
            }
        }
        return repFindElem;
    }*/

    protected abstract AbstractRepository<T> getRepositoryInstance();

    @Override
    public String toString() {
        return "RepositoryImpl{" +
                "list=" + list +
                '}';
    }

    /**
     * <p>Сортировка пузырьком</p>
     * @param comparator
     */
    protected void bubleSorting(ObjectComparator comparator) {
        Object temp;
        boolean sort = true;
        for(int i = 0; i < list.size() - 1; i++ ) {
            if ((comparator.compare(list.get(i + 1), list.get(i)) < 0)) {
                temp = list.get(i);
                list.set(i, list.get(i + 1));
                list.set(i + 1, (T) temp);
                sort = false;
            }
        }
        if (!sort) {
            bubleSorting(comparator);
        }
    }

    /**
     * <p>Быстрая сортировка(инициализация)</p>
     * @param comparator
     */
    protected void qsort(ObjectComparator comparator) {
        int startIndex = 0;
        int endIndex = list.size() - 1;
        doSort (comparator, startIndex, endIndex);
    }

    /**
     * <p>Быстрая сортировка(алгоритм)</p>
     * @param comparator
     * @param start Начало сортируемого массива
     * @param end Конец сортируемого массива
     */
    private void doSort(ObjectComparator comparator, int start, int end) {
        if(start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j)/2;
        while (i < j) {
            while ((i < cur) && (comparator.compare(list.get(i), list.get(cur)) >= 0))
                i++;
            while ((j > cur) && (comparator.compare(list.get(cur), list.get(j)) >= 0))
                j--;
            if (i < j) {
                Object temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, (T) temp);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(comparator, start, cur);
        doSort(comparator,cur+1, end);
    }
}
