/**
 * Julian Williams-Goldberg
 * CS245 (EJ)
 * Doubly-linked list implementation with next and prev pointers.
 * @param <T> the type of elements stored in the list
 */
public class DoublyLinkedList<T> implements List<T> {
    
    /**
     * Node class with both forward and backward pointers.
     */
    private class Node {
        T data;
        Node next;
        Node prev;
        
        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    /**
     * Creates an empty DoublyLinkedList.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Adds element at a specific index.
     * 
     * @param index the position where element should be inserted
     * @param element the element to add
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node newNode = new Node(element);
        
        if (size == 0) {
            head = tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index == size) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        
        size++;
    }
    
    /**
     * Adds element to the end of the list.
     * 
     * @param element the element to add
     * @return true if element was added successfully
     */
    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }
    
    /**
     * Gets the element at the specified index.
     * 
     * @param index the position of the element to retrieve
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if index is out of range
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
     * 
     * @param index the position of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        T removed = current.data;
        
        if (size == 1) {
            head = tail = null;
        } else if (current == head) {
            head = head.next;
            head.prev = null;
        } else if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        
        size--;
        return removed;
    }
    
    /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }
}