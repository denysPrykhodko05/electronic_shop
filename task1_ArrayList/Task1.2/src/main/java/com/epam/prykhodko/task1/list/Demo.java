package com.epam.prykhodko.task1.list;

import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import com.epam.prykhodko.task1.entity.Telephone;

import java.util.Iterator;

public class Demo {

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        ListImpl list2 = new ListImpl();

        list.add(new Telephone());
        list.add(new Smartphone());
        list.add(new Notebook());

        list2.add(new Telephone());
        list2.add(new Smartphone());

        list.containsAll(list2);

        Iterator it = list.iterator(x->x.equals(new Notebook()));
        it.remove();
    }

}
