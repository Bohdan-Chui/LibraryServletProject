package model;

import junit.framework.TestCase;

import java.util.Date;

public class CardTest extends TestCase {
    private Date date = new Date();
    public void testGetUserId() {
        Card card = new Card();
        card.setUserId(1);
        assertEquals(1,card.getUserId());
    }

    public void testGetBookId() {
        Card card = new Card();
        card.setBookId(2);
        assertEquals(2,card.getBookId());
    }

    public void testGetPatronymic() {
        Card card = new Card();
        card.setPatronymic("patr");
        assertEquals("patr", card.getPatronymic());
    }

    public void testTestGetName() {
        Card card = new Card();
        card.setName("nam");
        assertEquals("nam",card.getName());
    }

    public void testGetAuthor() {
        Card card = new Card();
        card.setAuthor("auth");
        assertEquals("auth", card.getAuthor());
    }

    public void testGetPlace() {
        Card card = new Card();
        card.setPlace("pla");
        assertEquals("pla", card.getPlace());
    }

    public void testGetStatus() {
        Card card = new Card();
        card.setStatus("stat");
        assertEquals("stat", card.getStatus());
    }

    public void testGetReturnDate() {
        Card card = new Card();
        card.setReturnDate(date);
        assertEquals(date, card.getReturnDate());
    }

    public void testGetFine() {
        Card card = new Card();
        card.setFine(23);
        assertEquals(23, card.getFine());
    }

//    public void testTestToString() {
//        Card card = new Card();
//        assertEquals("Card{" +
//                "userId=" + 0 +
//                ", bookId=" + 2 +
//                ", patronymic='" + "patr" + '\'' +
//                ", name='" + "nam" + '\'' +
//                ", author='" + "auth" + '\'' +
//                ", place='" + "pla" + '\'' +
//                ", status='" + "sta" + '\'' +
//                ", returnDate=" + date +
//                ", fine=" + 23 +
//                '}', card);
//    }
}