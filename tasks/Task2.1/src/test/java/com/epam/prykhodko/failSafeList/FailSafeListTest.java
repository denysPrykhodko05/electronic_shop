package com.epam.prykhodko.failSafeList;

import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Smartphone;
import com.epam.prykhodko.task1.entity.Telephone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FailSafeListTest {

    @Test
    void addSizeShouldBeEqualOne() {
        FailSafeList failSafeList = new FailSafeList();
        failSafeList.add(new Telephone());

        int expected = 1;
        int actual = failSafeList.size();

        assertEquals(expected, actual);
    }

    @Test
    void addShouldThrowIndexOutOfBoundsException() {
        FailSafeList failSafeList = new FailSafeList();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            failSafeList.add(-1, new Telephone());
        });
    }

    @Test
    void setShouldReturnPreviousElement() {
        FailSafeList failSafeList = new FailSafeList();
        failSafeList.add(new Telephone());
        failSafeList.add(new Telephone());
        failSafeList.add(new Telephone());

        assertNotNull(failSafeList.set(1, new Notebook()));
    }

    @Test
    void setShouldThrowIndexOutOfBoundException() {
        FailSafeList failSafeList = new FailSafeList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            failSafeList.add(-1, new Telephone());
        });
    }

    @Test
    void removeByIndexShouldThrowIndexOutOfBoundException() {
        FailSafeList failSafeList = new FailSafeList();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            failSafeList.remove(-1);
        });
    }

    @Test
    void removeByIndexShouldReturnRemovedObject() {
        FailSafeList failSafeList = new FailSafeList();

        failSafeList.add(new Telephone());
        failSafeList.add(new Telephone());
        failSafeList.add(new Telephone());

        assertNotNull(failSafeList.remove(1));
    }

    @Test
    void removeByObjectShouldReturnTrue() {
        FailSafeList failSafeList = new FailSafeList();
        Telephone telephone = new Telephone();

        failSafeList.add(telephone);

        boolean expected = true;
        boolean actual = failSafeList.remove(telephone);

        assertEquals(expected, actual);
    }

    @Test
    void removeByObjectShouldReturnFalse() {
        FailSafeList failSafeList = new FailSafeList();
        Telephone telephone = new Telephone();
        Notebook notebook = new Notebook();

        failSafeList.add(telephone);

        boolean expected = false;
        boolean actual = failSafeList.remove(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void addAllSizeShouldBeEqualTwo() {
        FailSafeList failSafeList = new FailSafeList();
        FailSafeList failSafeList1 = new FailSafeList();

        failSafeList.add(new Telephone());
        failSafeList1.add(new Notebook());
        failSafeList.addAll(failSafeList1);

        int expected = 2;
        int actual = failSafeList.size();

        assertEquals(expected, actual);
    }

    @Test
    void removeAll() {

        FailSafeList failSafeList = new FailSafeList();
        FailSafeList failSafeList1 = new FailSafeList();

        Telephone telephone = new Telephone();
        Notebook notebook = new Notebook();

        telephone.setCommunicationStandard("4G");
        notebook.setModelOfTouchpad("asus");

        failSafeList.add(telephone);
        failSafeList.add(notebook);
        failSafeList1.add(notebook);
        failSafeList.removeAll(failSafeList1);

        int expected = 1;
        int actual = failSafeList.size();

        assertEquals(expected, actual);
    }


}