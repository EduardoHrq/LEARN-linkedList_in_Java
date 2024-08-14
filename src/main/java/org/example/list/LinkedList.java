package org.example.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LinkedList<T> {

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int length = 0;

    public LinkedList() {}

    public LinkedList(T value) {
        Node<T> node = new Node<>(value);
        this.firstNode = node;
        this.lastNode = node;
        this.length++;
    }

    public Node<T> getFirstNode() {
        return firstNode;
    }

    public T getFirstNodeValue() {
        return firstNode.getValue();
    }

    public Node<T> getLastNode() {
        return lastNode;
    }

    public T getLastNodeValue() {
        return this.lastNode.getValue();
    }

    public int getLength() {
        return length;
    }

    public Node<T> get(int humanIndex) {
        if (this.length == 0) {
            return null;
        }

        if (humanIndex <= 1 && humanIndex >= 0) {
            return this.firstNode;
        } else if(humanIndex >= this.length) {
            return this.lastNode;
        }

        return getPrevNode(humanIndex).getNextNode();

    }

    private Node<T> getPrevNode(int index) {
        if (index > this.length) {
            index = this.length;
        }

        Node<T> aux = this.firstNode;

        for (int i = 1; i < (index - 1); i++) {
            aux = aux.getNextNode();
        }

        return aux;
    }

    public T getNodeValue(int humanIndex) {

        return this.get(humanIndex).getValue();

    }

    public T peekNodeValue(T value) {
        try {
            return this.toNodeValuesList().stream().filter(nodeValue -> nodeValue == value).toList().getFirst();
        } catch (Exception e) {
            System.err.println("Valor nao encontrado");
            throw new NoSuchElementException(e + "");
        }
    }

    public Node<T> peekNode(T value) {
        try {
            return this.toNodesList().stream().filter(node -> node.getValue() == value).toList().getFirst();
        } catch (Exception e) {
            System.err.println("Valor nao encontrado");
            throw new NoSuchElementException(e + "");
        }
    }

    public List<T> toNodeValuesList() {
        return this.toNodesList().stream().map(Node::getValue).toList();
    }

    public List<Node<T>> toNodesList() {
        Node<T> aux = this.firstNode;

        List<Node<T>> nodesList = new ArrayList<>();

        while (aux != null) {
            nodesList.add(aux);
            aux = aux.getNextNode();
        }

        return nodesList;
    }

    public boolean contains(T value) {

        for (T nodeValue : this.toNodeValuesList()) {
            if (nodeValue == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * <h2>Adicionar nó</h2>
     *
     * Adiciona um novo nó no final da lista encadeada
     *
     * @param value valor que sera atribuido ao nó
     * */
    public void pushNode(T value) {
        Node<T> node = new Node<>(value);

        if (this.firstNode == null) {
            this.firstNode = node;
        } else {
            this.lastNode.setNextNode(node);
        }

        this.lastNode = node;
        this.length++;
    }

    /**
     * <h3>Adicionar no inicio</h3>
     *
     * Adiciona um novo nó no inicio da lista encadeada
     *
     * @param value - valor que sera atribuido ao nó
     * */
    public void prepend(T value) {
        Node<T> node = new Node<>(value);

        if (firstNode == null) {
            this.lastNode = node;
        } else {
            node.setNextNode(this.firstNode);
        }

        this.firstNode = node;
        this.length++;

    }

    public void pushNode(int humanIndex, T value) {
        if (humanIndex <= 1 && humanIndex >= 0) {
            this.prepend(value);
        } else if (humanIndex >= this.length) {
            this.pushNode(value);
        } else {
            Node<T> node = new Node<>(value);
            Node<T> aux = this.getPrevNode(humanIndex);

            node.setNextNode(aux.getNextNode());
            aux.setNextNode(node);

            this.length++;
        }

    }

    public void pushAll(T... values) {
        for (T value : values) {
            this.pushNode(value);
        }
    }

    public void pushAll(Collection<T> values) {
        for (T value : values) {
            this.pushNode(value);
        }
    }

    public Node<T> pop() {

        if (this.lastNode == null) {
            return null;
        }

        Node<T> aux = this.firstNode;

        if (this.length == 1) {
            this.firstNode = null;
            this.lastNode = null;
            this.length--;
            return aux;
        }

        while(aux.getNextNode() != this.lastNode) {
            aux = aux.getNextNode();
        }

        Node<T> nodeRemoved = this.lastNode;
        aux.setNextNode(null);
        this.lastNode = aux;

        this.length--;

        return nodeRemoved;

    }

    public Node<T> removeFirst() {

        if (firstNode == null) {
            return null;
        }

        Node<T> aux = this.firstNode;

        if (this.length == 1) {
            this.lastNode = null;
        }

        this.firstNode = null;
        this.firstNode = aux.getNextNode();
        this.length--;
        return aux;
    }

    public Node<T> remove(int humanIndex) {
        Node<T> auxPrev = this.getPrevNode(humanIndex);
        Node<T> nodeToRemove = auxPrev.getNextNode();

        if (nodeToRemove == this.lastNode) {
            this.pop();
            return nodeToRemove;
        }

        Node<T> posNode = nodeToRemove.getNextNode();

        auxPrev.setNextNode(null);
        auxPrev.setNextNode(posNode);
        this.length--;
        return nodeToRemove;
    }

    public void showList() {
        if (this.firstNode != null) {
            System.out.println(this.toNodeValuesList());
        } else {
            System.out.println("[]");
        }

    }
}
