package org.students.homework1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ClassroomDataGroups classroomDataGroups = new ClassroomDataGroups();
        PersonAgeDataGroups personAgeDataGroups = new PersonAgeDataGroups();
        PersonNameDataGroup personNameDataGroup = new PersonNameDataGroup();

        String line, name, surname;
        String[] data;
        int group, age;
        int[] marks;

        try (BufferedReader reader = new BufferedReader(new FileReader("students2.csv"))) {
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                data = line.split(";");
                name = data[0];
                surname = data[1];
                group = Integer.parseInt(data[2]);
                age = Integer.parseInt(data[3]);
                marks = new int[]{Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9])};
                Person person = new Person(surname, name, group, age, marks);
                classroomDataGroups.addPerson(person);
                personAgeDataGroups.addPerson(person);
                personNameDataGroup.addPerson(person);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}