package oop.assigment_problems;

/**
 * F5. Capstone: a small HR + parking allocation mini-system.
 *
 * Reuses Employee / ManagerEmployee (F2) and ParkingSlot (F3).
 * Demonstrates COMPOSITION: an object whose own fields are other objects.
 */
public class CompanyEmployeeRecord {

    // Plain data fields
    String name;
    String empId;

    // Fields that are themselves OBJECTS - this is composition.
    Employee employee;
    ParkingSlot slot;      // may be null: not everyone gets parking

    // Shared across all records: how many records exist in total
    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords = totalRecords + 1;
    }

    // Works out the right pay for whatever kind of employee this is.
    double effectivePay() {
        if (employee instanceof ManagerEmployee) {
            return ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            return ((InternEmployee) employee).effectiveSalary();
        }
        return employee.getSalary();
    }

    // One line summarising this record. Null-safe on the slot.
    String fullProfile() {
        String slotText;
        if (slot == null) {
            slotText = "no parking assigned";   // guard: never touch a null slot
        } else {
            slotText = slot.slotNo;
        }
        return name + " | Pay: Rs " + effectivePay() + " | Slot: " + slotText;
    }

    public static void main(String[] args) {

        // Two parking slots, each holding exactly one car
        ParkingSlot[] slots = new ParkingSlot[2];
        slots[0] = new ParkingSlot("A1", 1, 0);
        slots[1] = new ParkingSlot("A2", 1, 0);

        // Three employees of different kinds
        Employee divya = new ManagerEmployee("E102", "Divya", 70000, 8000);
        Employee karan = new Employee("E101", "Karan", 40000);
        Employee meera = new InternEmployee("E103", "Meera", 12000, 10000);

        // Allot parking using F3's null-safe lookup.
        // The first two get a slot; by the third call every slot is full,
        // so findAvailableSlot returns null and Meera simply gets no parking.
        ParkingSlot slotForDivya = takeSlot(slots);
        ParkingSlot slotForKaran = takeSlot(slots);
        ParkingSlot slotForMeera = takeSlot(slots);   // this one comes back null

        CompanyEmployeeRecord[] records = new CompanyEmployeeRecord[3];
        records[0] = new CompanyEmployeeRecord("Divya", "E102", divya, slotForDivya);
        records[1] = new CompanyEmployeeRecord("Karan", "E101", karan, slotForKaran);
        records[2] = new CompanyEmployeeRecord("Meera", "E103", meera, slotForMeera);

        for (int i = 0; i < records.length; i++) {
            System.out.println(records[i].fullProfile());
        }

        // Called on the class name, because the count belongs to the class.
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }

    // Helper: grab the first free slot and mark it occupied, or return null.
    static ParkingSlot takeSlot(ParkingSlot[] slots) {
        ParkingSlot free = ParkingSlot.findAvailableSlot(slots);
        if (free == null) {
            return null;
        }
        free.occupiedCount = free.occupiedCount + 1;
        return free;
    }
}