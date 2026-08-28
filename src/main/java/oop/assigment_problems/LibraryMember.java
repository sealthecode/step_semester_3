package oop.assigment_problems;

/**
 * F4 - PART 2: the corrected design.
 *
 * The rule: data that describes ONE member is an instance field.
 * Data that describes the LIBRARY as a whole is static.
 */
public class LibraryMember {

    // ---- Instance fields: one separate copy per member ----
    String name;
    String memberId;
    int booksIssued;

    // ---- Static fields: one shared copy for the whole library ----
    static String libraryName = "STEP Central Library";
    static int memberCount = 0;

    public LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        // memberCount is shared, so it keeps counting up across all members
        // and gives each new member a unique ID.
        memberCount = memberCount + 1;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    // Instance method: prints THIS member's card
    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    // Static method: answers a question about the whole library
    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {

        // ================= BROKEN VERSION =================
        System.out.println("Broken version:");

        BrokenLibraryMember first = new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember second = new BrokenLibraryMember("Rohan", "LM-1002", 1);

        // Both print "Rohan". Creating the second member destroyed the first.
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);
        System.out.println("(Aditi's data was overwritten - both members now show \"Rohan\")");

        System.out.println();

        // ================= FIXED VERSION =================
        System.out.println("Fixed version:");

        LibraryMember aditi = new LibraryMember("Aditi", 2);
        LibraryMember rohan = new LibraryMember("Rohan", 1);

        // Each object kept its own data.
        aditi.printMemberCard();
        rohan.printMemberCard();

        // Called on the CLASS, because the count belongs to the library.
        LibraryMember.printTotalMembers();
    }
}