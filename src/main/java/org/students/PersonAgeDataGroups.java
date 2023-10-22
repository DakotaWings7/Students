package org.students;

import java.util.ArrayList;
import java.util.List;

public class PersonAgeDataGroups {
    private MyHashMap<Integer, Person> students = new MyHashMap<>();

    public void addPerson(Person person) {
        students.put(person.getAge(), person);
    }

    public Person[] getPersons(int age) {
        return students.get(age);
    }

    public List<Person> getExcellentPersonsInAgeGroup(int age) {
        Person[] persons = students.get(age);

        List<Person> studentsWithExcellentMarks = new ArrayList<>();
        for (Person person : persons) {
            if (person.isExcellentStudent()) {
                studentsWithExcellentMarks.add(person);
            }
        }
        return studentsWithExcellentMarks;
    }
}
