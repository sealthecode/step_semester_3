package string.assigment_problems;

/**
 * Week 2 - Problem 4: Library ISBN Normalizer & Validator
 *
 * A valid code is exactly 13 characters:
 *     3 letters (publisher code) + 4 digits (year) + 6 digits (catalog number)
 *
 * Stage 1 normalize: trim spaces, uppercase ONLY the first 3 characters.
 * Stage 2 validate : length, letters, digits - checked with a loop, no regex.
 */
public class IsbnNormalizerValidator {

    // ---------- STAGE 1: clean the raw scan up ----------
    String normalizeCode(String raw) {

        // trim() removes leading and trailing spaces (not spaces in the middle).
        String trimmed = raw.trim();

        // Guard: substring(0, 3) would crash on a string shorter than 3 chars.
        // Hand it back untouched and let validation report the bad length.
        if (trimmed.length() < 3) {
            return trimmed;
        }

        // substring(0, 3)  -> characters 0,1,2  (the publisher code)
        // substring(3)     -> character 3 to the end (year + catalog)
        String publisher = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);

        // Concatenate the uppercased head back onto the untouched tail.
        return publisher + rest;
    }

    // ---------- STAGE 2: check it, then format it ----------
    String validateAndFormat(String code) {

        // --- Rule 1: exactly 13 characters ---
        if (code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }

        // --- Rule 2: the first 3 characters must all be letters ---
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // --- Rule 3: the remaining 10 characters must all be digits ---
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: remaining 10 characters must be digits";
            }
        }

        // --- Valid: carve it into its three parts and build the display line ---
        String publisher = code.substring(0, 3);    // chars 0-2
        String year = code.substring(3, 7);         // chars 3-6
        String catalog = code.substring(7, 13);     // chars 7-12

        StringBuilder display = new StringBuilder();
        display.append("[").append(publisher).append("] ");
        display.append("YEAR: ").append(year).append(" | ");
        display.append("CATALOG: ").append(catalog);

        return display.toString();
    }

    public static void main(String[] args) {
        IsbnNormalizerValidator scanner = new IsbnNormalizerValidator();

        // Valid: stray spaces and a lowercase publisher code, both cleaned up
        String a = scanner.normalizeCode("  pen2026004251  ");
        System.out.println(scanner.validateAndFormat(a));

        // Invalid: publisher code has digits in it
        String b = scanner.normalizeCode("12N2026004251");
        System.out.println(scanner.validateAndFormat(b));

        // Invalid: too short
        String c = scanner.normalizeCode("pen20260042");
        System.out.println(scanner.validateAndFormat(c));

        // Invalid: a letter where a digit belongs
        String d = scanner.normalizeCode("pen202600425X");
        System.out.println(scanner.validateAndFormat(d));
    }
}