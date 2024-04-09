package org.example;

import org.example.classes.Person;
import org.example.classes.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        Person.run();
        Person person = new Person("geniuus", 26, "Male");
        person.walk();

        Person personWithBuilder = new PersonBuilder()
                .name("geniuus")
                .age(26)
                .sex("Male")
                .build();

        personWithBuilder.walk();
    }
}