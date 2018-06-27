package ru.testdb.dbconnect;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.testdb.dbconnect.config.AppConfig;
import ru.testdb.dbconnect.entities.Person;
import ru.testdb.dbconnect.service.PersonService;

public class DbconnectApplication {

    /*public static void main(String[] args) {
        SpringApplication.run(DbconnectApplication.class, args);
    }*/
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        PersonService personService = context.getBean(PersonService.class);

        // Add Persons
        personService.add(new Person("Vova", "Vova", "ololo@example.com"));
        personService.add(new Person("John", "Miller", "john.miller@example.com"));
        personService.add(new Person("Bill", "Smith", "bill.smith@example.com"));
        personService.add(new Person("Roger", "Pitt", "roger.pitt@example.com"));

        // Get Persons
        List<Person> persons = personService.listPersons();
        for (Person person : persons) {
            System.out.println("Id = "+person.getId());
            System.out.println("First Name = "+person.getFirstName());
            System.out.println("Last Name = "+person.getLastName());
            System.out.println("Email = "+person.getEmail());
            System.out.println();
        }

        context.close();
    }
}
