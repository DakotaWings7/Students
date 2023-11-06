package org.students.homework2.commandsettings;

import org.students.homework2.commandsettings.commands.GetExcellentStudentsAboveSomeAgeCommand;
import org.students.homework2.commandsettings.commands.GetMeanMarkCommand;
import org.students.homework2.commandsettings.commands.GetStudentCommand;
import org.students.homework2.services.StudentService;

import static org.students.homework2.commandsettings.CommandName.*;

public class CommandBuilder {
    private StudentService studentService;
    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
    }

    public void printInfo() {
        System.out.println("Выберите одну команду на выполнение:");
        System.out.println("1) Найти студента(ов) по фамилии;");
        System.out.println("2) Найти средний балл у студентов 10 и 11 классов;");
        System.out.println("3) Найти отличника(ов) старше 14 лет;");
        System.out.println("4) Выход.");
    }
    public Command build(CommandName commandName) {
        Command command = null;

        switch (commandName) {
            case GET_MEAN_MARK -> command = new GetMeanMarkCommand(studentService);
            case GET_EXCELLENT_STUDENTS_ABOVE_SOME_AGE -> command = new GetExcellentStudentsAboveSomeAgeCommand(studentService, 14);
            case GET_STUDENT -> command = new GetStudentCommand(studentService);
        }

        return command;
    }
}
