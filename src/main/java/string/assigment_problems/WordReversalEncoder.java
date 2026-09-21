package string.assigment_problems;

/**
 * Week 2 - Problem 2: Word Reversal Encoder
 *
 * Reverses every word in a sentence individually while keeping the
 * word order unchanged.  "hello club"  ->  "olleh bulc"
 */
public class WordReversalEncoder {

    String reverseEachWord(String sentence) {

        // split(" ") cuts the sentence at every space and hands back an
        // array of the pieces. "hello club" -> ["hello", "club"]
        String[] words = sentence.split(" ");

        // This builder collects the finished sentence as we go.
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            // Build this one word backwards, character by character.
            // We start at the LAST index (length - 1) and count DOWN to 0.
            StringBuilder reversedWord = new StringBuilder();
            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord.append(word.charAt(j));
            }

            output.append(reversedWord);

            // Put a space back between words, but not after the last one.
            if (i < words.length - 1) {
                output.append(" ");
            }
        }

        // toString() turns the StringBuilder into a normal String.
        return output.toString();
    }

    public static void main(String[] args) {
        WordReversalEncoder encoder = new WordReversalEncoder();

        System.out.println(encoder.reverseEachWord("hello club"));
        System.out.println(encoder.reverseEachWord("the mentor was great"));
        System.out.println(encoder.reverseEachWord("java"));
    }
}