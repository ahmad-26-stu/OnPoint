package com.OnPoint.Registration;

import com.OnPoint.DatabaseRelation.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Authenticate {

    private Profile profile = new Profile();

    //Account Creation and Authentication
    //register
    public void setProfile (String username, String email, String password){
//        this.profile = new Profile(username, email, password);
        profile.INSERTProfile(username, email, password);
    }

    //login & register verification
    public boolean authEmail(String email, boolean registerMode){
        //setelah dibandingkan dapat dimasukkan datanya ke dalam database
        //pakai set...()

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (registerMode && !profile.authEmail(email) && matcher.matches()){
            return true;
        } else if (!registerMode && matcher.matches() && profile.authEmail(email)){
            profile.setEmail(email);
            return true;
        } else {
            return false;
        }

        //2 pembanding dari regex dan database yang diambil dari Profile
        //belum
    }
    //login & register verification
    public boolean authPass(String pass, boolean registerMode){
        //setelah dibandingkan dapat dimasukkan datanya ke dalam database
        //pakai set...()
        String regex =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        if (registerMode && matcher.matches()){
            return true;
        } else if (!registerMode && matcher.matches() && profile.authpassword(pass)){
            profile.setPassword(pass);
            return true;
        } else {
            return false;
        }
        //2 pembanding dari regex dan database yang diambil dari Profile
        //belum
    }
    //login & register verification
    public boolean authUsername(String username, boolean registerMode){
        //pembanding dari database
        //setelah dibandingkan dapat dimasukkan datanya ke dalam database
        //pakai set...()
        //belum
        if(registerMode && !profile.authusername(username)){
            return true;
        } else if ( !registerMode && profile.authusername(username)){
            profile.setUsername(username);
            return true;
        }else {
            return false;
        }
    }

}
