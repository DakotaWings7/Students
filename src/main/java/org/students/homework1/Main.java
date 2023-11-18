//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.students.homework1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        ClassroomDataGroups classroomDataGroups = new ClassroomDataGroups();
        PersonAgeDataGroups personAgeDataGroups = new PersonAgeDataGroups();
        PersonNameDataGroups personNameDataGroup = new PersonNameDataGroups();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("students2.csv"));

            try {
                reader.readLine();

                String line;
                while((line = reader.readLine()) != null) {
                    String[] data = line.split(";");
                    String name = data[0];
                    String surname = data[1];
                    int group = Integer.parseInt(data[2]);
                    int age = Integer.parseInt(data[3]);
                    int[] marks = new int[]{Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9])};
                    Person person = new Person(surname, name, group, age, marks);
                    classroomDataGroups.addPerson(person);
                    personAgeDataGroups.addPerson(person);
                    personNameDataGroup.addPerson(person);
                }
            } catch (Throwable var15) {
                try {
                    reader.close();
                } catch (Throwable var14) {
                    var15.addSuppressed(var14);
                }

                throw var15;
            }

            reader.close();
        } catch (IOException var16) {
            throw new RuntimeException(var16);
        }
    }
}
