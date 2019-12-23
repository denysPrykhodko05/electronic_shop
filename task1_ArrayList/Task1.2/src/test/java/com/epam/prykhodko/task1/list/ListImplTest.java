package com.epam.prykhodko.task1.list;

import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Telephone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ListImplTest {

    @Test
    void sizeShouldBeZero() {
        ListImpl list = new ListImpl();

        int expected = 0;
        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test
    void listShouldBeEmpty() {
        ListImpl list = new ListImpl();

        boolean expected = true;
        boolean actual = list.isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    void listShouldContainTelephone() {
        ListImpl list = new ListImpl();
        Telephone telephone = new Telephone();
        telephone.setCommunicationStandard("4G");
        list.add(telephone);

        boolean expected = true;
        boolean actual = list.contains(telephone);

        assertEquals(expected, actual);
    }

    @Test
    void containsShouldThrowNullPointerException() {
        ListImpl list = new ListImpl();

        Assertions.assertThrows(NullPointerException.class, () -> {
            list.add(new Telephone());
            list.contains(new Telephone());
        });
    }

    @Test
    void comtainsShouldThrowClassCastException() {
        ListImpl list = new ListImpl();

        Assertions.assertThrows(ClassCastException.class, () -> {
            list.add(new Telephone());
            list.contains(new String());
        });
    }

    @Test
    void hasNextShouldReturnFalse() {
        ListImpl list = new ListImpl();

        boolean expected = false;
        boolean actual = list.iterator().hasNext();

        assertEquals(expected, actual);
    }

    @Test
    void nextShouldRetrunTelephone() {
        ListImpl list = new ListImpl();
        Telephone telephone = new Telephone();
        telephone.setCommunicationStandard("4G");
        list.add(telephone);

        Telephone expected = telephone;
        Telephone actual = (Telephone) list.iterator().next();

        assertEquals(expected, actual);
    }

    @Test
    void removeShouldRemoveOneElement() {
        ListImpl list = new ListImpl();
        Telephone telephone = new Telephone();
        telephone.setCommunicationStandard("4G");
        list.add(telephone);
        Iterator it = list.iterator();
        it.remove();

        int expected = 0;
        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddTelephone() {
        ListImpl list = new ListImpl();

        boolean actual = list.add(new Telephone());
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    void shouldAddNoteBookIntoSpecifiedPosition() {
        ListImpl list = new ListImpl();
        list.add(new Telephone());
        list.add(new Telephone());
        list.add(new Telephone());
        list.add(1, new Notebook());

        int actual = list.size();
        int expected = 4;

        assertEquals(actual, expected);
    }

    @Test
    void listShouldIncreaseHisSize() {
        ListImpl list = new ListImpl();
        for (int i = 0; i < 9; i++) {
            list.add(new Telephone());
        }
        list.add(1, new Notebook());

        int actual = list.size();
        int expected = 10;

        assertEquals(actual, expected);

    }

    @Test
    void removeShouldReturnTrue() {
        ListImpl list = new ListImpl();
        Telephone telephone = new Telephone();
        telephone.setCommunicationStandard("3G");
        list.add(telephone);

        boolean expected = true;
        boolean actual = list.remove(telephone);

        assertEquals(expected, actual);
    }

    @Test
    void removeShouldReturnFalse() {
        ListImpl list = new ListImpl();
        Telephone telephone = new Telephone();
        Telephone telephone2 = new Telephone();
        telephone.setCommunicationStandard("3G");
        telephone2.setCommunicationStandard("4G");
        list.add(telephone);

        boolean expected = false;
        boolean actual = list.remove(telephone2);

        assertEquals(expected, actual);
    }

    @Test
    void removeShouldReturnTelephoneFromSpecifiedPosition() {
        ListImpl list = new ListImpl();
        Telephone telephone = new Telephone();
        Notebook notebook = new Notebook();
        telephone.setCommunicationStandard("3G");
        notebook.setModelOfTouchpad("asus");
        list.add(telephone);
        list.add(notebook);

        Telephone expected = telephone;
        Product actual = list.remove(0);

        assertEquals(expected, actual);
    }

    @Test
    void getShouldReturnTelephoneFromSpecifiedPosition() {
        ListImpl list = new ListImpl();
        Telephone telephone = new Telephone();
        Telephone telephone2 = new Telephone();
        telephone.setCommunicationStandard("4G");
        telephone.setCommunicationStandard("5G");
        list.add(telephone);
        list.add(telephone2);

        Product actual = list.get(1);
        Product expected = telephone2;

        assertEquals(actual, expected);
    }

    @Test
    void getShouldReturnIndexOutOfBoundsException() {
        ListImpl list = new ListImpl();
        Telephone telephone = new Telephone();
        telephone.setCommunicationStandard("4G");
        list.add(telephone);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(3);
        });
    }

    @Test
    void toArrayShouldReturnTrue(){
        ListImpl list = new ListImpl();

        int actual = list.toArray().length;
        int expected = 10;

        assertEquals(actual,expected);
    }

    @Test
    void containsAllShouldReturnTrue(){
        ListImpl list = new ListImpl();
        ListImpl list2 = new ListImpl();
        list.add(new Telephone());
        list.add(new Telephone());
        list.add(new Notebook());

        list2.add(new Telephone());
        list2.add(new Telephone());
        list2.add(new Notebook());

        boolean actual = list.containsAll(list2);
        boolean expected = true;

        assertEquals(expected,actual);
    }



}