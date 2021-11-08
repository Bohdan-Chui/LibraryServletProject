package model;

import junit.framework.TestCase;

import java.util.Date;

public class BookTest extends TestCase {

    public void testTest() {
        Book book1 = new Book();
        Date date = new Date();
        Book book2 = new Book("name", "author","publisher",2,date);
        book2.setId(1);

        book1.setId(1);
        book1.setName("name");
        book1.setCount(2);
        book1.setPublisher("publisher");
        book1.setPublishedTime(date);
        book1.setAuthor("author");
        assertEquals(1,book1.getId());
        assertEquals(2,book1.getCount());
        assertEquals("publisher", book1.getPublisher());
        assertEquals("name", book1.getName());
        assertEquals("author", book1.getAuthor());
        assertEquals(book1.getPublishedTime(), book2.getPublishedTime());
        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
        assertEquals(book1.toString(), book2.toString());
        assertTrue(book1.equals(book2));
    }
}