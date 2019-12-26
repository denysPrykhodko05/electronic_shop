package com.epam.prykhodko.fail_safe_list;

import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.list.ListImpl;

import java.util.List;

import java.awt.*;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

public class FailSafeList implements List<Product> {

    private static final int DEFAULT_CAPACITY = 10;

    private Product[] innerArray;
    private int size = INTEGER_ZERO;

    public FailSafeList() {
        innerArray = new Product[DEFAULT_CAPACITY];
    }


    @Override
    public Iterator<Product> iterator() {
        return iterator(t -> true);
    }

    @Override
    public Object[] toArray() {
        return Arrays.stream(innerArray).toArray();
    }

    @Override
    public Object[] toArray(Object[] objects) {
        if (!(objects instanceof Product[])) {
            throw new ArrayStoreException();
        }

        if (objects.length < size) {
            objects = new Object[size];
            return Arrays.copyOf(innerArray, size, objects.getClass());
        }
        return Arrays.copyOf(innerArray, size, objects.getClass());
    }

    public Iterator<Product> iterator(Predicate<Product> predicate) {
        return new IteratorImpl(predicate, innerArray, size);
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
        return indexOf(o) > INTEGER_MINUS_ONE;

    }

    @Override
    public boolean add(Product o) {
        add(size, o);
        return true;
    }

    @Override
    public Product set(int i, Product o) {
        checkIndex(i);
        Product oldElement = innerArray[i];
        innerArray[i] = o;
        return oldElement;
    }

    @Override
    public boolean remove(Object o) {
        int position = indexOf(o);

        if (position > INTEGER_MINUS_ONE) {
            remove(position);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            try {
                if (!contains(o)) {
                    return false;
                }
            } catch (NullPointerException ex) {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Product> collection) {
        addAll(size, collection);
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Product> collection) {
        checkIndex(i);

        Product[] tempArray = new Product[innerArray.length + collection.size()];
        System.arraycopy(innerArray, 0, tempArray, 0, i);
        System.arraycopy(collection.toArray(), 0, tempArray, i, collection.size());
        System.arraycopy(innerArray, i, tempArray, i + collection.size(), size);
        innerArray = tempArray;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        Product[] tempArray = new Product[innerArray.length];
        int oldSize = size;
        int tempSize = INTEGER_ZERO;
        for (Object o : collection) {
            if (contains(o)) {
                tempArray[tempSize++] = (Product) o;
            }
        }
        size = tempSize;
        if (oldSize != size) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        int oldSize = size;
        for (Object o : collection) {
            remove(o);
        }
        return oldSize != size;
    }

    @Override
    public void clear() {
        innerArray = Arrays.copyOf(innerArray, innerArray.length);
        innerArray = new Product[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public Product get(int i) {
        checkIndex(i);
        return innerArray[i];
    }

    @Override
    public void add(int i, Product o) {
        checkIndex(i);
        innerArray = Arrays.copyOf(innerArray, innerArray.length);
        if (size >= innerArray.length * 0.8) {
            Product[] tempArr = Arrays.copyOf(innerArray, size);
            innerArray = new Product[size + size / INTEGER_TWO + INTEGER_ONE];
            innerArray = Arrays.copyOf(tempArr, innerArray.length);
        }
        innerArray[size++] = o;
    }

    @Override
    public Product remove(int i) {
        Product removableProduct;
        checkIndex(i);
        innerArray = Arrays.copyOf(innerArray, innerArray.length);
        for (int j = i; j < size - 1; j++) {
            Product temp = innerArray[j];
            innerArray[j] = innerArray[j + INTEGER_ONE];
            innerArray[j + INTEGER_ONE] = temp;
        }
        removableProduct = innerArray[size - INTEGER_ONE];
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
        for (int i = size - 1; i != 0; i--) {
            if (innerArray[i].equals(o)) {
                return i;
            }
        }
        return INTEGER_MINUS_ONE;
    }

    @Override
    public ListIterator<Product> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<Product> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Product> subList(int i, int i1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FailSafeList that = (FailSafeList) o;
        return size == that.size &&
                Arrays.equals(innerArray, that.innerArray);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(innerArray);
        return result;
    }

    private void checkIndex(int i) {
        if (i < INTEGER_ZERO || i > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    static class IteratorImpl implements Iterator<Product> {

        private int size;
        private int currentIndex = INTEGER_ZERO;
        private Predicate<Product> predicate;
        boolean result = false;
        Product[] snapshot;

        public IteratorImpl(Predicate<Product> predicate, Product[] array, int size) {
            snapshot = array;
            this.predicate = predicate;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            while (currentIndex < size) {
                if (predicate.test(snapshot[currentIndex])) {
                    result = true;
                    break;
                }
                currentIndex++;
            }
            return result;
        }

        @Override
        public Product next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            result = false;
            return snapshot[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
