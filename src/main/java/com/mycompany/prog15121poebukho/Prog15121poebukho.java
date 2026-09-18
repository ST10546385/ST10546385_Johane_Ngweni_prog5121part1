/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog15121poebukho;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Game
 */
public class Prog15121poebukho {

    static Scanner input = new Scanner(System.in);

   public static boolean checkUserName(String username) {
       return username.contains("_") && username.length() <= 5;
   }

   public static boolean checkPasswordComplexity(String password) {
       String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
       return Pattern.matches(regex, password);
   }

   public static boolean checkCellPhoneNumber(String number) {
       String regex = "^\\+27\\d{9}$";
       return Pattern.matches(regex, number);
   }

   public static String registerUser(String username, String password) {
       if (!checkUserName(username)) {
           return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
       }
       if (!checkPasswordComplexity(password)) {
           return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
       }
       return "User has been registered successfully.";
   }
   
   public static boolean loginUser(String username, String password, String storedUsername, String storedPassword) {
       return username.equals(storedUsername) && password.equals(storedPassword);
   }

   public static String returnLoginStatus(boolean status) {
       if (status) {
           return "Welcome! It is great to see you again.";
       } else {
           return "Username or password incorrect, please try again.";
       }
   }

   public static void main(String[] args) {
       String storedUsername = "";
       String storedPassword = "";
       String cellPhone;
       String username;
       
       while (true) {
           System.out.print("Enter Username (must contain '_' and max 5 chars): ");
           username = input.nextLine();
           if (checkUserName(username)) break;
           System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
       }

       String password;
       while (true) {
           System.out.print("Enter Password (8+ chars, 1 capital, 1 number, 1 special char): ");
           password = input.nextLine();
           if (checkPasswordComplexity(password)) break;
           System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
       }

       String registerMessage = registerUser(username, password);
       System.out.println(registerMessage);
       storedUsername = username;
       storedPassword = password;

       while (true) {
           System.out.print("Enter Cell Phone (+27 followed by 9 digits): ");
           cellPhone = input.nextLine();
           if (checkCellPhoneNumber(cellPhone)) {
               System.out.println("Cell phone number successfully added.");
               break;
           }
           System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
       }

       System.out.println("\n--- Login ---");
       while (true) {
           System.out.print("Enter Username: ");
           String loginUser = input.nextLine();
           System.out.print("Enter Password: ");
           String loginPass = input.nextLine();
           boolean status = loginUser(loginUser, loginPass, storedUsername, storedPassword);
           System.out.println(returnLoginStatus(status));
           if (status) break;
       }
   }
}
