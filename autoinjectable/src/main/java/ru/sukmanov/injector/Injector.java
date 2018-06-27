package ru.sukmanov.injector;

import ru.sukmanov.annotations.AutoInjectable;
import ru.sukmanov.properties_reader.PropertyReader;
import ru.sukmanov.somebean.SomeBean;
import ru.sukmanov.someclasses.OtherImpl;
import ru.sukmanov.someclasses.SomeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Injector{

    public<T> T inject(T object) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Получаем класс по имени класса в пакете
        Class cl = Class.forName(object.getClass().getName().toString());
        //Создаём экземпляр класса
        Object obj = cl.newInstance();
        //Получаем массив всех полей (в том числе private) созданного объекта
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            //Ищем аннотацию AutoInjectable в каждом поле, получаем её
            Annotation ann = field.getAnnotation(AutoInjectable.class);
            //Включаем разрешение, на работу с private полями
            field.setAccessible(true);
            //Если аннотация AutoInjectable существует
            if (ann != null) {
                //Читаем свойство из конфига по типу поля с аннотацией
                String className = PropertyReader.readProperty(field.getType().getName());
                //Загружаем класс по имени из конфига
                Class cn = Class.forName(className);
                //Инициализируем поле, помеченное аннотацией, новым экземпляром класса
                field.set(object, cn.newInstance());
            }

        }
        return object;
    }
}
