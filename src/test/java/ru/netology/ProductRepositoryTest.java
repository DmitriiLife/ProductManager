package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductRepositoryTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product first = new Book(1, "hamlet", 100, "shakespeare");
    private Product second = new Book(2, "harry potter", 80, "joan rowling");
    private Product third = new Book(3, "idiot", 75, "dostoevsky");
    private Product fourth = new Smartphone(4, "S10", 550, "samsung");
    private Product fifth = new Smartphone(5, "3310", 100, "nokia");
    private Product sixth = new Smartphone(6, "S20", 600, "samsung");
    private Product seventh = new Book(7, "instruction", 200, "samsung");


    @Test
    public void saveBook() {
        manager.add(third);
        Product[] expected = {new Book(3, "idiot", 75, "dostoevsky")};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void saveSmartphone() {
        manager.add(sixth);
        Product[] expected = {new Smartphone(6, "S20", 600, "samsung")};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        manager.add(third);
        Product[] expected = {new Book(3, "idiot", 75, "dostoevsky")};
        Product[] actual = manager.searchBy("dostoevsky");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByName() {
        manager.add(first);
        Product[] expected = {new Book(1, "hamlet", 100, "shakespeare")};
        Product[] actual = manager.searchBy("hamlet");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByBookName() {
        manager.add(second);
        Product[] expected = {new Book(2, "harry potter", 80, "joan rowling")};
        Product[] actual = manager.searchBy("harry potter");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByManufacturer() {
        manager.add(fifth);
        Product[] expected = {new Smartphone(5, "3310", 100, "nokia")};
        Product[] actual = manager.searchBy("nokia");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySmartphoneName() {
        manager.add(fifth);
        Product[] expected = {new Smartphone(5, "3310", 100, "nokia")};
        Product[] actual = manager.searchBy("3310");
        assertArrayEquals(expected, actual);
    }
}
//    @Test
//    void searchMoreThanOne() {
//        manager.add(seventh);
//        manager.add(sixth);
//        manager.add(fourth);
//        Product[] expected = {new Smartphone(4, "S10", 550, "samsung"),
//                new Smartphone(6, "S20", 600, "samsung"),
//                new Book(7, "a52", 200, "samsung")};
//        Product[] actual = manager.searchBy("samsung");
//        assertArrayEquals(expected, actual);
//    }
//        @Test
//        void removeById () {
//            manager.add(seventh);
//            manager.add(sixth);
//            manager.add(fourth);
//            manager.remove(6);
//            Product[] expected = {new Smartphone(4, "S10", 550, "samsung"),
//                    new Smartphone(7, "a52", 2800, "samsung")};
//            Product[] actual = repository.findAll();
//            assertArrayEquals(expected, actual);
//        }
//
//    @Test
//    public void useEquals() {
//        manager.add(seventh);
//        Product[] expected = {new Smartphone(7, "a52", 2800, "Samsung")};
//        Product[] actual = {new Smartphone(7, "a52", 2800, "Samsung")};
//        assertArrayEquals(expected, actual);
//    }