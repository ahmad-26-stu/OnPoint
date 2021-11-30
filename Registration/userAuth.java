package com.OnPoint.Registration;

import com.OnPoint.DatabaseRelation.Profile;

import java.util.Scanner;

public class userAuth {
    public static Scanner scan = new Scanner(System.in);
    public static Authenticate auth = new Authenticate();
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
        else if (!auth.authPass(pass, false) && (!auth.authUsername(user, false) || !auth.authEmail(user, false))) {

            System.out.println("username or password is wrong");
            userLogin();
        }



    }

    public void userRegister() {
        System.out.println("===>Register<===");
        System.out.println("username: ");
        String user = scan.nextLine();

        System.out.println("email: ");
        String email = scan.nextLine();

        System.out.println("password: ");
        String pass = scan.nextLine();
        System.out.println(auth.authPass(pass, true));
        System.out.println(auth.authUsername(user, true));
        System.out.println(auth.authEmail(email, true));
        if (auth.authPass(pass, true) && (auth.authUsername(user, true) && auth.authEmail(email, true))) {
            auth.setProfile(user, email, pass);
            userLogin();
        }else{
            String reportUsername = !auth.authUsername(user, true) ?
                    "username already taken": "Correct";
            String reportEmail = !auth.authEmail(email, true) ?
                    "maybe email already taken or wrong format ": "Correct";
            String reportPass = !auth.authPass(pass, true) ?
                    "password wrong with the format": "Correct";

            System.out.println(reportUsername);
            System.out.println(reportEmail);
            System.out.println(reportPass);
            userRegister();

        }

    }
}
