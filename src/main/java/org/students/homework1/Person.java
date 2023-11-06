package org.students.homework1;

import java.util.Arrays;

public class Person {
    private String name;
    private String surname;
    private int age;
    private int group;
    private int[] marks;


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstLetterOfSurname() {
        return String.valueOf(surname.charAt(0));
    }

    public int getAge() {
        return age;
    }

    public int getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    public boolean isExcellentStudent() {
        return Arrays.stream(marks).allMatch(x -> x == 5);
    }

    public double meanMark() {
        return (double) Arrays.stream(marks).sum() / marks.length;
    }
    public Person(String name, String surname, int age, int group, int[] marks) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.group = group;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", group=" + group +
                ", marks=" + Arrays.toString(marks);
    }
}
