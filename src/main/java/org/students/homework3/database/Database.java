package org.students.homework3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    public Database(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(String lastName, String firstName, int age, int groupId) {
        String query = "INSERT INTO students (last_name, first_name, age, group_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            statement.setInt(3, age);
            statement.setInt(4, groupId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGrade(int studentId, String subject, int newGrade) {
        int subjectId = Subjects.valueOf(subject.toUpperCase()).ordinal() + 1;

        String query = "UPDATE grades SET grade = ? WHERE student_id = ? and subject_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newGrade);
            statement.setInt(2, studentId);
            statement.setInt(3, subjectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getStudentInfo(int studentId) {
        String studentQuery = "SELECT * FROM students WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(studentQuery)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String lastName = resultSet.getString("last_name");
                    String firstName = resultSet.getString("first_name");
                    int age = resultSet.getInt("age");
                    int group = resultSet.getInt("group_id");
                    System.out.print("Student: " + lastName + " " + firstName + ", Возраст: " + age + ", Группа: " + group);

                    getStudentGrades(studentId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getStudentGrades(int studentId) {
        String gradesQuery = "SELECT * FROM grades WHERE student_id = ?";

        int[] marks = new int[6];
        try (PreparedStatement statement = connection.prepareStatement(gradesQuery)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    marks[resultSet.getInt("subject_id") - 1] = resultSet.getInt("grade");
                }

                System.out.println("Grades: physics=" + marks[0] + " maths=" + marks[1] + " rus=" + marks[2]
                                    + " literature=" + marks[3] + " geometry=" + marks[4] + " informatics=" + marks[5]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}