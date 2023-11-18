package org.students.homework1;

import java.util.ArrayList;
import java.util.List;

public class PersonAgeDataGroups {
    private MyHashMap<Integer, Person> students = new MyHashMap();

    public PersonAgeDataGroups() {
    }

    public void addPerson(Person person) {
        this.students.put(person.getAge(), person);
    }

    public Person[] getPersons(int age) {
        return this.students.get(age);
    }

    public List<Person> getExcellentPersonsInAgeGroup(int age) {
        Person[] persons = this.students.get(age);
        List<Person> studentsWithExcellentMarks = new ArrayList();
        Person[] var4 = persons;
        int var5 = persons.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Person person = var4[var6];
            if (person.isExcellentStudent()) {
                studentsWithExcellentMarks.add(person);
            }
        }

        return studentsWithExcellentMarks;
    }
}