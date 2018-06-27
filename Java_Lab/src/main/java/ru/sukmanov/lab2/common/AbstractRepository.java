package ru.sukmanov.lab2.common;

import org.junit.runners.model.TestClass;
import ru.sukmanov.lab2.sorting.ObjectComparator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Predicate;

public abstract class AbstractRepository<T> implements InterfaceRepository<T> {
    protected int sizeRep = 10;
    protected int elemInRep = 0;
    protected Object[] rep = new Object[sizeRep];

    /**
     * <p>Печать элементов репозитория</p>
     */
    public void showRepository(){
        for (int i = 0; i < elemInRep; i++) {
            System.out.println(rep[i].toString());
        }
    }

    /**
     * <p>Получение элемента репозитория по его индексу</p>
     * @param index Индекс элемента в репозитории
     * @return Элемент репозитория типа Object
     */
    public T getElem(int index){
        return (T) rep[index];
    }

    /**
     * <p>Возврат длины репозитория</p>
     * @return количество элементов репозитория
     */
    public int size(){
        return elemInRep;
    }

    /**
     * <p>Добавление нового элемента в репозиторий</p>
     * @param elem новый элемент
     */
    public void addElem(T elem){
        if (elemInRep >= sizeRep) {
            sizeRep = sizeRep + 10;
            Object[] tempRep = (Object[])new Object[sizeRep];
            System.arraycopy(rep, 0, tempRep, 0, elemInRep);
            rep = tempRep;
        }
        rep[elemInRep] = elem;
        elemInRep++;
    }

    /**
     * <p>Удаление элемента по индексу</p>
     * @param i индекс элемента
     */
    public void delElem(int i){
        if (i <= sizeRep - 1) {
            Object[] tempRep = (Object[])new Object[sizeRep - 1];
            System.arraycopy(rep, 0, tempRep, 0, i + 1);
            System.arraycopy(rep, i + 1, tempRep, i, sizeRep - i - 1);;
            rep = tempRep;
            sizeRep--;
            elemInRep--;
        }
    }

    /**
     * <p>Поиск элемента по предикату</p>
     * @param predicate
     * @return найденный элемент

    public T findElem(Predicate<T> predicate){
        for (int i=0; i < elemInRep; i++) {
            if (predicate.test((T) rep[i])){
                return (T) rep[i];
            }
        }
        return null;
    }
    public T[] findElem(Predicate<T> predicate){
        int sizeFindRep = 1;
        int elemInFindRep = 0;
        T[] repFindElem = (T[])new Object[sizeFindRep];
        for (Object o : rep) {
            if ((o != null) &&(predicate.test((T) o))){
                if (elemInFindRep >= sizeFindRep) {
                    sizeFindRep++;
                    T[] tempFindRep = (T[])new Object[sizeFindRep];
                    System.arraycopy(repFindElem, 0, tempFindRep, 0, elemInFindRep);
                    repFindElem = tempFindRep;
                }
                repFindElem[elemInFindRep] = (T)o;
                elemInFindRep++;
            }
        }
        return (T[]) repFindElem;
    }*/

    public AbstractRepository<T> findElem(Predicate<T> predicate){
        AbstractRepository<T> repFindElem = getRepositoryInstance();
        for (Object o : rep) {
            if ((o != null) &&(predicate.test((T) o))){
                repFindElem.addElem((T)o);
            }
        }
        return repFindElem;
    }

    protected abstract AbstractRepository<T> getRepositoryInstance();


    /**
     * <p>Сортировка пузырьком</p>
     * @param comparator
     */
    protected void bubleSorting(ObjectComparator comparator) {
        Object temp;
        boolean sort = true;
        for(int i = 0; i < elemInRep - 1; i++ ) {
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
     * <p>Быстрая сортировка(инициализация)</p>
     * @param comparator
     */
    protected void qsort(ObjectComparator comparator) {
        int startIndex = 0;
        int endIndex = elemInRep - 1;
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
            while ((i < cur) && (comparator.compare(rep[i], rep[cur]) >= 0))
                i++;
            while ((j > cur) && (comparator.compare(rep[cur], rep[j]) >= 0))
                j--;
            if (i < j) {
                Object temp = rep[i];
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
}
