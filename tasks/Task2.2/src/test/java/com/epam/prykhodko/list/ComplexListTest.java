package com.epam.prykhodko.list;

import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Telephone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ComplexListTest {


    @Test
    void sizeShouldBeThree() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        int expected = 3;
        int actual = complexList.size();

        assertEquals(expected, actual);
    }

    @Test
    void sizeShouldBeFour() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("1");

        int expected = 4;
        int actual = complexList.size();

        assertEquals(expected, actual);
    }

    @Test
    void isEmptyShouldBeFalse() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        boolean expected = false;
        boolean actual = complexList.isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    void containsUnModShouldBeTrue() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        boolean expected = true;
        boolean actual = complexList.contains("1");

        assertEquals(expected, actual);
    }

    @Test
    void containsModListShouldBeTrue() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

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
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");

        int expected = 4;
        int actual = complexList.toArray().length;

        assertEquals(expected, actual);
    }

    @Test
    void addListSizeShouldBeEqualFour() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
        complexList.add("4");

        int expected = 4;
        int actual = complexList.size();

        assertEquals(expected, actual);
    }

    @Test
    void addByIndexShouldThrowIllegalStateException() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            complexList.add(1, "4");
        });
    }


    @Test
    void removeShouldThrowIllegalStateException() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            complexList.remove(1);
        });
    }

    @Test
    void removeSizeShouldBeEqualThree() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");
        complexList.remove("4");

        int expected = 3;
        int actual = complexList.size();

        assertEquals(expected, actual);
    }

    @Test
    void removeObjectShouldThrowIllegalStateException() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());


        Assertions.assertThrows(IllegalStateException.class, () -> {
            complexList.remove("8");
        });
    }

    @Test
    void addAll() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
        ComplexList complexList1 = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList1.add("5");
        complexList.addAll(complexList1);

        int expected = 7;
        int actual = complexList.size();

        assertEquals(expected, actual);

    }

    @Test
    void addAllByIndexSizeShouldBeEqualTen() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
        ComplexList complexList1 = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

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
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
        ComplexList complexList1 = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

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
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            complexList.clear();
        });
    }

    @Test
    void getShouldReturnObjectFromModList() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");
        complexList.add("5");

        String expected = "4";
        Object actual = complexList.get(3);

        assertEquals(expected, actual);
    }

    @Test
    void getShouldReturnObjectFromUnModList() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");
        complexList.add("5");

        String expected = "2";
        Object actual = complexList.get(1);

        assertEquals(expected, actual);
    }

    @Test
    void setShouldReturnPreviousElementOnThisPosition() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");
        complexList.add("6");

        String expected = "6";
        Object actual = complexList.set(4, "5");

        assertEquals(expected, actual);
    }

    @Test
    void setShouldThrowIllegalStateException() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");
        complexList.add("6");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            complexList.set(1, "5");
        });
    }

    @Test
    void addObjectByIndexShouldThrowIllegalStateException() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            complexList.add(1, "5");
        });
    }

    @Test
    void removeByIndexShouldThrowIllegalStateException() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            complexList.remove(1);
        });
    }

    @Test
    void removeByIndexShouldReturnRemovedElement() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");

        String expected = "4";
        Object actual = complexList.remove(3);

        assertEquals(expected, actual);

    }

    @Test
    void indexOfShouldReturnOne() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        int expected = 1;
        int actual = complexList.indexOf("2");

        assertEquals(expected, actual);
    }

    @Test
    void indexOfShouldReturn3() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");

        int expected = 3;
        int actual = complexList.indexOf("4");

        assertEquals(expected, actual);
    }

    @Test
    void lastIndexOfShouldReturnThree() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("2");

        int expected = 3;
        int actual = complexList.lastIndexOf("2");

        assertEquals(expected, actual);
    }

    @Test
    void lastIndexOfShouldReturnOne() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        int expected = 0;
        int actual = complexList.lastIndexOf("1");

        assertEquals(expected, actual);
    }

    @Test
    void retainAllShouldThrowIllegalStateException() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
        ComplexList complexList1 = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");
        complexList1.add("4");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            complexList.retainAll(complexList1);
        });
    }

    @Test
    void retainAllShouldReturnTrue() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
        ComplexList complexList1 = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("4");
        complexList.add("5");

        boolean expected = true;
        boolean actual = complexList.retainAll(complexList1);

        assertEquals(expected, actual);
    }

    @Test
    void removeAllShouldReturnTrue() {
        List list = new ArrayList(Arrays.asList("5", "6"));
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList.add("5");
        complexList.add("6");

        boolean expected = true;
        boolean actual = complexList.removeAll(list);
        assertEquals(expected, actual);
    }

    @Test
    void removeAllShouldThrowIllegalStateException() {
        List list = new ArrayList(Arrays.asList("1", "6"));
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            complexList.removeAll(list);
        });
    }

    @Test
    void containsAllShouldReturnTrue() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
        ComplexList complexList1 = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        boolean expected = true;
        boolean actual = complexList.containsAll(complexList1);

        assertEquals(expected, actual);
    }

    @Test
    void containsAllShouldReturnFalse() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());
        ComplexList complexList1 = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        complexList1.add("5");

        boolean expected = false;
        boolean actual = complexList.containsAll(complexList1);

        assertEquals(expected, actual);
    }

    @Test
    void toArrayShouldReturnSizeEqualThree() {
        String[] arr = new String[0];
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        int expected = 3;
        int actual = complexList.toArray(arr).length;

        assertEquals(expected, actual);

    }

    @Test
    void iteratorRemoveShouldThrowIllegalStateException() {
        ComplexList complexList = new ComplexList(Arrays.asList("1", "2", "3"), Arrays.asList());

        Iterator it = complexList.iterator();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            it.remove();
        });
    }

}