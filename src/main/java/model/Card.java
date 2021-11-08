package model;

import java.util.Date;

public class Card extends Entity{
    private int userId;
    private int bookId;
    private String patronymic;
    private String name;
    private String author;
    private String place;
    private String status;
    private Date returnDate;
    private int fine;

    public Card(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        if(fine >0 )
            this.fine = fine;
        else fine =0;
    }

    @Override
    public String toString() {
        return "Card{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                ", patronymic='" + patronymic + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", place='" + place + '\'' +
                ", status='" + status + '\'' +
                ", returnDate=" + returnDate +
                ", fine=" + fine +
                '}';
    }
}
