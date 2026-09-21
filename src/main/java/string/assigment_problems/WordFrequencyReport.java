package string.assigment_problems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Week 2 - Problem 5: Stop-Word-Filtered Word Frequency Report
 *
 * Counts how often each meaningful word appears in a feedback paragraph,
 * ignoring common filler words, and prints the counts highest-first.
 */
public class WordFrequencyReport {

    // The filler words we refuse to count.
    private static final String[] STOP_WORDS = {"the", "was", "and", "a", "is", "of", "in"};

    // Returns true if this word is one of the filler words.
    private boolean isStopWord(String word) {
        for (int i = 0; i < STOP_WORDS.length; i++) {
            if (STOP_WORDS[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    void printFilteredWordFrequency(String feedback) {

        // ---- 1. Normalise: lowercase, then strip punctuation ----
        // replace() swaps every occurrence; replacing with "" deletes it.
        String cleaned = feedback.toLowerCase();
        cleaned = cleaned.replace(".", "");
        cleaned = cleaned.replace(",", "");
        cleaned = cleaned.replace("!", "");
        cleaned = cleaned.replace("?", "");

        // ---- 2. Split on whitespace ----
        // "\\s+" means "one or more whitespace characters", so double
        // spaces or a tab still produce clean single words.
        String[] words = cleaned.trim().split("\\s+");

        // ---- 3. Count, skipping stop words ----
        // LinkedHashMap remembers the order words were first seen, which
        // keeps tied counts in a sensible order when we print.
        Map<String, Integer> counts = new LinkedHashMap<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (word.isEmpty() || isStopWord(word)) {
                continue;   // skip this word, move to the next one
            }

            if (counts.containsKey(word)) {
                counts.put(word, counts.get(word) + 1);   // seen before: add one
            } else {
                counts.put(word, 1);                      // first sighting
            }
        }

        // ---- 4. Sort by count, highest first ----
        // A Map has no order we can sort, so copy the entries into a List.
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(counts.entrySet());

        // e2 minus e1 gives DESCENDING order. (e1 minus e2 would be ascending.)
        entries.sort((e1, e2) -> e2.getValue() - e1.getValue());

        // ---- 5. Print ----
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<String, Integer> entry = entries.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        WordFrequencyReport report = new WordFrequencyReport();

        report.printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}