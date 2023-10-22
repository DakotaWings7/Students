package org.students;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// - Поскольку задачи были в целом на демонстрирование работоспособности программы,
// решил оставить их выполнение прямо в main, не вынося в отдельные функции.
// - По поводу 3 задачи решил вывести список с учениками, поскольку нельзя выделить
// единственного ученика по одной лишь фамилии
// - Для решения задачи использовал следующие структуры данных, реализованные мной:
// HashMap - поскольку нам нужен быстрый доступ к элементу и данные по задаче нужно
// хранить по парам (ключ, значение : возраст, список человек; группа, список человек и т.д.)
// LinkedList - поскольку по задаче мы работаем только с концом списка.
// Добавил в классе Node указатель на "хвост" списка для константного
// добавления в список. Tail значительно ускорил работу программы.

public class Main {
    public static void main(String[] args) {
        ClassroomDataGroups classroomDataGroups = new ClassroomDataGroups();
        PersonAgeDataGroups personAgeDataGroups = new PersonAgeDataGroups();
        PersonNameDataGroup personNameDataGroup = new PersonNameDataGroup();

        String line, name, surname;
        String[] data;
        int group, age;
        int[] marks;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("students2.csv"));
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
        System.out.println(classroomDataGroups.getGroupMeanMark(10));
        System.out.println(classroomDataGroups.getGroupMeanMark(11));

        System.out.println(personAgeDataGroups.getExcellentPersonsInAgeGroup(15));
        System.out.println(personAgeDataGroups.getExcellentPersonsInAgeGroup(16));
        System.out.println(personAgeDataGroups.getExcellentPersonsInAgeGroup(17));

        System.out.println(personNameDataGroup.getPerson("Басов"));
        System.out.println(personNameDataGroup.getPerson("Абрамов"));

        //System.out.println(Arrays.toString(classroomDataGroups.getPersons(8)));
    }
}