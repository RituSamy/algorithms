package com.company;
import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node first, last;

    public int size() {
        Node x = first;
        int count = 0;
        while (x != null) {
            count++;
            x = x.next;
        }
        return count;
    }

    public void enqueue(Item s) {
        Node oldLast = last;
        last = new Node();
        last.item = s;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public Item peek() {
        return first.item;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean contains(Item item) {
        Node curr = first;
        while (curr != null) {
            if (curr.item == item) return true;
            curr = curr.next;
        }
        return false;
    }


    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        // Return the current item and MOVE to the next item.
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {};
    }
}
