package ru.sukmanov.somebean;

import ru.sukmanov.annotations.AutoInjectable;
import ru.sukmanov.someinterfaces.SomeInterface;
import ru.sukmanov.someinterfaces.SomeOtherInterface;

/**
 * Класс, имеющий поля с аннотациями
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}
