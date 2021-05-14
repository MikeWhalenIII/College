/***********************************************************************
 Michael Whalen
 SpellChecker.java
 COP5416 - Project IV

 This class contains the code for a SpellChecker. The spell checker will
 take a word and search for it in a dictionary. If the word is not found
 it will provide suggestions.
 ************************************************************************/

import java.util.ArrayList;

public class SpellChecker {
    HashTable dictionary;
    ArrayList<String> suggestions;

    /**
     * Constructor
     *
     * @param dictionary the dictionary
     */
    public SpellChecker(HashTable dictionary) {
        this.dictionary = dictionary;
        suggestions = new ArrayList<>();
    }

    /**
     * This method checks the dictionary to see if the word exist
     * if not, the method will provide spelling suggestions.
     *
     * @param s The word the method will search for
     */
    public void check(String s) {
        if (!dictionary.find(s)) {
            System.out.println("'" + s + "' may be spelled incorrectly\nSuggestions:");
            oneLetterMissing(s);
            oneLetterAdded(s);
            twoLettersReversed(s);

            // Print suggestions
            printSuggestions();

            // Clear suggestions
            suggestions.clear();
        }
    }

    /**
     * This method assembles new words by adding letters a..z
     * in each of the positions in the word from start to end.
     *
     * @param s The initial string
     */
    private void oneLetterMissing(String s) {
        StringBuilder sb;

        for (int i = 0; i <= s.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                sb = new StringBuilder(s);
                sb.insert(i, ch);

                // Check to see if word exist in dictionary
                if (dictionary.find(sb.toString())) {
                    if (!suggestions.contains(sb.toString())) {
                        suggestions.add(sb.toString());
                    }
                }
            }
        }
    }

    /**
     * This method scans through the provided string,
     * deleting one letter at a time to see if the
     * resulting string is a word found in the dictionary.
     *
     * @param s The initial string
     */
    private void oneLetterAdded(String s) {
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder(s);
            sb.deleteCharAt(i);
            if (sb.toString().isEmpty()) {
                break; // empty string
            }
            // Check to see if word exist in dictionary
            if (dictionary.find(sb.toString())) {
                if (!suggestions.contains(sb.toString())) {
                    suggestions.add(sb.toString());
                }
            }
        }
    }

    /**
     * This method takes the initial string and swaps
     * the letters in position 0..1, 1..2, 2..3, ..., n-2..n-1
     * and checks to see if the resulting string is a word in the
     * dictionary.
     *
     * @param s the initial string
     */
    private void twoLettersReversed(String s) {
        char[] word = s.toCharArray();

        for (int i = 0; i < s.length() - 1; i++) {
            char temp = word[i];
            word[i] = word[i + 1];
            word[i + 1] = temp;

            // Check to see if word exist in dictionary
            if (dictionary.find(String.valueOf(word))) {
                if (!suggestions.contains(String.valueOf(word))) {
                    suggestions.add(String.valueOf(word));
                }
            }
        }
    }

    /**
     * Prints suggestions to words not found in the dictionary
     */
    private void printSuggestions() {
        // Display word suggestion
        if (suggestions.isEmpty()) {
            System.out.print("NONE"); // No suggestions found in dictionary
        } else {
            for (String suggestion : suggestions) {
                System.out.print(suggestion + "  ");
            }
        }
        System.out.println("\n----------------------------------\n");
    }
}
