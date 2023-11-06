package org.students.homework2;

import org.students.homework2.commandsettings.CommandBuilder;
import org.students.homework2.commandsettings.CommandName;
import org.students.homework2.services.FileDataLoader;
import org.students.homework2.services.StudentService;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) throws Exception {
        FileDataLoader data = new FileDataLoader();
        StudentService studentService = new StudentService(data);
        studentService.loadData("students2.csv");

        CommandBuilder commandBuilder = new CommandBuilder(studentService);
        commandBuilder.printInfo();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean done = false;
            while (!done) {
                switch (scanner.next()) {
                    case "1" -> commandBuilder.build(CommandName.GET_STUDENT).execute();
                    case "2" -> commandBuilder.build(CommandName.GET_MEAN_MARK).execute();
                    case "3" -> commandBuilder.build(CommandName.GET_EXCELLENT_STUDENTS_ABOVE_SOME_AGE).execute();
                    case "4" -> done = true;
                    default -> System.out.println("There is no such command");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}