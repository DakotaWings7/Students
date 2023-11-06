package org.students.homework2.commandsettings.commands;

import org.students.homework1.Person;
import org.students.homework2.datagroups.DataGroup;
import org.students.homework2.commandsettings.Command;
import org.students.homework2.datagroups.DataGroupRequest;
import org.students.homework2.services.StudentService;

public class GetMeanMarkCommand implements Command {
    private StudentService studentService;

    public GetMeanMarkCommand(StudentService studentService) {
        this.studentService = studentService;
    }
    @Override
    public void execute() {
        DataGroup<Integer> dataGroup = new DataGroup<>(Person::getGroup);
        studentService.getStudentList().forEach(dataGroup::addPerson);

        System.out.printf("Средняя оценка в 10 классе: %f\n", DataGroupRequest.getMeanMark(dataGroup, 10));
        System.out.printf("Средняя оценка в 11 классе: %f\n", DataGroupRequest.getMeanMark(dataGroup, 11));
    }
}
