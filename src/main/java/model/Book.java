package model;


import java.util.Date;
import java.util.Objects;


public class Book extends Entity{
    private String name;
    private String author;
    private String publisher;
    private int count;
    private Date publishedTime;

    public Book(String name, String author, String publisher, int count, Date publishedTime) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.count = count;
        this.publishedTime = publishedTime;
    }

    public Book() {
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name) && author.equals(book.author) &&
                publisher.equals(book.publisher) && publishedTime.equals(book.publishedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, publisher, publishedTime);
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", count=" + count +
                ", publishedTime=" + publishedTime +
                '}';
    }
}
