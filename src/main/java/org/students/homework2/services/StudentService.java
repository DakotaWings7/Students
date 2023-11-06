package org.students.homework2.services;

import org.students.homework1.Person;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final DataLoader<Person> dataLoader;
    private List<Person> people;

    public StudentService(DataLoader<Person> dataLoader) {
        this.dataLoader = dataLoader;
        people = new ArrayList<>();
    }

    public void loadData(String path) throws Exception {
        people = dataLoader.load(path);
    }

    public List<Person> getStudentList() {
        return people;
    }
}