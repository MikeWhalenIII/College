/***********************************************************************
 Michael Whalen
 HashTable.java
 COP5416 - Project IV
 This file contains the code for a HashTable. It provides methods to
 insert and find data in the HashTable.

 This file also includes the code for a LinkedList in order to create a
 externally chained HashTable.
 ************************************************************************/

public class HashTable {
    private final LinkedList[] hashArray;
    private final int arraySize;

    /**
     * Constructor
     *
     * @param size the size of the hash table
     */
    public HashTable(int size) {
        arraySize = size;
        hashArray = new LinkedList[arraySize];

        // Fill array with linked list
        for (int i = 0; i < arraySize; i++) {
            hashArray[i] = new LinkedList();
        }
    }

    /**
     * Inserts a word into the hash table
     *
     * @param word the word to be inserted
     */
    public void insert(String word) {
        // Insert the word at the hash value just computed
        Node node = new Node(hashFunction(word), word);
        hashArray[hashFunction(word)].insert(node);
    }

    /**
     * Checks to see if the given word is present in
     * the hash table.
     *
     * @param word The word to search for
     * @return TRUE:  Word is found in hash table
     * FALSE: Word is not found in hash table
     */
    public Boolean find(String word) {
        // Compute the hash value of the word
        int hashValue = hashFunction(word);

        // word not found in dictionary
        return hashArray[hashValue].find(word); // word found in dictionary
    }

    /**
     * Computes a hash value of the word passed in.
     *
     * @param word The word to hash
     * @return The hash value of the word
     */
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

    /**
     * Constructor
     */
    public LinkedList() {
        first = null;
    }

    /**
     * Inserts a new node into the linked list
     *
     * @param node The node to be inserted
     */
    public void insert(Node node) {
        if (!isEmpty()) {
            node.next = first;
        }
        first = node;
    }

    /**
     * Checks to see if a node exist in the LL
     * that contains the provided word.
     *
     * @param word The word to search for
     * @return TRUE:  Word is found
     * FALSE: Word is not found
     */
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
     *
     * @return True/False on whether the list is empty.
     */
    public boolean isEmpty() {
        return (first == null);
    }
}

class Node {
    public Node next;
    private final String word;

    /**
     * Constructor
     *
     * @param index The index where the node is to be
     *              stored
     * @param word  The word to store in the Node
     */
    Node(int index, String word) {
        this.word = word;
    }

    /**
     * Returns the word that is stored in the node obj
     *
     * @return a word
     */
    public String getWord() {
        return this.word;
    }
}
