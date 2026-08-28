package oop.assigment_problems;

/**
 * F2. The original, already-tested Employee class.
 *
 * IMPORTANT: this file is never reopened or edited again. Every new kind of
 * employee is added by EXTENDING this class, not by changing it.
 */
public class Employee {

    // private = only this class can touch these directly (encapsulation)
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    // The outside world reads the salary through this method, not the field.
    public double getSalary() {
        return salary;
    }
}