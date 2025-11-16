/**
 * Julian Williams-Goldberg
 * CS245 (EJ)
 * Linked list with a dummy head node that simplifies operations.
 * @param <T> the type of elements stored in the list
 */
public class DummyHeadLinkedList<T> implements List<T> {
    
    /**
     * Node class representing a single element in the linked list.
     */
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node dummyHead;
    private int size;
    
    /**
     * Creates an empty DummyHeadLinkedList with a permanent dummy node.
     */
    public DummyHeadLinkedList() {
        this.dummyHead = new Node(null);
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
        Node current = dummyHead;
        
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        current.next = newNode;
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
        
        Node current = dummyHead.next;
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
        
        Node current = dummyHead;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        T removed = current.next.data;
        current.next = current.next.next;
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
