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
        char[] sCharArr = s.toCharArray();
        char[] word = s.toCharArray();

        for (int i = 0; i < s.length() - 1; i++) {
            char temp = word[i];
            word[i] = word[i+1];
            word[i+1] = temp;

            // Check to see if word exist in dictionary
            if (dictionary.find(String.valueOf(word))) {
                if (!suggestions.contains(String.valueOf(word))) {
                    suggestions.add(String.valueOf(word));
                }
            }
        }
    }

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
