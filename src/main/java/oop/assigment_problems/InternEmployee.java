package oop.assigment_problems;

/**
 * F2. An intern IS an employee, but their pay is capped by a stipend limit.
 */
public class InternEmployee extends Employee {

    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    // Whichever is SMALLER: the salary or the cap.
    public double effectiveSalary() {
        if (getSalary() < stipendCap) {
            return getSalary();
        }
        return stipendCap;
    }
}