package string.assigment_problems;

/**
 * Week 2 - Problem 1: ATM PIN Length Validator
 *
 * Checks that a PIN is exactly 4 characters long, using nothing but
 * length() and a single if / else. No loop is needed here.
 */
public class AtmPinLengthValidator {

    // Takes a PIN and prints whether its LENGTH is acceptable.
    void checkPinLength(String pin) {
        // length() returns how many characters the String holds.
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        // checkPinLength is an instance method, so we need an object to call it on.
        AtmPinLengthValidator validator = new AtmPinLengthValidator();

        validator.checkPinLength("482");    // 3 characters -> invalid
        validator.checkPinLength("4820");   // 4 characters -> valid
    }
}