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

        Telephone telephone = new Telephone();
        Smartphone smartphone = new Smartphone();
        Notebook notebook = new Notebook();

        telephone.setCommunicationStandard("4G");
        smartphone.setModelOfTouchScreen("motorola");
        notebook.setModelOfTouchpad("asus");

        list.add(telephone);
        list.add(smartphone);
        list.add(telephone);
        list.add(notebook);

        Product[] products = new Product[0];

        list.toArray(products);
        list.lastIndexOf(telephone);
        list.stream().forEach(System.out::println);
        Iterator it = list.iterator();
        it.remove();
    }

}
