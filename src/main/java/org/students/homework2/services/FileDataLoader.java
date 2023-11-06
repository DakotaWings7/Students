package org.students.homework2.services;

import org.students.homework1.Person;
import org.students.homework2.services.DataLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataLoader implements DataLoader {

    @Override
    public List<Person> load(String path) {
        List<Person> people = new ArrayList<>();

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
                people.add(person);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return people;
    }
}
