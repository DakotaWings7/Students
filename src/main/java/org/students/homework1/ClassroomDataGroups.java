package org.students.homework1;

public class ClassroomDataGroups {
    private MyHashMap<Integer, Person> students = new MyHashMap();

    public ClassroomDataGroups() {
    }

    public void addPerson(Person person) {
        this.students.put(person.getGroup(), person);
    }

    public Person[] getPersons(int groupName) {
        return this.students.get(groupName);
    }

    public double getGroupMeanMark(int groupName) {
        Person[] persons = this.students.get(groupName);
        double sumOfMeanMarks = 0.0;
        Person[] var5 = persons;
        int var6 = persons.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Person person = var5[var7];
            sumOfMeanMarks += person.meanMark();
        }

        return sumOfMeanMarks / (double)persons.length;
    }
}
