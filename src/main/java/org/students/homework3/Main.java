package org.students.homework3;

import com.opencsv.exceptions.CsvValidationException;
import org.students.homework3.database.Database;

import java.sql.*;


/*
* Попробовал реализовать somethingDTO
* работает слишком медленно.
* Попробовал реализовать отдельно DTO - сразу заполнить список Person
* - ещё дольше
*
* С работой с БД разобрался: подключение, заполнение и т.д., лишь не понял реализацию
* Data Transfer Object
* */

public class Main {
    public static void main(String[] args) throws SQLException, CsvValidationException {
        String url = "jdbc:postgresql://localhost:5432/mydatabase";
        String user = "postgres";
        String password = "Ronaldo2003";

        Database database = new Database(url, user, password);

        //DataBaseFiller.fillDataBaseFromCsv(database, "students2.csv");

        database.close();
    }
}
