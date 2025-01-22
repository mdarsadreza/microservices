package com.employee.utils;

import java.util.*;

class Employee implements Comparable<Employee> {
    private String name;
    private int age;

    // Constructor
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Employee other) {
        // Comparing by name first (alphabetical order)
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + "}";
    }
}

public class CompareToAndCompairing {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 30));
        employees.add(new Employee("Bob", 25));
        employees.add(new Employee("Alice", 28));
        employees.add(new Employee("Alice", 25));
        employees.add(new Employee("Charlie", 35));

        Collections.sort(employees);

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("*******************************************");
        employees.sort(Comparator.comparing(Employee::getName).thenComparing(Employee::getAge));

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
