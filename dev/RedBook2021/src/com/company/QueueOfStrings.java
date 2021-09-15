package com.company;

public class QueueOfStrings {

    private class Node {
        String item;
        Node next;
    }

    private Node first, last;

    public void enqueue(String s) {
        Node oldLast = last;
        last = new Node();
        last.item = s;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public String dequeue() {
       String item = first.item;
       first = first.next;
       if (isEmpty()) last = null;
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
