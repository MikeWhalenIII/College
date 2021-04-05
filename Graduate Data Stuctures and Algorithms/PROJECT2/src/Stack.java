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

    public void push(String data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        numOfNodesOnStack++;
    }

    private boolean isEmpty() {
        return (top == null);
    }

    public Node pop() {
        Node node = top;

        if (isEmpty() == true) {
            System.out.println("Stack is empty!");
        } else {
            top = top.next;
        }
        numOfNodesOnStack--; // Decrease the number of total links by 1.

        return node;
    }

}

class Node {
    public Node next;
    public String data;

    public Node(String data) {
        this.data = data;
        next = null;
    }
}