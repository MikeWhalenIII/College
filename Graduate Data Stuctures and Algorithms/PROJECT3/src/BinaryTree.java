/***********************************************************************
 Michael Whalen
 BinaryTree.java
 COP5416 - Project III
 This class contains the code for a BinaryTree. It provides methods to
 insert and search the binary tree. The class also contains the Node class.
 ************************************************************************/

public class BinaryTree {
    private final Node root;

    /**
     * Constructor
     */
    public BinaryTree() {
        // Create an empty root node
        root = new Node(null);
    }

    /**
     * Inserts a new node into the tree.
     * @param word will be assigned to the data field of the new node.
     * @param symbol Used to determine left/right subtree.
     */
    public void insert(String word, String symbol) {
        Node current = root;
        Node previous = current;

        while (current != null) {
            for (int i = 0; i < symbol.length(); i++) {
                previous = current;
                if (symbol.charAt(i) == '+') {
                    current = current.rightChild;
                } else {
                    current = current.leftChild;
                }
            }
        }

        if (symbol.charAt(symbol.length() - 1) == '+') {
            previous.rightChild = new Node(word);
        } else {
            previous.leftChild = new Node(word);
        }
    }

    /**
     * This method takes in an encoded string and
     * decodes it by traversing the tree.
     * @param code the encoded message.
     * @return The decoded message.
     */
    public String search(String code) {
        Node current;

        // Split the string
        String[] codeArr = code.split(" ");
        StringBuilder sb = new StringBuilder();

            // Iterate through the code array
            for (int i = 0; i < codeArr.length; i++) {
                current = root;
                String splitCode = codeArr[i];
                // Iterate through each character in the string
                for (int j = 0; j < splitCode.length(); j++) {
                    if (splitCode.charAt(j) == '+') {
                        current = current.rightChild;
                    } else if(splitCode.charAt(j) == '-') {
                        current = current.leftChild;
                    }
                }
                sb.append(current.data).append(" ");
            }
        return sb.toString();
    }
}

class Node {
    String data;
    Node leftChild = null;
    Node rightChild = null;

    /**
     * Constructor
     * @param data
     */
    public Node(String data) {
        this.data = data;
    }
}
