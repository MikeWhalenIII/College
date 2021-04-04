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

    public Node pop() {
        
    }

}

private class Node {
    private String data;
    private Node next;

    public Node(String data) {
        this.data = data;
        next = null;
    }
}