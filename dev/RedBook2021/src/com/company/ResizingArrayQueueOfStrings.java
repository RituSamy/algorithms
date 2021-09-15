package com.company;

public class ResizingArrayQueueOfStrings {
    private String[] q;
    int head, tail, N, capacity;

    public ResizingArrayQueueOfStrings(int capacity) {
        q = new String[capacity];
        N = 0;
        this.capacity = capacity;
        head = 0;
        tail = 0;
    }

    private boolean isFull() {
        return N == capacity;
    }
    private boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(String s) {
        if (isFull()) return; // later we will resize
        q[tail] = s;
        System.out.println("old tail:" + q[tail]);
        // increments tail, resets to 0 when capacity is reached
        tail = (tail+1) % q.length;
        N++;
        System.out.println("new tail:" + q[tail]);
    }

    public String dequeue() {
        if (isEmpty()) return null;

        System.out.println("old head:" + q[head]);
        String s = q[head];
        q[head] = null;
        head = (head+1) % q.length;
        N--;
        if (N == q.length / 4) resize(N/2);
        System.out.println("new head:" + q[head]);
        return s;
    }

    private void resize(int capacity) {
        String[] newQueue = new String[capacity];
        for (int i = 0; i < N; i++) {
            newQueue[i] = q[i];
        }
        q = newQueue;
    }

    public void print() {
        for (int i = 0; i < q.length; i++) {
            System.out.println(q[i]);
        }
    }
}
