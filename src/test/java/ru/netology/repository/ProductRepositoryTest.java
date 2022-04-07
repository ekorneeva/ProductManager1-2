package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepositoryTest {

    ProductRepository repository = new ProductRepository();

    Book Book1 = new Book(1, "Финансист", 300, "Драйзер");
    Book Book2 = new Book(2, "Титан", 300, "Драйзер");
    Book Book3 = new Book(3, "Стоик", 300, "Драйзер");
    Smartphone Phone1 = new Smartphone(4, "Galaxy", 104_000, "Samsung");
    Smartphone Phone2 = new Smartphone(5, "iPhone13mini", 79_000, "Apple");
    Smartphone Phone3 = new Smartphone(6, "iPhone13Pro", 149_000, "Apple");


    @Test
    void shouldAddProductsInRepository() {       //добавь продукты в репозиторий
        repository.save(Book1);

        Product[] expected = {Book1};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindById() {     //найди продукт по ID
        repository.save(Book1);
        repository.save(Book2);
        repository.save(Book3);
        repository.save(Phone1);
        repository.save(Phone2);
        repository.save(Phone3);


        Product expected = Phone1;
        Product actual = repository.findById(4);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {       //удали продукт по id
        repository.save(Book1);
        repository.save(Book2);
        repository.save(Book3);
        repository.save(Phone1);
        repository.save(Phone2);
        repository.save(Phone3);

        repository.removeById(2);

        Product[] expected = {Book1, Book3, Phone1, Phone2, Phone3};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}