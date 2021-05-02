import java.util.ArrayList;

public class Project4 {

    public static void main(String[] args) {

        // Read the Dictionary and Test file
        Reader r = new Reader("Dictionary.txt", "testTextFile.txt");

        // Get Dictionary Array and import them into hash table
        String[] dictionaryArr = r.getDictionaryArray();

        // Get Test file ArrayList
        ArrayList<String> testFileArrList = r.getTestFileArray();

        // Create new HashTable
        HashTable dictionary = new HashTable((4 * dictionaryArr.length) + 1);

        // Import dictionary into HashTable
        for (int i = 0; i < dictionaryArr.length; i++) {
            dictionary.insert(dictionaryArr[i]);
        }

        // Spell check the test file
        SpellChecker sc = new SpellChecker(dictionary);
        for (String s:testFileArrList) {
            sc.check(s);
        }
    }
}
