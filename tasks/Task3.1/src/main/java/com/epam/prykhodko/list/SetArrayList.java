package com.epam.prykhodko.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SetArrayList<E> extends ArrayList<E> {

  public SetArrayList() {
    super();
  }

  public SetArrayList(Collection<? extends E> c) {
    List<E> list = new ArrayList<>();
    for (E o : c) {
      if (list.contains(o)) {
        throw new IllegalArgumentException();
      }
      list.add(o);
    }
    addAll(c);
  }

  @Override
  public boolean add(E o) {
    if (contains(o)) {
      throw new IllegalArgumentException();
    }
    return super.add(o);
  }

  @Override
  public void add(int index, E element) {
    if (contains(element)) {
      throw new IllegalArgumentException();
    }
    super.add(index, element);
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    List<E> list = new ArrayList<>();
    for (E o : c) {
      if (contains(o) || list.contains(o)) {
        throw new IllegalArgumentException();
      }
      list.add(o);
    }
    return super.addAll(index, list);
  }

  @Override
  public boolean addAll(Collection c) {
    return addAll(size(), c);
  }

  @Override
  public E set(int index, E element) {

    if (element.equals(get(index))) {
      return super.set(index, element);
    }

    if (contains(element)) {
      throw new IllegalArgumentException();
    }
    return super.set(index, element);
  }
}
