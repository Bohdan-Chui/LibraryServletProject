package dao;

import database.DBManager;
import model.Book;
import model.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDao {
    private static final String SQL_CONFIRM_CARD = "UPDATE card SET fine=DATEDIFF(CURDATE(),returnDate)";
    private static final String SQL_DELETE_CARD_ID = "DELETE FROM card WHERE id=?";
    private static final String SQL_CONFIRM_CARD_BY_ID = "UPDATE card SET status=? WHERE id=?";
    private static final String SQL_ORDER_BOOK = "INSERT INTO card(bookId,UserId,place, returnDate)" +
            "VALUES (?,?,?,?)";
    private static final String SQL_SELECT_CARDS =
            "SELECT  user.patronymic," +
                    "book.name,book.author," +
                    "card.status,card.bookId, card.userId,card.id,card.place,card.returnDate, card.fine" +
                    " FROM card" +
                    " JOIN book ON book.id = card.bookId" +
                    " JOIN user ON user.id = card.userId" +
                    " WHERE card.status = ?";
    private static final String SQL_SELECT_CARDS_BY_ID =
            "SELECT  user.patronymic," +
                    " book.name,book.author," +
                    "card.bookId, card.userId,card.id,card.place,card.status,card.returnDate, card.fine" +
                    " FROM card" +
                    " JOIN book ON book.id = card.bookId" +
                    " JOIN user ON user.id = card.userId" +
                    " WHERE card.userId=?";


    public static int fineCounter(){

        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CONFIRM_CARD);
            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }catch (SQLException e){
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }


    public static int deleteCard(int id){
        Connection connection = null;
        int result = 0;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_CARD_ID);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }catch (SQLException e){
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }
    public static int confirmCardById(int id){
        fineCounter();

        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CONFIRM_CARD_BY_ID);

            preparedStatement.setString(1, "confirmed");
            preparedStatement.setInt(2,id);


            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }catch (SQLException e){
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }
    public static int orderBook(int bookId, int userId, String place, java.util.Date returnDate){

        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_ORDER_BOOK);

            preparedStatement.setString(1,Integer.toString(bookId));
            preparedStatement.setString(2,Integer.toString(userId));
            preparedStatement.setString(3,place);
            preparedStatement.setDate(4, new java.sql.Date(returnDate.getTime()));
            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }catch (SQLException e){
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }
    public static List<Card> selectAllNotConfirmedCards() {
        fineCounter();

        List<Card> list = new ArrayList<Card>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            CardMapper mapper = new CardMapper();
            stmt = con.prepareStatement(SQL_SELECT_CARDS);

            stmt.setString(1,"not confirmed");
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

    public static List<Card> selectAllUsersCards(int userId) {
        fineCounter();
        List<Card> list = new ArrayList<Card>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getConnection();
            CardMapper mapper = new CardMapper();
            stmt = con.prepareStatement(SQL_SELECT_CARDS_BY_ID);

            stmt.setInt(1,userId);
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
    private static class CardMapper implements EntityMapper{
        @Override
        public Card mapRow(ResultSet rs) {
            try{
                Card card = new Card();

                card.setId(rs.getInt("id"));
                card.setBookId(rs.getInt("bookId"));
                card.setUserId(rs.getInt("userId"));
                card.setPatronymic(rs.getString("patronymic"));
                card.setName(rs.getString("name"));
                card.setAuthor(rs.getString("author"));;
                card.setPlace(rs.getString("place"));
                card.setStatus(rs.getString("status"));
                card.setReturnDate(rs.getDate("returnDate"));
                card.setFine(rs.getInt("fine"));

                return card;
            }catch (Exception e) {
                throw new IllegalStateException(e);
            }
    }
    }
}


