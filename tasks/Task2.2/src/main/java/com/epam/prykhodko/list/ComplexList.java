package com.epam.prykhodko.list;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ComplexList<T> implements List<T> {

  private List<T> mod;
  private List<T> unMod;

  public ComplexList(List<T> list, List<T> list1) {
    unMod = new ArrayList<>(list);
    mod = new ArrayList<>(list1);
  }

  @Override
  public int size() {
    return unMod.size() + mod.size();
  }

  @Override
  public boolean isEmpty() {
    return unMod.size() + mod.size() == INTEGER_ZERO;
  }

  @Override
  public boolean contains(Object o) {
    return mod.contains(o) || unMod.contains(o);
  }

  @Override
  public Iterator<T> iterator() {
    return new IteratorImpl();
  }

  @Override
  public Object[] toArray() {
    Object[] temp = new Object[unMod.size() + mod.size()];
    System.arraycopy(unMod.toArray(), INTEGER_ZERO, temp, INTEGER_ZERO, unMod.size());
    System.arraycopy(mod.toArray(), INTEGER_ZERO, temp, unMod.size(), mod.size());
    return temp;
  }

  @Override
  public Object[] toArray(Object[] objects) {
    if (objects.length < size()) {
      objects = new Object[size()];
    }
    System.arraycopy(toArray(), INTEGER_ZERO, objects, INTEGER_ZERO, size());
    return objects;
  }

  @Override
  public boolean add(T o) {
    mod.add(o);
    return true;
  }

  @Override
  public void add(int i, T o) {
    checkIndex(i);
    checkUnmodList(i);
    mod.add(position(i), o);
  }

  @Override
  public boolean remove(Object o) {
    int index = indexOf(o);
    checkUnmodList(index);
    remove(index);
    return true;
  }

  @Override
  public T remove(int i) {
    checkIndex(i);
    checkUnmodList(i);
    return mod.remove(position(i));
  }

  @Override
  public boolean addAll(Collection collection) {
    return mod.addAll(collection);
  }

  @Override
  public boolean addAll(int i, Collection collection) {
    checkUnmodList(i);
    return mod.addAll(position(i), collection);
  }

  @Override
  public void clear() {
    if (!unMod.isEmpty()) {
      throw new IllegalStateException();
    }
    mod.clear();
  }

  @Override
  public T get(int i) {
    checkIndex(i);
    if (i < unMod.size()) {
      return unMod.get(i);
    }
    return mod.get(position(i));
  }

  @Override
  public T set(int i, T o) {
    checkIndex(i);
    checkUnmodList(i);
    T prevEl = mod.get(position(i));
    mod.set(position(i), o);
    return prevEl;
  }

  @Override
  public int indexOf(Object o) {
    int index = unMod.indexOf(o);
    if (index > INTEGER_MINUS_ONE) {
      return index;
    }
    return mod.indexOf(o) + unMod.size();
  }

  @Override
  public int lastIndexOf(Object o) {
    int index = mod.lastIndexOf(o);
    if (index > INTEGER_MINUS_ONE) {
      return index + unMod.size();
    }
    return unMod.lastIndexOf(o);
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

  @Override
  public boolean retainAll(Collection collection) {
    for (Object o : collection) {
      if (!unMod.contains(o)) {
        throw new IllegalStateException();
      }
    }
    return mod.retainAll(collection);
  }

  @Override
  public boolean removeAll(Collection collection) {
    for (Object o : collection) {
      if (unMod.contains(o)) {
        throw new IllegalStateException();
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

  private void checkIndex(int i) {
    if (i < INTEGER_ZERO || i > size()) {
      throw new IndexOutOfBoundsException();
    }
  }

  private void checkUnmodList(int index) {
    if (index < unMod.size()) {
      throw new IllegalStateException();
    }
  }

  private int position(int index) {
    return index - unMod.size();
  }

  class IteratorImpl implements Iterator<T> {

    private int currentIndex = INTEGER_ZERO;
    private boolean nextCalled;


    @Override
    public boolean hasNext() {
      return currentIndex < size();
    }

    @Override
    public T next() {
      nextCalled = true;
      int index = currentIndex;
      currentIndex++;
      if (index < unMod.size()) {
        return unMod.get(index);
      }
      return mod.get(index - unMod.size());
    }

    @Override
    public void remove() {
      if (!nextCalled) {
        throw new IllegalStateException();
      }
      checkUnmodList(currentIndex);
      mod.remove(currentIndex - unMod.size());
    }
  }
}
