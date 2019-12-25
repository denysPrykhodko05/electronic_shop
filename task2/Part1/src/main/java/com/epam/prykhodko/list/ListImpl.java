package com.epam.prykhodko.part1;

import com.epam.prykhodko.entity.Product;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

public class ListImpl implements List<Product> {
    private static final int DEFAULT_CAPACITY = 10;

    private Product[] innerArray;
    private int size = INTEGER_ZERO;

    ListImpl() {
        innerArray = new Product[DEFAULT_CAPACITY];
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
    public Iterator<Product> iterator() {
        return iterator(t -> true);
    }

    public Iterator<Product> iterator(Predicate<Product> predicate) {
        return new IteratorImpl(predicate);

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

    @Override
    public boolean add(Product o) {
        add(size, o);
        return true;
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
    public boolean containsAll(Collection collection) {
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
    public boolean removeAll(Collection collection) {
        int oldSize = size;
        for (Object o : collection) {
            remove(o);
        }
        if (oldSize != size) {
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection collection) {
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
    public void clear() {
        innerArray = new Product[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public Product get(int i) {
        checkIndex(i);
        return innerArray[i];
    }

    @Override
    public Product set(int i, Product o) {
        checkIndex(i);
        Product oldElement = innerArray[i];
        innerArray[i] = o;
        return oldElement;
    }

    @Override
    public void add(int i, Product o) {
        checkIndex(i);
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

    class IteratorImpl implements Iterator<Product> {

        private int size;
        private int currentIndex = 0;
        private Predicate<Product> predicate;
        boolean result = false;

        public IteratorImpl(Predicate<Product> predicate) {
            this.predicate = predicate;
            this.size = size();
        }

        @Override
        public boolean hasNext() {
            while (currentIndex < size) {
                if (predicate.test(innerArray[currentIndex])) {
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
            return innerArray[currentIndex++];
        }

        @Override
        public void remove() {
            Product temp = next();
            if (temp != null) {
                ListImpl.this.remove(temp);
            }
        }
    }
}
