package ru.sukmanov.lab2.repositories;

import ru.sukmanov.lab2.common.AbstractRepository;

public class MyRepository<T> extends AbstractRepository<T> {

    /**
     * <p>Конструктор MyRepository</p>
     * @param <T>
     */
    public <T> MyRepository(){
        //this.rep = new Object[sizeRep];
    }

    /**
     * <p>Отобразить элементы репозитория</p>
     */
    public void showRepository(){
        for (int i = 0; i < elemInRep; i++) {
            System.out.println(rep[i].toString());
        }
    }

    @Override
    protected AbstractRepository<T> getRepositoryInstance() {
        return null;
    }
}
