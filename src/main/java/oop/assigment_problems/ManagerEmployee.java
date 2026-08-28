package oop.assigment_problems;

/**
 * F2. A manager IS an employee, plus a team bonus.
 * "extends Employee" means we inherit empId, empName, salary and getSalary()
 * for free, without copying or editing a single line of Employee.java.
 */
public class ManagerEmployee extends Employee {

    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        // super(...) runs Employee's constructor first, so the inherited
        // fields get filled in. It must be the very first line.
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    // New behaviour that only managers have.
    // We cannot read the private 'salary' field directly, so we use the
    // inherited public getSalary() method.
    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}