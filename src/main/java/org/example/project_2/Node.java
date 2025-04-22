package org.example.project_2;

public class Node {
    private Object element;
    private int next;
    public Node(Object element) {
        this(element, 0);
    }
    public Node(Object element, int next) {
        this.element = element;
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }


}

