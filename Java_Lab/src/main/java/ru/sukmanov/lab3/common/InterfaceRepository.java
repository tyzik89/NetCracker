package ru.sukmanov.lab3.common;

public interface InterfaceRepository<T> {

    /**
     * <p>Добавление нового элемента в репозиторий</p>
     * @param elem новый элемент
     */
    void addElem(T elem);

    /**
     * <p>Удаление элемента репозитория по индексу</p>
     * @param i индекс элемента
     */
    void delElem(int i);

    /**
     * <p>Поиск элемента по предикату</p>
     * @param predicate
     * @return найденный элемент

    T findElem(Predicate<T> predicate);*/
    //T[] findElem(Predicate<T> predicate);

    /**
     * @param i
     * @return элемент
     */
    T getElem(int i);

    void showRepository();
}
