package db;

import constants.CommonConstants;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyJDBC {
    public static boolean register(String username, String password) {
        try(var conn = DriverManager.getConnection(
                CommonConstants.DB_URL,
                CommonConstants.DB_USERNAME,
                CommonConstants.DB_PASSWORD);
            var stmt = conn.prepareStatement("INSERT INTO `" +
                    CommonConstants.DB_USERS_TABLE_NAME + "` (username, password) VALUES(?, ?)")) {
            if (!checkUser(username)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
                return true;
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return false;
    }

    public static boolean checkUser (String username) {
        try(var conn = DriverManager.getConnection(
                CommonConstants.DB_URL,
                CommonConstants.DB_USERNAME,
                CommonConstants.DB_PASSWORD);
            var stmt = conn.prepareStatement("SELECT * FROM `" +
                    CommonConstants.DB_USERS_TABLE_NAME + "` WHERE username = ?")) {
            stmt.setString(1, username);
            var result = stmt.executeQuery();
            if (!result.isBeforeFirst()) {
                return false;
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return true;
    }

    public static boolean validateLogin(String username, String password) {
        try(var conn = DriverManager.getConnection(
                CommonConstants.DB_URL,
                CommonConstants.DB_USERNAME,
                CommonConstants.DB_PASSWORD);
            var stmt = conn.prepareStatement("SELECT * FROM `" +
                    CommonConstants.DB_USERS_TABLE_NAME + "` WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            var result= stmt.executeQuery();
            if (!result.isBeforeFirst()) {
                return false;
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
}
