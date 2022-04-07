package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository { // репозиторий работает с продуктами

    private Product[] items = new Product[0]; // хранит массив продуктов

    public void save(Product item) {   // умеет сохранять продукт
        int length = items.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Product[] findAll() { // умеет возвращать все продукты
        return items;
    }

    public Product findById(int id) { // умеет искать по id
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {   // умеет удалять продукт по id
        int length = items.length - 1;
        Product[] tmp = new Product[length];
        int index = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }
}
