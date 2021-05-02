public class HashTable {
    private LinkedList[] hashArray;
    private int arraySize;

    public HashTable(int size) {
        arraySize = size;
        hashArray = new LinkedList[arraySize];

        // Fill array with linked list
        for (int i = 0; i < arraySize; i++) {
            hashArray[i] = new LinkedList();
        }
    }

    public void insert(String word) {
        // Insert the word at the hash value just computed
        Node node = new Node(hashFunction(word), word);
        hashArray[hashFunction(word)].insert(node);
    }

    public Boolean find(String word) {
        // Compute the hash value of the word
        int hashValue = hashFunction(word);

        if(hashArray[hashValue].find(word)) {
            return true; // word found in dictionary
        } else {
            return false; // word not found in dictionary
        }
    }

    private int hashFunction(String word) {
        int hashValue = 0;

        // Find the hash value for the word.
        for (int i = 0; i < word.length(); i++) {
            int letter = word.charAt(i) - 96;
            hashValue = (hashValue * 27 + letter) % arraySize;
        }
        return hashValue;
    }

}

class LinkedList {
    private Node first;

    LinkedList(){
        first = null;
    }

    public void insert(Node node) {
        if (!isEmpty()) {
            node.next = first;
        }
        first = node;
    }

    public boolean find(String word) {
        Node current = first;

        while (current != null) {
            if (current.getWord().equals(word)) {
                return true;
            } else {
                current = current.next;
            }
        }
        return false;
    }

    /**
     * Checks if the Linked List is empty
     * @return True/False on whether the list is empty.
     */
    public boolean isEmpty() {
        return (first == null);
    }
}

class Node {
    public Node next;
    private int index;
    private String word;

    Node(int index, String word) {
        this.index = index;
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }
}
