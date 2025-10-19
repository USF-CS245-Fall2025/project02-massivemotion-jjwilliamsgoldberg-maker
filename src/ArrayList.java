/**
 * Julian Williams-Goldberg
 * CS245 (EJ)
 * Array-based list implementation.
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
     * Adds element at a specific index.
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
     */
    @Override
    public int size() {
        return size;
    }
    
    // Doubles the array capacity when needed
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}