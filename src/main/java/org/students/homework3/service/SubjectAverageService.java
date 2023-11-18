package org.students.homework3.service;

import org.students.homework3.database.Database;
import org.students.homework3.dto.SubjectsAverageDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectAverageService {
    private Database database;

    public SubjectAverageService(Database database) {
        this.database = database;
    }

    public List<SubjectsAverageDTO> getSubjectsAverage() {
        List<SubjectsAverageDTO> result = new ArrayList<>();

        String studentQuery = "SELECT * FROM students where group_id = ?";
        String subjectQuery = "SELECT * FROM grades where student_id = ?";

        for (int group = 10; group < 12; ++group) {
            double allGrades = 0, gradesAverage;
            int studentsInGroupCount = 0;

            try (PreparedStatement studentStatement = database.getConnection().prepareStatement(studentQuery)) {
                studentStatement.setInt(1, group);

                try (ResultSet studentResultSet = studentStatement.executeQuery()) {
                    // Получил всех студентов из группы = group
                    while (studentResultSet.next()) {
                        studentsInGroupCount++;

                        try (PreparedStatement subjectsStatement = database.getConnection().prepareStatement(subjectQuery)) {
                            subjectsStatement.setInt(1, studentResultSet.getInt("id"));
                            try (ResultSet subjectResultState = subjectsStatement.executeQuery()) {
                                // Получил все оценки студента из группы = group
                                gradesAverage = 0;
                                while (subjectResultState.next()) {
                                    gradesAverage += subjectResultState.getInt("grade");
                                }
                                gradesAverage /= 6;
                            }
                        }
                        // Прибавил ко всем оценкам среднюю оценку одного студента
                        System.out.println("allGrades: " + allGrades + "; students: " + studentsInGroupCount);
                        allGrades += gradesAverage;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            SubjectsAverageDTO s = new SubjectsAverageDTO();
            s.setGroup(group);
            s.setAverageGrade(allGrades / studentsInGroupCount);
            result.add(s);
        }
        return result;
    }
}
