public class BinaryTree {
    private Node root;

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

    public void find() {

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
