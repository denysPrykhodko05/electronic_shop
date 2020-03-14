package com.epam.prykhodko.entity.products;

public enum ProductsCategory {
    NOTEBOOK {
        @Override
        public String toString() {
            return "notebook";
        }
    },

    SMARTPHONE {
        @Override
        public String toString() {
            return "smartphone";
        }
    }
}
