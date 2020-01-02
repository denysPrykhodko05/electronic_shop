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
      checkContains(o, list);
      list.add(o);
    }
    addAll(c);
  }

  @Override
  public boolean add(E o) {
    checkContains(o);
    return super.add(o);
  }

  @Override
  public void add(int index, E element) {
    checkContains(element);
    super.add(index, element);
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    List<E> list = new ArrayList<>();
    for (E o : c) {
      checkContains(o, this, list);
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

    checkContains(element);
    return super.set(index, element);
  }

  private void checkContains(E o) {
    if (contains(o)) {
      throw new IllegalArgumentException();
    }
  }

  private void checkContains(E o, List<E> list) {
    if (list.contains(o)) {
      throw new IllegalArgumentException();
    }
  }

  private void checkContains(E o, List<E> list, List<E> list2) {
    if (list.contains(o) || list2.contains(o)) {
      throw new IllegalArgumentException();
    }
  }

}
