/***********************************************************************
 Michael Whalen
 Stack.java
 COP5416 - Project II

 This class is used to implement a stack using a singly linked list.
 ************************************************************************/

public class Stack {
    private Node top;

    /**
     * Constructor
     */
    public Stack() {
        top = null;
    }

    /**
     * Adds a new item to the stack.
     * @param data
     */
    public void push(char data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    /**
     * Checks if the stack is empty
     * @return True/False on whether the stack is empty.
     */
    public boolean isEmpty() {
        return (top == null);
    }

    /**
     * Removes and item from the stack and returns its data.
     * @return The items data.
     */
    public char pop() {
        Node node = top;

        if (isEmpty() == true) {
            System.out.println("Stack is empty!");
        } else {
            top = top.next;
        }
        return node.data;
    }

}

/**
 * Node class
 */
class Node {
    public Node next;
    public char data;

    /**
     * Constructor
     * @param data The data to be stored in the node.
     */
    public Node(char data) {
        this.data = data;
        next = null;
    }
}