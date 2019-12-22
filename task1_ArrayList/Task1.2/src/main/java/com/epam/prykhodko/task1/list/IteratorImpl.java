package com.epam.prykhodko.task1.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class IteratorImpl<T> implements Iterator<T> {

    private ListImpl list;
    private int size;
    private int currentIndex = 0;
    private Predicate<T> predicate;
    boolean result=false;

    public IteratorImpl(Predicate<T> predicate, ListImpl list){
        this.predicate = predicate;
        this.list = list;
        this.size = list.size();
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < size) {
        if (predicate.test((T) list.get(currentIndex))) {
            result = true;
            break;
        }
        currentIndex++;
    }
        return result;
    }

    @Override
    public T next() {
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        return (T) list.get(currentIndex++);
    }

    @Override
    public void remove() {
        T temp = next();
        if (temp!=null){
            list.remove(temp);
        }
    }
}
