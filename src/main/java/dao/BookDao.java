package dao;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DBManager;



public class BookDao {
    private static final String SQL_FIND_BOOKS_PUBLISHER_WITH_LIMIT = "SELECT * FROM book WHERE publisher LIKE ? LIMIT ?,? ";
    private static final String SQL_FIND_BOOKS_NAME_WITH_LIMIT = "SELECT * FROM book WHERE name LIKE ? LIMIT ?,? ";
    private static final String SQL__FIND_BOOKS_BY_PUBLISHER= "SELECT * FROM book WHERE publisher LIKE ?";
    private static final String SQL__FIND_BOOKS_BY_NAME= "SELECT * FROM book WHERE name LIKE ?";
    private static final String SQL_EDIT_BOOK = "UPDATE book SET name=?,author=?,publisher=?,count=?,publishedTime=? WHERE id=?";
    private static final String SQL_FIND_BOOK_BY_ID = "SELECT * FROM book WHERE id=?";
    private static final String SQL_INSERT_BOOK = "INSERT INTO book (name, author, publisher,  count, publishedTime) " +
            "VALUES  (?, ?, ?, ?, ?);";
    private static final String SQL__FIND_ALL_BOOKS= "SELECT * FROM book";
    private static final String SQL__FIND_ALL_BOOKS_WITH_LIMIT = "SELECT * FROM book LIMIT ?,?";
    private static final String SQL_CHANGE_COUNT = "UPDATE book SET count=? WHERE id=?";
    private static final String SQL_FIND_ALL_BOOKS = "SELECT * FROM book WHERE count > 0";
    private static final String SQL_DELETE_BOOK_EMAIL = "DELETE FROM book WHERE id=?";
    private static final String SQL_FIND_ALL_BOOKS_WITH_LIMIT_ORDER_NAME = "SELECT * FROM book WHERE count > 0 ORDER BY name LIMIT ?,? ";
    private static final String SQL__FIND_ALL_BOOKS_WITH_LIMIT_COUNT = "SELECT * FROM book WHERE count > 0 LIMIT ?,?";
    private static final String SQL_FIND_ALL_BOOKS_WITH_LIMIT_ORDER_TIME = "SELECT * FROM book WHERE count > 0 ORDER BY publishedTime LIMIT ?,? ";
    private static final String SQL_FIND_ALL_BOOKS_WITH_LIMIT_ORDER_PUBLISHER = "SELECT * FROM book WHERE count > 0 ORDER BY publisher LIMIT ?,? ";
    private static final String SQL_FIND_ALL_BOOKS_WITH_LIMIT_ORDER_AUTHOR = "SELECT * FROM book WHERE count > 0 ORDER BY author LIMIT ?,? ";






