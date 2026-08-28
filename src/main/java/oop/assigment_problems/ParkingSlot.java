package oop.assigment_problems;

/**
 * F3. Object References, Null Safety, and a Mutating Method
 *
 * The old allocation run crashed because a method returned null and nobody
 * checked for it. This version can never throw a NullPointerException.
 */
public class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    // ---------- Instance method that MUTATES this object ----------
    // It changes occupiedCount on the real slot object, not on a copy.
    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount = occupiedCount + 1;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        } else {
            System.out.println("Slot " + slotNo + " is already full");
        }
    }

    // ---------- Returns the first free slot, or null if there is none ----------
    // Returning null is the dangerous part: the caller MUST check it.
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].occupiedCount < slots[i].capacity) {
                return slots[i];
            }
        }
        return null;   // every slot is full
    }

    // ---------- The null-safe wrapper ----------
    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);

        // THE GUARD. We check for null BEFORE touching the object.
        // Without this line, slot.allot(...) on a null reference would
        // throw NullPointerException and crash the whole allocation run.
        if (slot == null) {
            System.out.println("No slots available for " + vehicleNo);
        } else {
            slot.allot(vehicleNo);
        }
    }

    /*
     * WHY PASSING THE ARRAY DOES NOT COPY THE SLOTS
     * ---------------------------------------------
     * A ParkingSlot variable does not hold the object itself. It holds a
     * REFERENCE -- the address of the object sitting in memory.
     *
     * An array of ParkingSlot is therefore an array of addresses, not an
     * array of slot objects. When we pass 'slots' into a method, Java copies
     * the array reference only. Both main and the method end up pointing at
     * the very same array, holding the very same addresses, pointing at the
     * very same slot objects.
     *
     * That is why allot() incrementing occupiedCount is visible back in main:
     * there was only ever ONE slot object, seen through two names.
     */

    public static void main(String[] args) {

        // ---- Case 1: a slot IS available (A1 has 3 of 4 taken) ----
        ParkingSlot[] slotsWithSpace = new ParkingSlot[2];
        slotsWithSpace[0] = new ParkingSlot("A1", 4, 3);
        slotsWithSpace[1] = new ParkingSlot("A2", 5, 5);

        safeAllot(slotsWithSpace, "TN09AB1234");

        // ---- Case 2: EVERY slot is full (A1 now 4 of 4) ----
        ParkingSlot[] slotsAllFull = new ParkingSlot[2];
        slotsAllFull[0] = new ParkingSlot("A1", 4, 4);
        slotsAllFull[1] = new ParkingSlot("A2", 5, 5);

        safeAllot(slotsAllFull, "TN09AB1234");
    }
}