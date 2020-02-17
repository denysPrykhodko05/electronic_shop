package com.epam.prykhodko.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class SetArrayList<E> extends ArrayList<E> {

  public SetArrayList() {
    super();
  }

  public SetArrayList(Collection<? extends E> c) {
    List<E> list = new ArrayList<>();
    for (E o : c) {
      checkContains(o, list::contains);
      list.add(o);
    }
    addAll(c);
  }

  @Override
  public boolean add(E o) {
    checkContains(o, this::contains);
    return super.add(o);
  }

  @Override
  public void add(int index, E element) {
    checkContains(element, this::contains);
    super.add(index, element);
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    List<E> list = new ArrayList<>();
    for (E o : c) {
      checkContains(o, (e) -> contains(e) || list.contains(e));
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
    checkContains(element, this::contains);
    return super.set(index, element);
  }

  private void checkContains(E o, Predicate<E> predicate) {
    if (predicate.test(o)) {
      throw new IllegalArgumentException();
    }
  }
}
