package edu.sdccd.cisc191;

import java.util.LinkedList;

public class GenericMatchQueue<T> {

    private final LinkedList<T> items = new LinkedList<>();

    public void enqueue(T item) {
        // add the item to the back of the queue
        items.addLast(item);
    }

    public T dequeue() {
        // remove and return the front item
        // throw IllegalStateException if the queue is empty
        if (items.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return items.removeFirst();
    }

    public T peek() {
        // return the front item without removing it
        // throw IllegalStateException if the queue is empty
        if (items.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return items.getFirst();
    }

    public boolean isEmpty() {
        // return true when the queue has no items
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }
}
