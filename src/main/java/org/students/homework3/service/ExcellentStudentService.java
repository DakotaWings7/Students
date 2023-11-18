package org.students.homework3.service;

import org.students.homework3.database.Database;
import org.students.homework3.dto.ExcellentStudentDTO;
import org.students.homework3.dto.StudentAverageDTO;
import org.students.homework3.dto.SubjectsAverageDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcellentStudentService {
    private Database database;

    public ExcellentStudentService(Database database) {
        this.database = database;
    }

    public List<ExcellentStudentDTO> getSubjectsAverage(String last_name) {
        List<ExcellentStudentDTO> result = new ArrayList<>();

        String studentQuery = "SELECT * FROM students where last_name = ?";
        String subjectQuery = "SELECT * FROM grades where student_id = ?";

        boolean isExcellentStudent;
        try (PreparedStatement studentStatement = database.getConnection().prepareStatement(studentQuery)) {
            studentStatement.setString(1, last_name);

            try (ResultSet studentResultSet = studentStatement.executeQuery()) {
                // Получил всех студентов по фамилии = last_name
                while (studentResultSet.next()) {

                    try (PreparedStatement subjectsStatement = database.getConnection().prepareStatement(subjectQuery)) {
                        subjectsStatement.setInt(1, studentResultSet.getInt("id"));
                        try (ResultSet subjectResultState = subjectsStatement.executeQuery()) {
                            // Получил все оценки студента по фамилии = last_name
                            isExcellentStudent = true;
                            while (subjectResultState.next()) {
                                if (subjectResultState.getInt("grade") != 5) {
                                    isExcellentStudent = false;
                                    break;
                                }
                            }
                        }
                    }

                    if (isExcellentStudent) {
                        ExcellentStudentDTO s = new ExcellentStudentDTO();
                        s.setAge(studentResultSet.getInt("age"));
                        s.setFullName(studentResultSet.getString("last_name") + " " + studentResultSet.getString("first_name"));
                        s.setGroup(studentResultSet.getInt("group"));
                        result.add(s);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
