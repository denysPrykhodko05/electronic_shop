package com.epam.prykhodko.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.prykhodko.entity.products.Notebook;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.entity.products.Telephone;
import java.util.Iterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void removeShouldThrowNullPointerEception() {
        ListImpl list = new ListImpl();
        Telephone telephone = new Telephone();
        Telephone telephone2 = new Telephone();
        telephone.setCommunicationStandard("3G");
        telephone2.setCommunicationStandard("4G");
        list.add(telephone);


        Assertions.assertThrows(NullPointerException.class, () -> {
            list.add(new Telephone());
            list.contains(new Telephone());
        });
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