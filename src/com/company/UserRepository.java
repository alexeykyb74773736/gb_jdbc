package com.company;

import com.company.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {



    public static User get(int id){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        User usr = null;

        try{
            conn = MyConnection.getConnection();
            st = conn.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            while (rs.next()){
                usr = new User(rs.getString("NAME"), rs.getInt("AGE"), rs.getInt("PIN"));
                usr.setId(rs.getInt("ID"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usr;
    }

    public static List<User> get(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        List<User> lst = new ArrayList<>();

        try{
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM USERS");
            while (rs.next()){
                User usr = new User(rs.getString("NAME"), rs.getInt("AGE"), rs.getInt("PIN"));
                usr.setId(rs.getInt("ID"));

                lst.add(usr);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lst;
    }

    public static void create(User user){
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MyConnection.getConnection();
            st = conn.prepareStatement("INSERT INTO USERS (NAME, AGE, PIN) VALUES(?,?,?)");
            st.setString(1, user.getName());
            st.setInt(2,user.getAge());
            st.setInt(3,user.getPin());
            st.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                st.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    public static void createPackage(){
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = MyConnection.getConnection();
            st = conn.prepareStatement("INSERT INTO USERS (NAME, AGE, PIN) VALUES(?,?,?)");
            st.setString(1, "Alla");
            st.setInt(2,20);
            st.setInt(3,1111);
            st.addBatch();
            st.setString(1, "Kiril");
            st.setInt(2,25);
            st.setInt(3,11132);
            st.addBatch();

            st.executeBatch();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                st.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    public static void delete(int id){
        Connection conn = null;
        Statement st = null;
        try {
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);
            st = conn.createStatement();
            st.execute("DELETE FROM USERS WHERE id = "+id);

            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                st.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
    public static void update(User user){
        Connection conn = null;
        Statement st = null;
        try {
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute("UPDATE USERS SET NAME='"+user.getName()+"', AGE="+user.getAge()+", PIN="+user.getPin()+" WHERE ID="+user.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                st.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

}
