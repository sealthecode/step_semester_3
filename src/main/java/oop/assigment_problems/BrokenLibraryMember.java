package oop.assigment_problems;

/**
 * F4 - PART 1: the junior developer's broken first draft.
 *
 * Every field is static "to make it easier to access from anywhere".
 * A static field belongs to the CLASS, so there is only ONE copy of it in
 * the entire program, shared by every object ever created.
 */
public class BrokenLibraryMember {

    static String name;
    static String memberId;
    static int booksIssued;

    public BrokenLibraryMember(String name, String memberId, int booksIssued) {
        // These do NOT fill in "this object's" fields, because there are no
        // per-object fields. They overwrite the single shared copy.
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }

    /*
     * WHY static IS WRONG FOR EACH FIELD
     * ----------------------------------
     * static String name
     *     A name identifies one particular person. Making it static means
     *     the whole library has exactly one name between all members, so
     *     registering Rohan erases Aditi.
     *
     * static String memberId
     *     A member ID must be unique per member -- that is its entire
     *     purpose. One shared ID makes every member indistinguishable and
     *     makes issuing or returning a book untraceable.
     *
     * static int booksIssued
     *     Each member borrows their own books. Shared, one member's
     *     borrowing silently changes everyone else's count, so limits and
     *     fines would be charged to the wrong person.
     */
}