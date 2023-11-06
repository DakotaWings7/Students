package org.students.homework2.commandsettings.commands;

import org.students.homework1.Person;
import org.students.homework2.commandsettings.Command;
import org.students.homework2.datagroups.DataGroup;
import org.students.homework2.datagroups.DataGroupRequest;
import org.students.homework2.services.StudentService;

import java.util.Scanner;

public class GetStudentCommand implements Command {
    private StudentService studentService;

    public GetStudentCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        DataGroup<String> surnameData = new DataGroup<>(Person::getFirstLetterOfSurname);
        studentService.getStudentList().forEach(surnameData::addPerson);

        System.out.println("Введите фамилию: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println(DataGroupRequest.getPerson(surnameData, scanner.nextLine()));
    }
}
