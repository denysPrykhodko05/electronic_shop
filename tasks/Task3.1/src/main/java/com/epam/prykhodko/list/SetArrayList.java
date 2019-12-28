package com.epam.prykhodko.list;

import java.util.ArrayList;
import java.util.Collection;

public class SetArrayList<E> extends ArrayList<E> {

  public SetArrayList() {
    super();
  }

  @Override
  public boolean add(E o) {
    if (contains(o)) {
      return false;
    }
    add(size(), o);
    return true;
  }

  @Override
  public void add(int index, E element) {
    if (contains(element)) {
      return;
    }
    super.add(index, element);
  }

  @Override
  public boolean addAll(int index, Collection c) {
    for (Object o : c) {
      if (contains(o)) {
        return false;
      }
    }
    return super.addAll(index, c);
  }

  @Override
  public boolean addAll(Collection c) {
    return addAll(size(), c);
  }

  @Override
  public E set(int index, E element) {
    if (contains(element)) {
      return null;
    }
    return super.set(index, element);
  }
}
