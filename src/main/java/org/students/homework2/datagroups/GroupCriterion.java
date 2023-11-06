package org.students.homework2.datagroups;

import org.students.homework1.Person;

@FunctionalInterface
public interface GroupCriterion<T> {
    T getGroupKey(Person person);
}