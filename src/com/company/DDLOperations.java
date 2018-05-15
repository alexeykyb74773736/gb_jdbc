package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DDLOperations {

    public static void createUsersTable(){
        Connection conn = null;
        Statement st = null;
        try{
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute("CREATE TABLE USERS("+
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "NAME CHAR(50)    NOT NULL,"+
                    "AGE  INTEGER  ,"+
                    "PIN  INTEGER(4))");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createAccountTable(){
        Connection conn = null;
        Statement st = null;
        try{
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute("CREATE TABLE ACCOUNT("+
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "USER_ID INTEGER,"+
                    "AMOUNT  REAL  ,"+
                    "FOREIGN KEY(USER_ID) REFERENCES USERS(ID))");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dropUserTable(){
        Connection conn = null;
        Statement st = null;
        try{
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute("DROP TABLE USERS");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dropAccountTable(){
        Connection conn = null;
        Statement st = null;
        try{
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute("DROP TABLE ACCOUNT");
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void showMeta(){
        Connection conn = null;
        try{
            conn = MyConnection.getConnection();
            ResultSet rs = conn.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
            while(rs.next())
            {
                //Print
                System.out.println(rs.getString("TABLE_NAME"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

}
