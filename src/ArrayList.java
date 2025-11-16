/**
 * Julian Williams-Goldberg
 * CS245 (EJ)
 * Array-based list implementation with dynamic resizing.
 * Initial capacity is 10 and doubles when full.
 * 
 * @param <T> the type of elements stored in the list
 */
public class ArrayList<T> implements List<T> {
    
    private T[] data;
    private int size;
    
    /**
     * Creates an empty ArrayList with initial capacity of 10.
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.data = (T[]) new Object[10];
        this.size = 0;
    }
    
    /**
     * Adds element at a specific index, shifting elements to the right.
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
        
        if (size == data.length) {
            resize();
        }
        
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        
        data[index] = element;
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
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
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
        return data[index];
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
        
        T removed = data[index];
        
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        
        data[--size] = null;
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
    
    /**
     * Doubles the array capacity when the array is full.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}