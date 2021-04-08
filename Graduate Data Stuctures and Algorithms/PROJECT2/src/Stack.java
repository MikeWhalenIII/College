/***********************************************************************
 Michael Whalen
 Stack.java
 COP5416 - Project II

 This class is used to implement a stack using a singly linked list.
 ************************************************************************/

public class Stack {
    private Node top;
    private int numOfNodesOnStack;

    public Stack() {
        top = null;
        numOfNodesOnStack = 0;
    }

    public void push(char data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        numOfNodesOnStack++;
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public char pop() {
        Node node = top;

        if (isEmpty() == true) {
            System.out.println("Stack is empty!");
        } else {
            top = top.next;
        }
        numOfNodesOnStack--; // Decrease the number of total links by 1.

        return node.data;
    }

}

class Node {
    public Node next;
    public char data;

    public Node(char data) {
        this.data = data;
        next = null;
    }
}