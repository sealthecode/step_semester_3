package constructors.assigment_problems;

/**
 * Week 4 - Problem 1: Ghost Order Validator
 *
 * An invalid FoodOrder can never be constructed. Validation happens in the
 * constructor, so a "ghost order" is rejected before the object exists.
 */
public class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    /*
     * Blocking the no-argument constructor.
     * Java only supplies a free no-arg constructor when a class declares NO
     * constructor at all. Declaring the parameterized one below already
     * removes it. This private one makes that intent explicit and stops the
     * class itself from sneaking round the rule.
     */
    private FoodOrder() {
        throw new UnsupportedOperationException("A FoodOrder always needs a student name and a dish name");
    }

    public FoodOrder(String studentName, String dishName) {
        if (isBlank(studentName)) {
            throw new IllegalArgumentException("Student name is missing");
        }
        if (isBlank(dishName)) {
            throw new IllegalArgumentException("Dish name is missing");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    // null, "", or "   " are all treated as missing.
    // The null test MUST come first - calling .trim() on null would crash.
    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    void markDelivered() {
        if (delivered) {
            System.out.println("WARNING: " + dishName + " for " + studentName
                    + " was already marked delivered - possible double-serve");
            return;
        }
        delivered = true;
        System.out.println("Delivered: " + dishName + " to " + studentName);
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (int i = 0; i < rawOrders.length; i++) {
            String[] row = rawOrders[i];

            // A malformed row is garbage input too, not a crash.
            if (row == null || row.length < 2) {
                rejected++;
                continue;
            }

            try {
                new FoodOrder(row[0], row[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                // The constructor refused to build it - that IS the rejection.
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] batch = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };
        processBatch(batch);

        System.out.println();
        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");
        order.markDelivered();
        order.markDelivered();
    }
}