package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private final ProductRepository repository = new ProductRepository();
    private final ProductManager manager = new ProductManager(repository);


    Book book1 = new Book(1, "Финансист", 300, "Драйзер");
    Book book2 = new Book(2, "Титан", 300, "Драйзер");
    Book book3 = new Book(3, "Стоик", 300, "Драйзерр");
    Smartphone phone1 = new Smartphone(4, "Galaxy", 104_000, "Samsung");
    Smartphone phone2 = new Smartphone(5, "iPhone13mini", 79_000, "Apple");
    Smartphone phone3 = new Smartphone(6, "iPhone13Pro", 149_000, "Apple");

    @Test
    void shouldAddProducts() {        //добавь продукты (через репозиторий и менеджер)
        repository.save(book1);
        manager.add(book2);
        repository.save(phone1);
        manager.add(phone2);



        Product[] expected = {book1, book2, phone1, phone2};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindById() {       //найди продукт по ID
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product expected = phone3;
        Product actual = manager.findById(6);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByIdNoId() {       //найди продукт по ID, нет id
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product actual = manager.findById(7);
        assertNull(actual);
    }

    @Test
    void shouldRemoveById() {     //удали продукт по id (через репозиторий и менеджер)
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        repository.removeById(5);
        manager.removeById(2);

        Product[] expected = {book1, book3, phone1, phone3};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookByName() {     //найди книгу по имени
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {book2};
        Product[] actual = manager.searchBy("Титан");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookByNameNoProduct() {     //найди книгу по имени, нет такого товара
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {};
        Product[] actual = manager.searchBy("book4");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookByNameSeveralProduct() {     //найди книгу по имени, несколько таких товаров
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {book1, book2, book3};
        Product[] actual = manager.searchBy("Драйзер");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookByAuthor() {     //найди книгу по автору
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {book3};
        Product[] actual = manager.searchBy("Драйзерр");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneByName() {     //найди смартфон по имени
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {phone2};
        Product[] actual = manager.searchBy("iPhone13mini");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneByProducer() {     //найди смартфон по производителю
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(phone1);
        repository.save(phone2);
        repository.save(phone3);

        Product[] expected = {phone1};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }


}

