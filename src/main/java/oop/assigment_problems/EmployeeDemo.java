package oop.assigment_problems;

/**
 * F2. Driver class - creates one of each employee type and uses instanceof
 * to decide which extra behaviour applies to each one.
 */
public class EmployeeDemo {

    public static void main(String[] args) {

        // One of each type. Note the declared type is Employee for all three:
        // a ManagerEmployee IS-A Employee, so it fits in an Employee box.
        Employee plain = new Employee("E101", "Karan", 40000);
        Employee manager = new ManagerEmployee("E102", "Divya", 70000, 8000);
        Employee intern = new InternEmployee("E103", "Meera", 12000, 10000);

        Employee[] staff = { plain, manager, intern };

        for (int i = 0; i < staff.length; i++) {
            Employee e = staff[i];

            // instanceof asks at RUNTIME: "what kind of object is this really?"
            // We must check the subclasses FIRST, because a ManagerEmployee is
            // also an Employee - if we checked Employee first it would match
            // everything and the special cases would never run.
            if (e instanceof ManagerEmployee) {
                ManagerEmployee m = (ManagerEmployee) e;   // cast to unlock effectiveSalary()
                System.out.println("Manager effective pay: Rs " + m.effectiveSalary());

            } else if (e instanceof InternEmployee) {
                InternEmployee in = (InternEmployee) e;
                System.out.println("Intern effective pay: Rs " + in.effectiveSalary());

            } else {
                System.out.println("Plain employee pay: Rs " + e.getSalary());
            }
        }
    }
}