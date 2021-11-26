// Source: https://www.w3schools.com/java/java_arraylist.asp

package com.OnPoint;

import com.OnPoint.DatabaseRelation.Activity;
import com.OnPoint.DatabaseRelation.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Account {
    Profile profile = new Profile();
    ArrayList<Activity> activityList = new ArrayList<>();

    public void showProfile() {
        System.out.println("username    = " + profile.getUsername());
        System.out.println("email   = " + profile.getEmail());
        System.out.println("password    = " + profile.getPassword());
        System.out.println("rating    = " + profile.getRating());
    }

    //Activity CRUD
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
        String timeinput = activityTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeIn = LocalDateTime.parse(timeinput, formatter);
        Timestamp timestamp = Timestamp.valueOf(timeIn);
        activityList.add(new Activity(activityName, timestamp));

        try{
            String sql = "INSERT INTO activities (username, name_activity, start_time) VALUES (? ,?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, this.profile.getUsername());
            statement.setString(2, activityName);
            statement.setTimestamp(3, timestamp);

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
    public void removeActivity(int index){
        if (activityList.size() <= 0){
            System.out.println("You are free from any schedule :)");
        } else {
            activityList.remove(index);
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
}