package dao;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.User;
import database.DBManager;

public class UserDao {
    private static final String SQL__FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?";
    private static final String SQL_CHANGE_COUNT = "UPDATE user SET blocked=? WHERE email=?";
    private static final String SQL_DELETE_USER_EMAIL = "DELETE FROM user WHERE id=?";
    private static final String SQL_SELECT_ROLE = "SELECT * FROM user WHERE role=?";
    private static final String SQL_SELECT_USER_BLOCK_BY_LOGIN = "SELECT blocked FROM user WHERE email=?";
    private static final String SQL_INSERT_USER = "INSERT INTO User (patronymic, firstname, secondname,  email, password, role) " +
            "VALUES  (?, ?, ?, ?, ?, ?);";
    private static  final String SQL_SELECT_USER_BY_LOGIN = "SELECT patronymic, firstname, secondname,  email," +
            " password, role FROM user WHERE email=?";





    public static int changeBlockStatus(String email, boolean block){
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_COUNT);

            preparedStatement.setString(2,email);
            preparedStatement.setBoolean(1,block);
            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }catch (SQLException e){
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }
    public static int deleteUserById(int id){

        Connection connection = null;
        int result = 0;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_EMAIL);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }catch (SQLException e){
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }

    public static List<User> getUserListFromRole(String role){

        List<User> list = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DBManager.getConnection();
            UserMapper mapper = new UserMapper();
            pstmnt = connection.prepareStatement(SQL_SELECT_ROLE);
            pstmnt.setString(1, role);
            ResultSet rs = pstmnt.executeQuery();
            while(rs.next()){
                list.add(mapper.mapRow(rs));
            }
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
        }
        return list;
    }

    public static boolean isBlocked(String email){


        Connection connection = null;
        PreparedStatement pstmnt = null;
        boolean isblocked = false;
        try {
            connection = DBManager.getConnection();
            pstmnt = connection.prepareStatement(SQL_SELECT_USER_BLOCK_BY_LOGIN);
            pstmnt.setString(1, email);
            ResultSet rs = pstmnt.executeQuery();
            if(rs.next()){
                isblocked = rs.getBoolean("blocked");
            }
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
        }
        return isblocked;
    }

    public static int registerUser(User user){

        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getSecondname());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            DBManager.printSQLException(e);
        }
        return result;
    }

    public static boolean isRegistered(String email){
               Connection connection = null;
        PreparedStatement pstmnt = null;
        boolean registered = false;
        try {
            connection = DBManager.getConnection();
            pstmnt = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            pstmnt.setString(1, email);
            ResultSet rs = pstmnt.executeQuery();
            if(rs.next()){
                registered = true;
            }
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
        }
        return registered;
    }

    public static User findUserByEmail(String email) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        System.out.println("find1");
        try {
            con = DBManager.getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            System.out.println("find2");
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setPatronymic(rs.getString("patronymic"));
                user.setFirstname(rs.getString("firstname"));
                user.setSecondname(rs.getString("secondname"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
                user.setBlocked(rs.getBoolean("blocked"));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}