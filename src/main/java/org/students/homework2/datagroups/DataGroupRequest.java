package org.students.homework2.datagroups;

import org.students.homework1.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGroupRequest {
    public static double getMeanMark(DataGroup dataGroup, int group) {
        Person[] persons = dataGroup.getPersons(group);

        double sumOfMeanMarks = 0;
        for (Person person : persons) {
            sumOfMeanMarks += person.meanMark();
        }

        return sumOfMeanMarks / persons.length;
    }

    public static List<Person> getPerson(DataGroup dataGroup, String surname) {
        Person[] persons = dataGroup.getPersons(String.valueOf(surname.charAt(0)));

        List<Person> students = new ArrayList<>();
        for (Person person : persons) {
            if (person.getSurname().equals(surname)) {
                students.add(person);
            }
        }
        return students;
    }

    public static List<Person> getExcellentPersonsInAgeGroup(DataGroup dataGroup, int age) {
        Person[] persons = dataGroup.getPersons(age);

        List<Person> studentsWithExcellentMarks = new ArrayList<>();
        for (Person person : persons) {
            if (person.isExcellentStudent()) {
                studentsWithExcellentMarks.add(person);
            }
        }
        return studentsWithExcellentMarks;
    }
}
