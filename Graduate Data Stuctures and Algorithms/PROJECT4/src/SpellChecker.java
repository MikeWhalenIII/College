import java.util.ArrayList;

public class SpellChecker {
    HashTable dictionary;
    ArrayList<String> suggestions;

    public SpellChecker(HashTable dictionary) {
        this.dictionary = dictionary;
        suggestions = new ArrayList<String>();
    }

    public void check(String s) {
        if (!dictionary.find(s)) {
            System.out.println("'" + s + "' may be incorrectly spelled.\nSuggestions:");
            oneLetterMissing(s);
            oneLetterAdded(s);
            twoLettersReversed(s);

            // Print suggestions
            printSuggestions();

            // Clear suggestions
            suggestions.clear();
        }
    }

    private void oneLetterMissing(String s) {
        char[] test;

        for (int i = 0; i < s.length(); i++) {
            test = s.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                test[i] = ch;

                // Check to see if word exist in dictionary
                if (dictionary.find(String.valueOf(test))) {
                    if (!suggestions.contains(String.valueOf(test))) {
                        suggestions.add(String.valueOf(test));
                    }
                }
            }
        }
    }

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

    private void twoLettersReversed(String s) {

    }

    private void printSuggestions() {
        // Display word suggestion
        if (suggestions.isEmpty()) {
            System.out.print("NONE"); // No suggestions found in dictionary
        } else {
            for (String suggestion : suggestions) {
                System.out.print(suggestion + " ");
            }
        }
        System.out.println("\n==================================\n");
    }
}
