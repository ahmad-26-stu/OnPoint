package com.OnPoint.Registration;

import com.OnPoint.DatabaseRelation.Profile;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class userAuth {
    public Profile profile = new Profile();
    public static Scanner scan = new Scanner(System.in);
    public void userLogin(){

        System.out.println("===>Welcome to OnPoint<===\n");
        System.out.println("press R to register");
        System.out.println("Register\n");

        System.out.println("username or email: ");
        String user = scan.nextLine();

        System.out.println("password: ");
        String pass = scan.nextLine();

        if(pass.equals("R") || pass.equals("r") || user.equals("r") || user.equals("R")){
            userRegister();
        }
        else if (!authPass(pass, false) && (!authUsername(user, false) || !authEmail(user, false))) {
            System.out.println("username or password is wrong");
            userLogin();
        }
        else{
            profile.setUsername(user);
        }
    }

    public String userRegister() {
        System.out.println("===>Register<===");
        System.out.println("username: ");
        String user = scan.nextLine();

        System.out.println("email: ");
        String email = scan.nextLine();

        System.out.println("password: ");
        String pass = scan.nextLine();
        if (authPass(pass, true) && (authUsername(user, true) && authEmail(email, true))) {
            setProfile(user, email, pass);
            userLogin();
        }else{
            String reportUsername = !authUsername(user, true) ?
                    "username already taken": "Correct";
            String reportEmail = !authEmail(email, true) ?
                    "maybe email already taken or wrong format ": "Correct";
            String reportPass = !authPass(pass, true) ?
                    "password wrong with the format": "Correct";

            System.out.println(reportUsername);
            System.out.println(reportEmail);
            System.out.println(reportPass);
            userRegister();
        }
        return user;
    }

    //Account Creation and Authentication
    public void setProfile (String username, String email, String password){
        profile.INSERTProfile(username, email, password);
    }

    //login & register verification
    public boolean authEmail(String email, boolean registerMode){
        //setelah dibandingkan dapat dimasukkan datanya ke dalam database
        //pakai set...()

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (registerMode && !profile.checkEmail(email) && matcher.matches()){
            return true;
        } else if (!registerMode && matcher.matches() && profile.checkEmail(email)){
            profile.setEmail(email);
            return true;
        } else {
            return false;
        }

        //2 pembanding dari regex dan database yang diambil dari Profile
        //belum
    }
    public boolean authPass(String pass, boolean registerMode){
        //setelah dibandingkan dapat dimasukkan datanya ke dalam database
        //pakai set...()
        String regex =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        if (registerMode && matcher.matches()){
            return true;
        }
        else if (!registerMode && matcher.matches() && profile.checkPassword(pass)){
            this.profile.setPassword(pass);
            return true;
        } else {
            return false;
        }
        //2 pembanding dari regex dan database yang diambil dari Profile
        //belum
    }
    public boolean authUsername(String username, boolean registerMode){
        //pembanding dari database
        //setelah dibandingkan dapat dimasukkan datanya ke dalam database
        //pakai set...()
        //belum
        if(registerMode && !profile.checkUsername(username)){
            return true;
        } else if ( !registerMode && profile.checkUsername(username)){
            profile.setUsername(username);
            return true;
        }else {
            return false;
        }
    }

}
