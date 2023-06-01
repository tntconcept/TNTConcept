package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Book;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BookSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDAO_IT extends IntegrationTest {
    final BookDAO bookDAO;

    public BookDAO_IT() {
        bookDAO = (BookDAO) SpringUtils.getSpringBean("daoBook");
    }

    @Test
    public void loadByIdShouldLoadBook() {
        final int bookId = 1;

        final Book book = bookDAO.loadById(bookId);

        assertEquals("Test", book.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullBook() {
        final int bookId = Integer.MAX_VALUE;

        final Book book = bookDAO.loadById(bookId);

        assertNull(book);
    }

    @Test
    public void getByIdShouldGetBook() {
        final int bookId = 1;

        final Book book = bookDAO.getById(bookId);

        assertEquals("Test", book.getName());
    }

    @Test
    public void getByIdShouldGetNullBook() {
        final int bookId = Integer.MAX_VALUE;

        final Book book = bookDAO.getById(bookId);

        assertNull(book);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultBook() {
        Book book = createBook();
        bookDAO.insert(book);

        List<Book> books = bookDAO.search(new SortCriteria());

        assert(books.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedBook() {
        Book book = createBook();
        bookDAO.insert(book);

        BookSearch bookSearch = new BookSearch();
        bookSearch.setName(book.getName());
        List<Book> books = bookDAO.search(bookSearch, new SortCriteria());

        assert(books.size() == 1);
    }

    @Test
    public void updateShouldChangeBookName() {
        Book bookToUpdate = bookDAO.getById(1);
        bookToUpdate.setName("Update");

        bookDAO.update(bookToUpdate);

        Book updatedBook = bookDAO.getById(1);
        assertEquals("Update", updatedBook.getName());
    }

    @Test
    public void deleteShouldRemoveBook() {
        Book bookToDelete = bookDAO.getById(1);

        bookDAO.delete(bookToDelete);

        assertNull(bookDAO.getById(1));
    }

    private Book createBook() {
        Book book = new Book();
        book.setName("Book");
        book.setAuthor("");
        book.setISBN("");
        book.setURL("");
        book.setPrice(BigDecimal.ZERO);
        book.setOwnerId(1);
        book.setDepartmentId(1);

        return book;
    }
}