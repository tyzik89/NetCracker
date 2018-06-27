package ru.sukmanov;

import ru.sukmanov.injector.Injector;
import ru.sukmanov.somebean.SomeBean;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //SomeBean someBean = new SomeBean();
        //someBean.foo();
        SomeBean sb = (new Injector()).inject(new SomeBean());
        sb.foo();
    }
}
