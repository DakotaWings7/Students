package org.students.homework2.commandsettings.commands;

import org.students.homework1.Person;
import org.students.homework2.commandsettings.Command;
import org.students.homework2.datagroups.DataGroup;
import org.students.homework2.datagroups.DataGroupRequest;
import org.students.homework2.services.StudentService;

import java.util.ArrayList;
import java.util.List;

public class GetExcellentStudentsAboveSomeAgeCommand implements Command {
    private StudentService studentService;

    private int age;

    public GetExcellentStudentsAboveSomeAgeCommand(StudentService studentService, int age) {
        this.studentService = studentService;
        this.age = age;
    }

    @Override
    public void execute() {
        DataGroup<Integer> ageData = new DataGroup<>(Person::getAge);
        studentService.getStudentList().forEach(ageData::addPerson);

        List<Person> excellentStudents = new ArrayList<>();
        for (int i = age; i < 18; ++i) {
            excellentStudents.addAll(DataGroupRequest.getExcellentPersonsInAgeGroup(ageData, i));
        }
        System.out.println(excellentStudents);
    }
}
