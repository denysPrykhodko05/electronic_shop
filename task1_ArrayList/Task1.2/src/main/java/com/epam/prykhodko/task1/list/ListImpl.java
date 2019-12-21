package com.epam.prykhodko.task1.list;

import com.epam.prykhodko.task1.entity.Product;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

public class ListImpl<T extends Product> implements List<T> {

    public static final int DEFAULT_CAPACITY = 10;
    private T[] innerArray;
    private int size = INTEGER_ZERO;

    ListImpl() {
        innerArray = (T[]) new Product[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == INTEGER_ZERO;
    }

    @Override
    public boolean contains(Object o) {
        checkElementClass(o);
        return indexOf(o) > INTEGER_MINUS_ONE;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private boolean nextCall = false;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && innerArray[currentIndex] != null;
            }

            @Override
            public T next() {
                nextCall = true;
                return innerArray[currentIndex++];
            }

            @Override
            public void remove() {
                if (nextCall) {
                    nextCall = false;
                    ListImpl.this.remove(size - 1);
                    return;
                }
                throw new IllegalStateException();
            }
        };
        return it;
    }

    @Override
    public Object[] toArray() {
        return Arrays.stream(innerArray).toArray();
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return toArray();
    }

    @Override
    public boolean add(T o) {
        add(size, o);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }
        remove(indexOf(o));
        return true;
    }

    @Override
    public boolean containsAll(Collection collection) {
        checkCollectionClass(collection);
        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection collection) {
        addAll(size, collection);
        return true;
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        checkIndex(i);
        checkCollectionClass(collection);

        T[] tempArray = (T[]) new Product[innerArray.length + collection.size()];
        System.arraycopy(innerArray, 0, tempArray, 0, i);
        System.arraycopy(collection.toArray(), 0, tempArray, i, collection.size());
        System.arraycopy(innerArray, i, tempArray, i + collection.size(), size);
        innerArray = tempArray;
        return true;
    }

    @Override
    public boolean removeAll(Collection collection) {
        checkCollectionClass(collection);
        for (Object o : collection) {
            remove(o);
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection collection) {
        checkCollectionClass(collection);
        T[] tempArray = (T[]) new Product[innerArray.length];
        int tempSize = 0;
        for (Object o : collection) {
            if (contains(o)) {
                tempArray[tempSize++] = (T) o;
            }
        }
        size = tempSize;
        return true;
    }

    @Override
    public void clear() {
        innerArray = (T[]) new Product[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public T get(int i) {
        checkIndex(i);
        return innerArray[i];
    }

    @Override
    public T set(int i, T o) {
        checkIndex(i);
        checkElementOnNull(o);
        T oldElement = innerArray[i];
        innerArray[i] = o;
        return oldElement;
    }

    @Override
    public void add(int i, T o) {
        checkIndex(i);
        checkElementOnNull(o);
        if (size >= innerArray.length * 0.8) {
            Product[] tempArr = Arrays.copyOf(innerArray, size);
            innerArray = (T[]) new Product[size + size / INTEGER_TWO + INTEGER_ONE];
            innerArray = (T[]) Arrays.copyOf(tempArr, innerArray.length);
        }
        innerArray[size++] = o;
    }

    @Override
    public T remove(int i) {
        T removableProduct;
        checkIndex(i);

        for (int j = i; j < size - 1; j++) {
            T temp = innerArray[j];
            innerArray[j] = innerArray[j + INTEGER_ONE];
            innerArray[j + INTEGER_ONE] = temp;
        }
        removableProduct = (T) innerArray[size - INTEGER_ONE];
        innerArray[size - INTEGER_ONE] = null;
        size--;

        return removableProduct;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(innerArray[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        checkElementClass(o);
        int temp = INTEGER_MINUS_ONE;
        for (int i = 0; i < size; i++) {
            if (innerArray[i].equals(o)) {
                temp = i;
            }
        }
        return temp;
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int i, int i1) {
        throw new UnsupportedOperationException();
    }

    private void checkIndex(int i) {
        if (i < INTEGER_ZERO || i > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkCollectionOnNull(Collection collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
    }

    private void checkElementOnNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    private void checkCollectionClass(Collection collection) {
        checkCollectionOnNull(collection);
        for (Object o : collection) {
            if (!(o instanceof Product)) {
                throw new ClassCastException();
            }
        }
    }

    private void checkElementClass(Object o) {
        checkElementOnNull(o);
        if (!(o instanceof Product)) {
            throw new ClassCastException();
        }
    }
}
