/***********************************************************************
 Michael Whalen
 BinaryTree.java
 COP5416 - Project III
 This class reads the two text files, words.txt and encoded.txt
 ************************************************************************/

public class BinaryTree {
    private final Node root;

    public BinaryTree() {
        // Create an empty root node
        root = new Node(null);
    }

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

    public Node(String data) {
        this.data = data;
    }
}