    public static List<Book> findBookByPublisherWithLimit(String publisher, int currentPage, int recordPerPage) {
        int start = currentPage * recordPerPage - recordPerPage;

        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL_FIND_BOOKS_PUBLISHER_WITH_LIMIT);
            stmt.setString(1, '%'+publisher+'%');
            stmt.setInt(2,start);
            stmt.setInt(3,recordPerPage);
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public static List<Book> findBookByNameWithLimit(String name, int currentPage, int recordPerPage) {
        int start = currentPage * recordPerPage - recordPerPage;
        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL_FIND_BOOKS_NAME_WITH_LIMIT);
            stmt.setString(1,'%'+name+'%');
            stmt.setInt(2,start);
            stmt.setInt(3,recordPerPage);
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static List<Book> findBookByPublisher(String publisher) {
        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL__FIND_BOOKS_BY_PUBLISHER);
            stmt.setString(1,'%'+publisher+'%');
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static List<Book> findBookByName(String name) {

        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL__FIND_BOOKS_BY_NAME);
            stmt.setString(1,'%'+name+'%');
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static int editBook(Book book){

        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_EDIT_BOOK);

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setInt(4, book.getCount());
            preparedStatement.setDate(5, new java.sql.Date(book.getPublishedTime().getTime()));
            preparedStatement.setInt(6,book.getId());

            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }catch (SQLException e){
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }
    public static Book selectBookById(int id){


        Book book = new Book();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection con = null;
            try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            preparedStatement = con.prepareStatement(SQL_FIND_BOOK_BY_ID);
            preparedStatement.setInt(1,id);

            rs = preparedStatement.executeQuery();
            if (rs.next())
               book = mapper.mapRow(rs);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
            return book;
    }
    public static List<Book> selectBooks() {


        List<Book> list = new ArrayList<Book>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_BOOKS);
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static int addBook(Book book){

        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_BOOK);

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setInt(4, book.getCount());
            preparedStatement.setDate(5, new java.sql.Date(book.getPublishedTime().getTime()));
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }

    public static List<Book> selectBooksWithLimit(int currentPage, int recordPerPage) {
        int start = currentPage * recordPerPage - recordPerPage;

        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL__FIND_ALL_BOOKS_WITH_LIMIT);
            stmt.setInt(1,start);
            stmt.setInt(2,recordPerPage);
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static int deleteBookById(int id){

        Connection connection = null;
        int result = 0;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_BOOK_EMAIL);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }catch (SQLException e){
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }

    public static int changeCount(int bookId, int count){
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_COUNT);

            preparedStatement.setInt(1,count);
            preparedStatement.setInt(2,bookId);
            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }catch (SQLException e){
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }


    public static List<Book> selectAllBooks() {

        List<Book> list = new ArrayList<Book>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_BOOKS);
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static List<Book> selectAllBooksSortedByNameWithLimit(int currentPage, int recordPerPage) {
        int start = currentPage * recordPerPage - recordPerPage;
        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL_FIND_ALL_BOOKS_WITH_LIMIT_ORDER_NAME);
            stmt.setInt(1,start);
            stmt.setInt(2,recordPerPage);
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static List<Book> selectAllBooksSortedByAuthorWithLimit(int currentPage, int recordPerPage) {
        int start = currentPage * recordPerPage - recordPerPage;
        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL_FIND_ALL_BOOKS_WITH_LIMIT_ORDER_AUTHOR);
            stmt.setInt(1,start);
            stmt.setInt(2,recordPerPage);
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static List<Book> selectAllBooksSortedByPublisherWitLimit(int currentPage, int recordPerPage) {
        int start = currentPage * recordPerPage - recordPerPage;
        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL_FIND_ALL_BOOKS_WITH_LIMIT_ORDER_PUBLISHER);
            stmt.setInt(1,start);
            stmt.setInt(2,recordPerPage);
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static List<Book> selectAllBooksSortedByPublishedTimeWithLimit(int currentPage, int recordPerPage) {
        int start = currentPage * recordPerPage - recordPerPage;
        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL_FIND_ALL_BOOKS_WITH_LIMIT_ORDER_TIME);
            stmt.setInt(1,start);
            stmt.setInt(2,recordPerPage);
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
    public static List<Book> selectAllBooksWithLimit(int currentPage, int recordPerPage) {
        int start = currentPage * recordPerPage - recordPerPage;
        List<Book> list = new ArrayList<Book>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            BookMapper mapper = new BookMapper();
            stmt = con.prepareStatement(SQL__FIND_ALL_BOOKS_WITH_LIMIT_COUNT);
            stmt.setInt(1,start);
            stmt.setInt(2,recordPerPage);
            rs = stmt.executeQuery();
            while (rs.next())
                list.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    private static class BookMapper implements EntityMapper {
        @Override
        public Book mapRow(ResultSet rs) {
            try {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setCount(rs.getInt("count"));
                book.setPublishedTime(rs.getDate("publishedTime"));

                return book;
            } catch (Exception e) {
                System.out.println(e);
                throw new IllegalStateException(e);
            }
        }
    }
}