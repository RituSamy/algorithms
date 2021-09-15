package com.company;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // the order in which the items come out of the stack
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        public boolean hasNext() { return i > 0; }
        public Item next() { return stack[--i]; }
    }

    Item[] stack;
    int N;

    public ResizingArrayStack(int capacity) {
        // unfortunately Java doesn't allow the creation of Generic arrays. so we have to add this ugly cast.
        stack = (Item[]) new Object[capacity];
        N = 0;
    }

    private void resize(int capacity) {
        Item[] newStack = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    public void push(Item s) {
        if (N == stack.length) resize(N*2);
        stack[N++] = s;
    }

    public Item pop() {
        Item s = stack[--N];
        stack[N] = null;
        if (N > 0 && N == stack.length / 4) resize(stack.length/2);
        return s;
    }

    public boolean isEmpty() {
        return stack.length == 0;
    }

    public int size() {
        return stack.length;
    }

    public void print() {
        for (int i = 0; i < stack.length; i++) {
            System.out.println(stack[i]);
        }
    }
}
