package org.example.list;

import java.util.LinkedList;

public class Node<T> {

    private T value;
    private Node<T> nextNode;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }



}
