package com.company;
import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node curr = first;

        public boolean hasNext() {
            return curr != null;
        }

        public Item next() {
            Item item = curr.item;
            curr = curr.next;
            return item;
        }
    }

    private class Node {
        Item item;
        Node next;
    }

    private Node first = null;

    public void push(Item s) {
        Node oldFirst = first;
        first = new Node();
        first.item = s;
        first.next = oldFirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int count = 0;
        Node curr = first;
        while (curr != null) {
            count++;
        }
        return count;
    }
}
