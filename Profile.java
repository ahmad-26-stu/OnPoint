package com.OnPoint.DatabaseRelation;

import com.OnPoint.Rating;

import java.sql.*;
import java.util.ArrayList;

public class Profile{
    private String username;
    String email;
    String password;
    Rating rating;
    ArrayList<Boolean> confriend = new ArrayList<>();

    Appsql appsql = new Appsql();
    Connection conndb = appsql.connect();

    public boolean checkEmail(String email){
        boolean email_exist = false;
        try {
            String sql = "SELECT email from Profile WHERE email = ?";
            PreparedStatement st = conndb.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                email_exist = true;
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed");
            except.printStackTrace();
        }
        setEmail(email);
        return email_exist;
    }
    public boolean checkUsername(String username){
        boolean username_exist = false;
        try {
            String sql = "SELECT username from Profile WHERE username = ?";
            PreparedStatement st = conndb.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                username_exist = true;
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed");
            except.printStackTrace();
        }
        setUsername(username);
        return username_exist;
    }
    public boolean checkPassword(String password){
        boolean password_exist = false;
        try {
            String sql = "SELECT password from Profile WHERE password = md5(?)";
            PreparedStatement st = conndb.prepareStatement(sql);
            st.setString(1, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                password_exist = true;
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed");
            except.printStackTrace();
        }
        setPassword(password);
        return password_exist;
    }
    public void INSERTProfile(String username, String email, String password) {
        setRating(new Rating());
        setUsername(username);
        setEmail(email);
        setPassword(password);
        try {
            String sql = "INSERT INTO profile VALUES (nextval('profile_no_seq'::regclass) ,?, ?, md5(?), ?)";
            PreparedStatement statement = conndb.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setDouble(4, getRating().getRate());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Profile Added");
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed");
            except.printStackTrace();
        }

    }

    //database
    public void Friends(){
        System.out.println("adfasdf");
        //ngambil friend di database dimana friend tersebut memiliki username-nya si user
        //pake select username, friend from Profile where friend = "--username--  kemudian masukin ke string/list
        //if(friend != null){ print friends...}
        //apabila temannya kosong, else {System.out.println("sorry, you don't have any friends);}
        //add friend atau remove friend
    }
    public ArrayList<Boolean> getConfirm(){
        //friends confirmation boolean from database
        //save the boolean to variable
        //pengecekan database di profile
        //select confirmation from profile where = "--username--"
        //--username-- adalah user yang ingin merubah activity
        //username dari getName()
        //hasilnya diletakkan di String/list
        return confriend;
    }
    public void removeConfirm(){
        //pakai insert into pada database untuk confirm saja
        //seluruhnya jadi false
        //mencari berdasarkan username yang ingin merubah activity
        //username dari getName()
    }
    public void setRating(Rating rating) {
        this.rating = rating;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Rating getRating() {
        return this.rating;
    }
    public String getUsername() {
        return this.username;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
}