package org.students.homework2.datagroups;

import org.students.homework1.MyHashMap;
import org.students.homework1.Person;

public class DataGroup<T> {
    private MyHashMap<T, Person[]> group;
    private GroupCriterion<T> groupCriterion;

    public DataGroup(GroupCriterion groupCriterion) {
        this.groupCriterion = groupCriterion;
        group = new MyHashMap<>();
    }

    public void addPerson(Person person) {
        T groupKey = groupCriterion.getGroupKey(person);
        group.put(groupKey, person);
    }

    public Person[] getPersons(T key) {
        return group.get(key);
    }

    @Override
    public String toString() {
        return group.toString();
    }
}