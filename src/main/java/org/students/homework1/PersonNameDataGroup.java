package org.students.homework1;

import java.util.ArrayList;
import java.util.List;

public class PersonNameDataGroup {
    private MyHashMap<String, Person> students = new MyHashMap<>();

    public void addPerson(Person person) {
        students.put(String.valueOf(person.getSurname().charAt(0)), person);
    }

    public Person[] getPersons(String firstLetter) {
        return students.get(firstLetter);
    }

    public List<Person> getPerson(String surname) {
        Person[] persons = students.get(String.valueOf(surname.charAt(0)));

        List<Person> students = new ArrayList<>();
        for (Person person : persons) {
            if (person.getSurname().equals(surname)) {
                students.add(person);
            }
        }
        return students;
    }
}
