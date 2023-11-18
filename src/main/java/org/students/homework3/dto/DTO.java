package org.students.homework3.dto;

import org.students.homework1.Person;
import org.students.homework3.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTO {
    List<Person> personList;

    public DTO() {
        personList = new ArrayList<>();
    }

    public void fillDTO(Database database) {
        String studentQuery = "SELECT * FROM students";
        String subjectQuery = "SELECT * FROM grades";

        int studentsInGroupCount = 0;
        try (PreparedStatement studentStatement = database.getConnection().prepareStatement(studentQuery)) {
            try (ResultSet studentResultSet = studentStatement.executeQuery()) {
                // Получил всех студентов
                while (studentResultSet.next()) {
                    studentsInGroupCount++;

                    int[] curGrades = new int[6];
                    try (PreparedStatement subjectsStatement = database.getConnection().prepareStatement(subjectQuery)) {
                        try (ResultSet subjectResultSet = subjectsStatement.executeQuery()) {
                            // Получил все оценки студента из группы = group
                            int i = 0;
                            while (subjectResultSet.next()) {
                                curGrades[i] = subjectResultSet.getInt("grade");
                            }
                        }
                    }
                    // Прибавил ко всем оценкам среднюю оценку одного студента
                    String name = studentResultSet.getString("first_name");
                    String surname = studentResultSet.getString("last_name");
                    int age = studentResultSet.getInt("age");
                    int group = studentResultSet.getInt("group_id");
                    int[] grades = curGrades;
                    personList.add(new Person(name, surname, age, group, curGrades));
                    System.out.println("Students count: " + studentsInGroupCount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
