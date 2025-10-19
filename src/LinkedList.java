/**
 * Julian Williams-Goldberg
 * CS245 (EJ)
 * Singly-linked list implementation.
 */
public class LinkedList<T> implements List<T> {
    
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    /**
     * Creates an empty LinkedList.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Adds element at a specific index.
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node newNode = new Node(element);
        
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        
        size++;
    }
    
    /**
     * Adds element to the end of the list.
     */
    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }
    
    /**
     * Gets the element at the specified index.
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.data;
    }
    
    /**
     * Removes and returns the element at the specified index.
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        T removed;
        
        if (index == 0) {
            removed = head.data;
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removed = current.next.data;
            current.next = current.next.next;
        }
        
        size--;
        return removed;
    }
    
    /**
     * Returns the number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }
}