package oop.assigment_problems;

/**
 * F1. From Procedural Mess to a Working Library Fine System
 *
 * The old script tracked five overdue books using five sets of parallel
 * variables. Here each book is instead ONE object that carries its own
 * data and knows how to answer questions about itself.
 */
public class BookIssue {

    // ---------- Fields (each object gets its own copy of these) ----------
    String title;
    String borrowerName;
    int daysOverdue;

    // ---------- Constructor: sets all three fields when the object is made ----------
    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    // ---------- Instance method: fine for THIS one book ----------
    // Rs 5 per overdue day. A book that is not overdue owes nothing.
    double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }
        return 0;
    }

    // ---------- Instance method: is THIS one book badly overdue? ----------
    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // ---------- Static method: works over MANY books at once ----------
    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;
        for (int i = 0; i < issues.length; i++) {
            total = total + issues[i].fineAmount();
        }
        return total;
    }

    /*
     * WHY totalFineCollected IS static BUT fineAmount IS NOT
     * -----------------------------------------------------
     * fineAmount() answers a question about ONE particular book: it reads
     * that object's own daysOverdue field. It cannot even run without a
     * specific book to run on, so it must be an instance method.
     *
     * totalFineCollected() answers a question about the WHOLE COLLECTION.
     * It does not belong to any single book -- asking "Clean Code, what is
     * the library's total fine?" makes no sense. It belongs to the class
     * as a whole, so it is static and is called as
     * BookIssue.totalFineCollected(...) without needing any one object.
     */

    public static void main(String[] args) {

        // Build an array of five BookIssue objects
        BookIssue[] issues = new BookIssue[5];
        issues[0] = new BookIssue("Clean Code", "Aditi", 18);
        issues[1] = new BookIssue("Effective Java", "Rohan", 5);
        issues[2] = new BookIssue("Refactoring", "Meera", 0);
        issues[3] = new BookIssue("DSA Handbook", "Karan", 21);
        issues[4] = new BookIssue("Design Patterns", "Divya", 9);

        // Print each book's title and overdue status
        for (int i = 0; i < issues.length; i++) {
            String status;
            if (issues[i].isSeverelyOverdue()) {
                status = "Severely overdue";
            } else {
                status = "OK";
            }
            System.out.println(issues[i].title + " - " + issues[i].daysOverdue + " days - " + status);
        }

        // Print the total using the CLASS NAME, not an object
        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}