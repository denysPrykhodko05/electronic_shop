In linkedHashMap elements are located in orderRepository that they were inserted into map. In hashMap elements are located sorted by their hashcode.
In our task we are use two method to calculate hashcode(by string length and by calculating sum of the first four symbols in string);
The more effective method is calculating sum, because we will have less chance of collision. But when a collision occurs elements will located in linked list.
LinkedHashMap has attribute "accessOrder" that is responsible for orderRepository of elements in map when using iterator. If we are set up true for accessOrder, the last used element will be in the end,
when use false for accessOrder the elements will be in orderRepository which elements were inserted.