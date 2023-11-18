package org.students.homework3.database;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseFiller {
    public static void fillDataBaseFromCsv(Database database, String csvFile) throws CsvValidationException, SQLException {
        String insertStudent = "INSERT INTO students(last_name, first_name, age, group_id) VALUES (?, ?, ?, ?)";
        String insertGrades = "INSERT INTO grades(student_id, subject_id, grade) VALUES (?, ?, ?)";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;
            reader.readNext();

            int i = 1;
            while ((nextLine = reader.readNext()) != null) {
                nextLine = nextLine[0].split(";");

                try (PreparedStatement statement = database.getConnection().prepareStatement(insertStudent)) {
                    statement.setString(1, nextLine[0]);
                    statement.setString(2, nextLine[1]);
                    statement.setInt(3, Integer.parseInt(nextLine[2]));
                    statement.setInt(4, Integer.parseInt(nextLine[3]));

                    statement.executeUpdate();
                }

                try (PreparedStatement statement = database.getConnection().prepareStatement(insertGrades)) {
                    for (int j = 1; j < 7; ++j) {
                        statement.setInt(1, i);
                        statement.setInt(2, j);
                        statement.setInt(3, Integer.parseInt(nextLine[j + 3]));
                        statement.executeUpdate();
                    }
                }

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}