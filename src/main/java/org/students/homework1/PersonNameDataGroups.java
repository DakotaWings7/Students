package org.students.homework1;

import java.util.ArrayList;
import java.util.List;

public class PersonNameDataGroups {
    private MyHashMap<String, Person> students = new MyHashMap();

    public PersonNameDataGroups() {
    }

    public void addPerson(Person person) {
        this.students.put(String.valueOf(person.getSurname().charAt(0)), person);
    }

    public Person[] getPersons(String firstLetter) {
        return this.students.get(firstLetter);
    }

    public List<Person> getPerson(String surname) {
        Person[] persons = this.students.get(String.valueOf(surname.charAt(0)));
        List<Person> students = new ArrayList();
        Person[] var4 = persons;
        int var5 = persons.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            Person person = var4[var6];
            if (person.getSurname().equals(surname)) {
                students.add(person);
            }
        }

        return students;
    }
}