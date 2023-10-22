package org.students;

public class ClassroomDataGroups {
    private MyHashMap<Integer, Person> students = new MyHashMap<>();

    public void addPerson(Person person) {
        students.put(person.getGroup(), person);
    }

    public Person[] getPersons(int groupName) {
        return students.get(groupName);
    }

    public double getGroupMeanMark(int groupName) {
        Person[] persons = students.get(groupName);

        double sumOfMeanMarks = 0;
        for (Person person : persons) {
            sumOfMeanMarks += person.meanMark();
        }

        return sumOfMeanMarks / persons.length;
    }
}
