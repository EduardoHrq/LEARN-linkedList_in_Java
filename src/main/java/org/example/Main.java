package org.example;

import org.example.list.LinkedList;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.pushAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.showList();
        System.out.println("quantidade: " + list.getLength());

        list.prepend(0);
        list.pushNode(10);
        list.showList();

        list.removeFirst();
        list.remove(20); //mesmo que maior que a quantidade, retornara o ultimo e o removera
        list.showList();

        System.out.println("primeiro no: " + list.getFirstNodeValue());
        System.out.println("ultimo no: " + list.getLastNodeValue());
        System.out.println("no com index: " + list.getNodeValue(6));
        System.out.println("buscando pelo valor: " + list.peekNode(5));


    }
}