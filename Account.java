<<<<<<< Updated upstream:Account.java
// Source: https://www.w3schools.com/java/java_arraylist.asp

package com.OnPoint;

import com.OnPoint.DatabaseRelation.Activity;
import com.OnPoint.DatabaseRelation.Profile;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Account {
    private final Profile profile = new Profile();
    ArrayList<Activity> activityList = new ArrayList<>();

    //Profile Management
    public Profile getProfile(){
        return this.profile;
    }
    public void showProfile() {
        System.out.println("username   = " + profile.getUsername());
        System.out.println("email      = " + profile.getEmail());
        System.out.println("password   = " + profile.getPassword());
        System.out.println("rating     = " + profile.getRating().getRate());
        System.out.println("total meet = " + profile.getRating().getTotalMeet());
    }

    //Activity CRUD
    public void reloadActivity(Connection connect, String username){
//        List<String> listActivityName = new ArrayList<>();
//        List<Timestamp> listActivityTime = new ArrayList<>();
        try {
            String sql = "SELECT activity_name,start_time FROM activities WHERE issuer = ?";
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            String c1 = "activity_name";
            String c2 = "start_time";
            while (rs.next()){
                System.out.println(rs.getString(c1) + " " + rs.getString(c2));
//                activityList.add(new Activity(rs.getString(c1),rs.getTimestamp(c2)));
//                listActivityName.add(rs.getString(c1));
//                listActivityTime.add(rs.getTimestamp(c2));
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed on reloadActivity");
            except.printStackTrace();
        }
    }
    public void showActivity(){
        if(activityList.size() <= 0) {
            System.out.println("You are free from any schedule :)");
        }else{
            System.out.println("--Appointment--");
            for(int i = 0; i < activityList.size(); i++) {
                System.out.println(activityList.get(i).getTime() + " " + activityList.get(i).getName());
            }
        }
    }
    public void addActivity(String activityName, String activityTime, Connection connect){
        String timeInput = activityTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeIn = LocalDateTime.parse(timeInput, formatter);
        Timestamp timestamp = Timestamp.valueOf(timeIn);
        activityList.add(new Activity(activityName, timestamp));

        try{
            String sql = "INSERT INTO activities (activity_name, start_time, issuer) VALUES (? ,?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, activityName);
            statement.setTimestamp(2, timestamp);
            statement.setString(3, this.profile.getUsername());
            int rows = statement.executeUpdate();
            if (rows>0){
                System.out.println("Activity Added");
            }
        }
        catch (SQLException except) {
            System.out.println("Connection Failed");
            except.printStackTrace();
        }
    }
    public void removeActivity(String activityName, String name, Connection connect){
        try {
            String sql = "DELETE FROM activities WHERE activity_name = ? AND issuer = ?";
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, activityName);
            st.setString(2, name);
            int row = st.executeUpdate();
            if (row>0){
                System.out.println("Activity Have Been Removed!");
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed removeActivity");
            except.printStackTrace();
        }
    }
    public void editActivity(int index, String activityName, String activityTime){
//        if (activityList.size() <= 0){
//            System.out.println("You are free from any schedule :)");
//        }else {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            LocalDateTime timeIn = LocalDateTime.parse(activityTime, formatter);
//            activityList.set(index, new Activity(activityName, timeIn));
//        }
    }
    public Activity getActivity(int index){
        if (activityList.size() <= 0){
            System.out.println("You are free from any schedule :)");
            return null;
        }else {
            return activityList.get(index);
        }
    }

    //Friends CRUD
    public boolean approvalFriend(){
        profile.Friends();
        return true;
    }
    public void friends() {
        profile.Friends();
    }

    //pengecekan konfirmasi dari friend
    // public boolean approvalFriend(){
    //     String cancel = scan.nextLine();

    //     int confirm = 0;         //jumlah konfirmasi
    //     int i = 0;               //indeks
    //     boolean check = false;    //hasil boolean
    //     ArrayList<Boolean> friends = new ArrayList<Boolean>(profile.getConfirm());//membuat arraylist untuk boolean confirm dari database
    //     // looping sampai kondisi yang ditentukan
    //     while(i >= 0){
    //         if(i >= friends.size()){    //apabila sama sizenya dibikin jadi 0 lagi
    //             i = 0;
    //         }if (friends.get(i)) {      //apabila true pada arraylist boolean
    //             confirm += 1;
    //         }if (confirm >= (friends.size() * 0.7)){    //minimal jumlah confirm adalah anggota * 70%
    //             System.out.println("you can change activity now");
    //         }if(confirm == friends.size()){             //apabila semua anggota sudah konfirmasi maka keluar
    //             friends = null;
    //             confirm = 0;
    //             check = true;
    //             break;
    //         }if(cancel == "c" || cancel == "C"){        //tidak jadi merubah
    //             friends = null;
    //             confirm = 0;
    //             break;
    //         }

    //         i++;
    //     }
    //     return true;
    // }

//     public String getCurrentTime(){
//         LocalDateTime in = LocalDateTime.now();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//         System.out.println(in.format(formatter));

// //         LocalTime obj = LocalTime.now();
// //         DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
// //         String result = obj.format(format);
// //         System.out.println(result);

// //         LocalDate date = LocalDate.now();
// //         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
// //         String text = date.format(formatter);
// //         LocalDate parsedDate = LocalDate.parse(text, formatter);
// //         System.out.println(parsedDate);

//         return result;
//     }
=======
// Source: https://www.w3schools.com/java/java_arraylist.asp

package com.OnPoint;

import com.OnPoint.DatabaseRelation.Activity;
import com.OnPoint.DatabaseRelation.Profile;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Account {
    private final Profile profile = new Profile();
    ArrayList<Activity> activityList = new ArrayList<>();
    ArrayList<People> friends = new ArrayList<>();

    //    Profile Management
    public Profile getProfile(){
        return this.profile;
    }
    public void showProfile() {
        System.out.println("username   = " + profile.getUsername());
        System.out.println("email      = " + profile.getEmail());
        System.out.println("password   = " + profile.getPassword());
        System.out.println("rating     = " + profile.getRating());
        System.out.println("total meet = " + profile.rate.getTotalMeet());
    }

    //Activity CRUD
    public void reloadActivity(Connection connect, String username){
        try {
            String sql = "SELECT name_activity,start_time FROM activities WHERE username = ?";
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                String activity = rs.getString("name_activity");
                Timestamp time  = rs.getTimestamp("start_time");
                activityList.add(new Activity(activity,time));
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed on reloadActivity");
            except.printStackTrace();
        }
    }
    public void showActivity(){
        if(activityList.size() <= 0) {
            System.out.println("You are free from any schedule :)");
        }else{
            System.out.println("--Appointment--");
            for (Activity activity : activityList) {
                System.out.println(activity.getName() + " " + activity.getTime());
            }
        }
    }
    public void addActivity(String activityName, String activityTime){
        String timeInput = activityTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeIn = LocalDateTime.parse(timeInput, formatter);
        Timestamp timestamp = Timestamp.valueOf(timeIn);
        activityList.add(new Activity(activityName, timestamp));
    }
    public void removeActivity(int index){
        activityList.remove(index-1);
    }
    public void editActivity(int index, String activityName, String activityTime){
        if (activityList.size() <= 0){
            System.out.println("You are free from any schedule :)");
        }else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Timestamp timeIn = Timestamp.valueOf(LocalDateTime.parse(activityTime, formatter));
            activityList.set(index-1, new Activity(activityName, timeIn));
        }
    }
    public void uploadActivity(Connection connect, String issuer){
        try {
            String sqlDel = "DELETE FROM activities WHERE username = ?";
            PreparedStatement stDel = connect.prepareStatement(sqlDel);
            stDel.setString(1, issuer);
            int row = stDel.executeUpdate();
            String sqlIn = "INSERT INTO activities (name_activity, start_time, username) VALUES (?,?,?)";
            PreparedStatement stIn = connect.prepareStatement(sqlIn);
            for (Activity act : activityList){
                stIn.setString(1, act.getName());
                stIn.setTimestamp(2, act.getTime());
                stIn.setString(3, issuer);
            }
            row = stIn.executeUpdate();
            if (row>0) {
                System.out.println("Database Successfully Uploaded");
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed on uploadActivity");
            except.printStackTrace();
        }
    }

    //Friends CRUD
    public void reloadFriends(Connection connect, String username) {
        try {
            String sql = "SELECT profile.username, profile.email, profile.password, profile.rating FROM friends INNER JOIN profile ON friends.username = ? AND friends.friend = profile.username";
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, profile.getUsername());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String nameF = rs.getString("username");
                String emailF = rs.getString("email");
                String passF = rs.getString("password");
                double rateF = rs.getDouble("rating");
                friends.add(new People(nameF, emailF, passF, rateF));
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed");
            except.printStackTrace();
        }
    }
    public void showFriends(Connection connect){
        for(People friend : friends){
            System.out.println(friend.getUsername());
        }
    }
    public boolean findFriends(Connection connect, String fr) {
        String nameF = null;
        String emailF= null;
        String passF= null;
        double rateF = 0.0;
        boolean friend_found = false;
        try {
            String sql = "SELECT * from profile where username = ?";
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, fr);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                nameF = rs.getString("username");
                emailF = rs.getString("email");
                passF = rs.getString("password");
                rateF = rs.getDouble("rating");
                friend_found = true;
            }
            if(friend_found){
                System.out.println("Your friend? " + nameF);
                friends.add(new People(nameF, emailF, passF, rateF));
            }else{
                System.out.println("Can't find your friend");
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed");
            except.printStackTrace();
        }
        return friend_found;
    }
    public void addFriend(Connection connect) {
        try {
            String sql = "INSERT INTO friends VALUES(?, ?)";
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, profile.getUsername());
            st.setString(2, friends.get((friends.size()-1)).getUsername());

            int rows = st.executeUpdate();
            if (rows > 0) {
                System.out.println("you add "+ friends.get((friends.size()-1)).getUsername() + " as friend");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void confFriends(Connection connect, String addornot){
        switch (addornot){
            case "y":
                System.out.println("add friend");
                addFriend(connect);
                break;
            case "n":
                System.out.println("erased friend");
                friends.remove((friends.size()-1));
                break;
        }
    }

    //pengecekan konfirmasi dari friend
    // public boolean approvalFriend(){
    //     String cancel = scan.nextLine();

    //     int confirm = 0;         //jumlah konfirmasi
    //     int i = 0;               //indeks
    //     boolean check = false;    //hasil boolean
    //     ArrayList<Boolean> friends = new ArrayList<Boolean>(profile.getConfirm());//membuat arraylist untuk boolean confirm dari database
    //     // looping sampai kondisi yang ditentukan
    //     while(i >= 0){
    //         if(i >= friends.size()){    //apabila sama sizenya dibikin jadi 0 lagi
    //             i = 0;
    //         }if (friends.get(i)) {      //apabila true pada arraylist boolean
    //             confirm += 1;
    //         }if (confirm >= (friends.size() * 0.7)){    //minimal jumlah confirm adalah anggota * 70%
    //             System.out.println("you can change activity now");
    //         }if(confirm == friends.size()){             //apabila semua anggota sudah konfirmasi maka keluar
    //             friends = null;
    //             confirm = 0;
    //             check = true;
    //             break;
    //         }if(cancel == "c" || cancel == "C"){        //tidak jadi merubah
    //             friends = null;
    //             confirm = 0;
    //             break;
    //         }

    //         i++;
    //     }
    //     return true;
    // }

//     public String getCurrentTime(){
//         LocalDateTime in = LocalDateTime.now();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//         System.out.println(in.format(formatter));

// //         LocalTime obj = LocalTime.now();
// //         DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
// //         String result = obj.format(format);
// //         System.out.println(result);

// //         LocalDate date = LocalDate.now();
// //         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
// //         String text = date.format(formatter);
// //         LocalDate parsedDate = LocalDate.parse(text, formatter);
// //         System.out.println(parsedDate);

//         return result;
//     }
>>>>>>> Stashed changes:src/main/java/com/OnPoint/Account.java
}