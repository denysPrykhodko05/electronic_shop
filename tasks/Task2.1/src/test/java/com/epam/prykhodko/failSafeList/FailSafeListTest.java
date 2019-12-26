package com.epam.prykhodko.failSafeList;

import com.epam.prykhodko.task1.entity.Telephone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class FailSafeListTest {

    @Test
    void iteratorShouldNotEqualNull() {
        FailSafeList failSafeList = new FailSafeList();
        Iterator actual = failSafeList.iterator();

        assertNotNull(actual);
    }

    @Test
    void toArrayShouldHaveSizeTen() {
        FailSafeList failSafeList = new FailSafeList();

        int expected = 10;
        int actual = failSafeList.toArray().length;

        assertEquals(expected, actual);
    }

    @Test
    void toArrayShouldReturnArrayWhichSizeEqualZero() {
        FailSafeList failSafeList = new FailSafeList();


        int expected = 0;
        int actual = failSafeList.toArray(new Object[0]).length;

        assertEquals(expected, actual);
    }

    @Test
    void toArrayShouldThrowArrayStoreException() {
        FailSafeList failSafeList = new FailSafeList();

        int expected = 0;
        int actual = failSafeList.toArray(new Object[0]).length;

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            failSafeList.toArray(new Telephone[0]);
        });
    }

    @Test
    void testIterator() {
        FailSafeList failSafeList = new FailSafeList();

        failSafeList.add(new Telephone());

        assertNotNull(failSafeList.iterator(x -> x.equals(new Telephone())));

    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void contains() {
    }

    @Test
    void add() {
    }

    @Test
    void set() {
    }

    @Test
    void remove() {
    }

    @Test
    void containsAll() {
    }

    @Test
    void addAll() {
    }

    @Test
    void testAddAll() {
    }

    @Test
    void retainAll() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void get() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void listIterator() {
    }

    @Test
    void testListIterator() {
    }

    @Test
    void subList() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}