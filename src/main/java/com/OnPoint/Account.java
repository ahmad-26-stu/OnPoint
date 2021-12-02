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
        try {
            String sql = "SELECT activity_name,start_time FROM activities WHERE issuer = ?";
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            String c1 = "activity_name";
            String c2 = "start_time";
            while (rs.next()){
                String kolom1 = rs.getString(c1);
                Timestamp time  = rs.getTimestamp(c2);
                activityList.add(new Activity(kolom1,time));
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
                System.out.println(activityList.get(i).getName() + " " + activityList.get(i).getTime());
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
            String sql = "DELETE FROM activities WHERE issuer = ?";
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, issuer);
            int row = st.executeUpdate();
            if (row>0){
                for (int i=0; i<activityList.size(); i++){
                    sql = "INSERT INTO activities (activity_name, start_time, issuer) VALUES (?,?,?)";
                    st = connect.prepareStatement(sql);
                    st.setString(1, activityList.get(i).getName());
                    st.setTimestamp(2, activityList.get(i).getTime());
                    st.setString(3, issuer);
                    row = st.executeUpdate();
                    if (row>0){
                        System.out.println("Database Successfully Uploaded");
                    }
                }
            }
        } catch (SQLException except) {
            System.out.println("Connection Failed on uploadActivity");
            except.printStackTrace();
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
}