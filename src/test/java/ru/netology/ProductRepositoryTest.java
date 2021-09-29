package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepositoryTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product first = new Book(1, "hamlet", 1, "shakespeare");
    private Product second = new Book(2, "harry potter", 1, "joan rowling");
    private Product third = new Book(3, "idiot", 1, "dostoevsky");
    private Product fourth = new Smartphone(4, "S10", 1, "samsung");
    private Product fifth = new Smartphone(5, "3310", 1, "nokia");
    private Product sixth = new Smartphone(6, "s20", 1, "samsung");
    private Product seventh = new Book(7, "instruction", 1, "samsung");
    private Product eighth = new Smartphone(8, "a90", 1, "samsung");

    @Test
    public void addBook() {
        manager.add(third);
        Product[] expected = {new Book(3, "idiot", 1, "dostoevsky")};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }


    @Test
    public void saveSmartphone() {
        manager.add(sixth);
        Product[] expected = {new Smartphone(6, "s20", 1, "samsung")};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        manager.add(third);
        Product[] expected = {new Book(3, "idiot", 1, "dostoevsky")};
        Product[] actual = manager.searchBy("dostoevsky");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByName() {
        manager.add(first);
        Product[] expected = {new Book(1, "hamlet", 1, "shakespeare")};
        Product[] actual = manager.searchBy("hamlet");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByManufacturer() {
        manager.add(fifth);
        Product[] expected = {new Smartphone(5, "3310", 1, "nokia")};
        Product[] actual = manager.searchBy("nokia");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySmartphoneName() {
        manager.add(fifth);
        Product[] expected = {new Smartphone(5, "3310", 1, "nokia")};
        Product[] actual = manager.searchBy("3310");
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeById() {
        manager.add(eighth);
        manager.add(sixth);
        manager.add(fourth);
        manager.removeById(6);
        Product[] expected = {new Smartphone(4, "S10", 1, "samsung"),
                new Smartphone(8, "a90", 1, "samsung")};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void useEqualsProduct() {
        Product first = new Book(1, "hamlet", 1, "shakespeare");
        Product fourth = new Book(1, "hamlet", 1, "shakespeare");
        assertEquals(first, fourth);
    }

    @Test
    public void useEqualsBook() {
        Product first = new Book(1, "hamlet", 1, "shakespeare");
        Product fourth = new Book(1, "hamlet", 1, "shakespeare");
        assertEquals(first, fourth);
    }

    @Test
    public void useEqualsSmartphone() {
        Product fourth = new Smartphone(4, "S10", 1, "samsung");
        Product sixth = new Smartphone(0, "0", 0, "samsung");
        assertEquals(sixth, fourth);
    }

//    @Test
//    public void useOver() {
//        Product product = new Product();
//        product.toString();
//    }

    @Test
    public void objectCheck() {
        Product product = new Product();
        boolean expected = false;
        boolean actual = manager.matches(product, "samsung");

        assertEquals(expected, actual);

    }

    @Test
    public void searchMoreThanOne() {
        manager.add(fourth);
        manager.add(sixth);
        manager.add(eighth);
        Product[] expected = {new Smartphone(4, "S10", 1, "samsung"),
                new Smartphone(6, "s20", 1, "samsung"),
                new Smartphone(8, "a90", 1, "samsung")};
        Product[] actual = manager.searchBy("samsung");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByNonExistName() {
        manager.add(fourth);
        manager.add(sixth);
        manager.add(eighth);
        Product[] expected = {};
        Product[] actual = manager.searchBy("ivanov");
        assertArrayEquals(expected, actual);
    }
}
