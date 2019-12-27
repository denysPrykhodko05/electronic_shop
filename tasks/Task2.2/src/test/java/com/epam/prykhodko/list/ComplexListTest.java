package com.epam.prykhodko.list;

import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Telephone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ComplexListTest {

  private ComplexList complexList;
  private ComplexList complexList1;

  @BeforeEach
  void init() {
    complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
    complexList1 = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
  }

  @Test
  void sizeShouldBeThree() {
    int expected = 3;
    int actual = complexList.size();

    assertEquals(expected, actual);
  }

  @Test
  void sizeShouldBeFour() {

    complexList.add("1");

    int expected = 4;
    int actual = complexList.size();

    assertEquals(expected, actual);
  }

  @Test
  void isEmptyShouldBeFalse() {
    boolean expected = false;
    boolean actual = complexList.isEmpty();

    assertEquals(expected, actual);
  }

  @Test
  void containsUnModShouldBeTrue() {
    boolean expected = true;
    boolean actual = complexList.contains("1");

    assertEquals(expected, actual);
  }

  @Test
  void containsModListShouldBeTrue() {
    complexList.add("6");

    boolean expected = true;
    boolean actual = complexList.contains("6");

    assertEquals(expected, actual);
  }

  @Test
  void iteratorShouldBeIteratorType() {
    ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
    Iterator actual = complexList.iterator();

    assertNotNull(actual);
  }

  @Test
  void toArrayArraySizeShouldBeEqualFour() {
    complexList.add("4");

    int expected = 4;
    int actual = complexList.toArray().length;

    assertEquals(expected, actual);
  }

  @Test
  void addListSizeShouldBeEqualFour() {
    complexList.add("4");

    int expected = 4;
    int actual = complexList.size();

    assertEquals(expected, actual);
  }

  @Test
  void addByIndexShouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.add(1, "4");
    });
  }


  @Test
  void removeShouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.remove(1);
    });
  }

  @Test
  void removeSizeShouldBeEqualThree() {
    complexList.add("4");
    complexList.remove("4");

    int expected = 3;
    int actual = complexList.size();

    assertEquals(expected, actual);
  }

  @Test
  void removeObjectShouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.remove("8");
    });
  }

  @Test
  void addAll() {
    complexList1.add("5");
    complexList.addAll(complexList1);

    int expected = 7;
    int actual = complexList.size();

    assertEquals(expected, actual);

  }

  @Test
  void addAllByIndexSizeShouldBeEqualTen() {
    complexList.add("4");
    complexList.add("7");
    complexList1.add("5");
    complexList1.add("6");
    complexList.addAll(4, complexList1);

    int expected = 10;
    int actual = complexList.size();

    assertEquals(expected, actual);
  }

  @Test
  void addAllByIndexShouldThrowIllegalStateException() {
    complexList.add("4");
    complexList.add("7");
    complexList1.add("5");
    complexList1.add("6");

    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.addAll(1, complexList1);
    });

  }

  @Test
  void clear() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.clear();
    });
  }

  @Test
  void getShouldReturnObjectFromModList() {
    complexList.add("4");
    complexList.add("5");

    String expected = "4";
    Object actual = complexList.get(3);

    assertEquals(expected, actual);
  }

  @Test
  void getShouldReturnObjectFromUnModList() {
    complexList.add("4");
    complexList.add("5");

    String expected = "2";
    Object actual = complexList.get(1);

    assertEquals(expected, actual);
  }

  @Test
  void setShouldReturnPreviousElementOnThisPosition() {
    complexList.add("4");
    complexList.add("6");

    String expected = "6";
    Object actual = complexList.set(4, "5");

    assertEquals(expected, actual);
  }

  @Test
  void setShouldThrowIllegalStateException() {
    complexList.add("4");
    complexList.add("6");

    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.set(1, "5");
    });
  }

  @Test
  void addObjectByIndexShouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.add(1, "5");
    });
  }

  @Test
  void removeByIndexShouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.remove(1);
    });
  }

  @Test
  void removeByIndexShouldReturnRemovedElement() {
    complexList.add("4");

    String expected = "4";
    Object actual = complexList.remove(3);

    assertEquals(expected, actual);

  }

  @Test
  void indexOfShouldReturnOne() {
    int expected = 1;
    int actual = complexList.indexOf("2");

    assertEquals(expected, actual);
  }

  @Test
  void indexOfShouldReturn3() {
    complexList.add("4");

    int expected = 3;
    int actual = complexList.indexOf("4");

    assertEquals(expected, actual);
  }

  @Test
  void lastIndexOfShouldReturnThree() {
    complexList.add("2");

    int expected = 3;
    int actual = complexList.lastIndexOf("2");

    assertEquals(expected, actual);
  }

  @Test
  void lastIndexOfShouldReturnOne() {
    int expected = 0;
    int actual = complexList.lastIndexOf("1");

    assertEquals(expected, actual);
  }

  @Test
  void retainAllShouldThrowIllegalStateException() {
    complexList.add("4");
    complexList1.add("4");

    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.retainAll(complexList1);
    });
  }

  @Test
  void retainAllShouldReturnTrue() {
    complexList.add("4");
    complexList.add("5");

    boolean expected = true;
    boolean actual = complexList.retainAll(complexList1);

    assertEquals(expected, actual);
  }

  @Test
  void removeAllShouldReturnTrue() {
    List list = new ArrayList(Arrays.asList("5", "6"));
    complexList.add("5");
    complexList.add("6");

    boolean expected = true;
    boolean actual = complexList.removeAll(list);
    assertEquals(expected, actual);
  }

  @Test
  void removeAllShouldThrowIllegalStateException() {
    List list = new ArrayList(Arrays.asList("1", "6"));
    Assertions.assertThrows(IllegalStateException.class, () -> {
      complexList.removeAll(list);
    });
  }

  @Test
  void containsAllShouldReturnTrue() {
    boolean expected = true;
    boolean actual = complexList.containsAll(complexList1);

    assertEquals(expected, actual);
  }

  @Test
  void containsAllShouldReturnFalse() {
    complexList1.add("5");

    boolean expected = false;
    boolean actual = complexList.containsAll(complexList1);

    assertEquals(expected, actual);
  }

  @Test
  void toArrayShouldReturnSizeEqualThree() {
    int expected = 3;
    int actual = complexList.toArray(new String[0]).length;

    assertEquals(expected, actual);

  }

  @Test
  void iteratorRemoveShouldThrowIllegalStateException() {
    Iterator it = complexList.iterator();

    Assertions.assertThrows(IllegalStateException.class, () -> {
      it.remove();
    });
  }

}