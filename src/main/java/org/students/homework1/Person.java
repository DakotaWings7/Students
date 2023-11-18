package org.students.homework1;

import java.util.Arrays;

public class Person {
    private String name;
    private String surname;
    private int age;
    private int group;
    private int[] marks;

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getFirstLetterOfSurname() {
        return String.valueOf(this.surname.charAt(0));
    }

    public int getAge() {
        return this.age;
    }

    public int getGroup() {
        return this.group;
    }

    public int[] getMarks() {
        return this.marks;
    }

    public boolean isExcellentStudent() {
        return Arrays.stream(this.marks).allMatch((x) -> {
            return x == 5;
        });
    }

    public double meanMark() {
        return (double)Arrays.stream(this.marks).sum() / (double)this.marks.length;
    }

    public Person(String name, String surname, int age, int group, int[] marks) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.group = group;
        this.marks = marks;
    }

    public String toString() {
        String var10000 = this.surname;
        return "surname='" + var10000 + "', name='" + this.name + "', age=" + this.age + ", group=" + this.group + ", marks=" + Arrays.toString(this.marks);
    }
}