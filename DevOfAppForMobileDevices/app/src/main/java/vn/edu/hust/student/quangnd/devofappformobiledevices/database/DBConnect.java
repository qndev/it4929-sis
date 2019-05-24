package vn.edu.hust.student.quangnd.devofappformobiledevices.database;


import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Setting up API JDBC to Collection to DB.
 * @author quangnd
 */
public class DBConnect {

    /**
     * Define JDBC driver using Mysql Server
     */
    public static String driver = "com.mysql.jdbc.Driver";

    /**
     * Path to Connect database
     */
    public static String url = "jdbc:mysql://localhost:3306/sis?characterEncoding=UTF-8";

    /**
     * User of database
     */
    public static String user = "root";

    /**
     * Password of root user to connect database
     */
    public static String pass = "Hoamoclan1996@";


    /**
     * Connection
     */

    public static Connection cnn;

    /**
     *
     * @return
     */
    public static boolean openConnection() {
        try {
            if (cnn == null || cnn.isClosed()) {
                Class.forName(driver);
                cnn = DriverManager.getConnection(url, user, pass);
                Log.e("success", "success");
            }
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     */
    public static void close() {
        try {
            if (cnn != null) {
                cnn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param ps
     */
    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    /**
     *
     * @param ps
     * @param rs
     */
    public static void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        close(ps);
    }

    /**
     *
     * @param myConn
     * @param myStmt
     * @param myRs
     * @throws SQLException
     */
    public static void close(Connection myConn, Statement myStmt, ResultSet myRs)
            throws SQLException {

        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {

        }

        if (myConn != null) {
            myConn.close();
        }
    }

    /**
     *
     * @param myStmt
     * @param myRs
     * @throws SQLException
     */
    public static void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

    /**
     *
     * @param myStmt
     * @throws SQLException
     */
    public static void close(Statement myStmt) throws SQLException {
        close(null, myStmt, null);
    }
}
