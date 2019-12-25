package com.epam.prykhodko.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

public class ComplexList implements List<String> {

    private List<String> mod = new ArrayList<>();
    private List<String> unMod = new ArrayList<>(Arrays.asList("1", "2", "3"));
    private int size;

    public ComplexList() {
        size = unMod.size() + mod.size();
    }

    @Override
    public int size() {
        return unMod.size() + mod.size();
    }

    @Override
    public boolean isEmpty() {
        return unMod.size() + mod.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (unMod.contains(o)) {
            return true;
        }
        return mod.contains(o);
    }

    @Override
    public Iterator iterator() {
        return new IteratorImpl();
    }

    @Override
    public Object[] toArray() {
        Object[] temp = new Object[unMod.size() + mod.size()];
        System.arraycopy(unMod.toArray(), 0, temp, 0, unMod.size());
        System.arraycopy(mod.toArray(), 0, temp, unMod.size(), mod.size());
        return temp;
    }

    @Override
    public boolean add(String o) {
        add(size, o);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > unMod.size()) {
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        return mod.addAll(collection);
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        if (i < unMod.size()) {
            throw new UnsupportedOperationException();
        }
        return mod.addAll(i - unMod.size(), collection);
    }

    @Override
    public void clear() {
       throw new UnsupportedOperationException();
    }

    @Override
    public String get(int i) {
        checkIndex(i);
        if (i < unMod.size()) {
            return unMod.get(i);
        }
        return mod.get(i - unMod.size());
    }

    @Override
    public String set(int i, String o) {
        checkIndex(i);
        if (i < unMod.size()) {
            throw new UnsupportedOperationException();
        }
        mod.set(i - unMod.size(), o);
        return null;
    }

    @Override
    public void add(int i, String o) {
        checkIndex(i);
        if (unMod.size() > i) {
            throw new UnsupportedOperationException();
        }

        mod.add(i - unMod.size(), o);
        size++;
    }

    @Override
    public String remove(int i) {
        checkIndex(i);
        if (unMod.size() > i) {
            throw new UnsupportedOperationException();
        }
        size--;
        return mod.remove(i - unMod.size());
    }

    @Override
    public int indexOf(Object o) {
        int index = unMod.indexOf(o);
        if (index > INTEGER_MINUS_ONE) {
            return index;
        }
        index = mod.indexOf(o);
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = mod.lastIndexOf(o);
        if (index > INTEGER_MINUS_ONE) {
            return index;
        }
        return unMod.lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int i) {
        return null;
    }

    @Override
    public List subList(int i, int i1) {
        return null;
    }

    @Override
    public boolean retainAll(Collection collection) {
        for (Object o : collection) {
            if (unMod.contains(o)) {
                throw new UnsupportedOperationException();
            }
        }

        return mod.retainAll(collection);
    }

    @Override
    public boolean removeAll(Collection collection) {
        for (Object o : collection) {
            if (unMod.contains(o)) {
                throw new UnsupportedOperationException();
            }
        }
        return mod.removeAll(collection);
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object o : collection) {
            if (!unMod.contains(o) && !mod.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return new Object[0];
    }

    private void checkIndex(int i) {
        if (i < INTEGER_ZERO || i > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    class IteratorImpl implements Iterator {

        private int currentIndex = INTEGER_ZERO;
        private boolean nextCalled;


        @Override
        public boolean hasNext() {
            if (currentIndex < size) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            nextCalled = true;
            if (currentIndex < unMod.size()) {
                return unMod.get(currentIndex);
            }
            return mod.get(currentIndex - unMod.size());
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException();
            }
            if (currentIndex < unMod.size()) {
                throw new UnsupportedOperationException();
            }
            mod.remove(currentIndex - unMod.size());
        }
    }
}
