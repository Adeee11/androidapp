package com.example.androidbanking;

import android.content.Context;

import java.util.ArrayList;

public class User {
    static  ArrayList<User> allUsers = new ArrayList<User>();
    long uid;
    int password;
    String name;
    long phone;
    static User  currentUser = null;
    ArrayList<Account> accounts = new ArrayList<Account>();


    /**
     *
     * Find a user by uid in all user list
     * @param uid
     * @return
     */
    static User findUser(long uid){
        User username = null;
        for(User user: User.allUsers){
            if(user.uid == uid){
                username = user;
                break;
            }
        }
        return username;
    }

    /**
     * Deletes an account of logged in user
     * @param cont
     * @param accNum
     * @return
     */
    public String deleteAccount(Context cont, long accNum){
        int index = -1;
        for (int i = 0; i < this.accounts.size(); i++){
            Account acc = this.accounts.get(i);
            if(acc.accNum == accNum) {
                index = i;
                break;
            }
        }
        this.accounts.remove(index);
        Helpers.saveAllUsers(cont,User.allUsers);

        return "Account Deleted "+accNum;
    }

    /**
     * Handles updation of user details
     * @param cont
     * @param name
     * @param phone
     * @return
     */
    public String updateDetails(Context cont, String name, long phone){

        if(String.valueOf(phone).length() != 10){
            return "Please Enter a 10 digit Phone Number";
        }
        this.name = name;
        this.phone = phone;
        Helpers.saveAllUsers(cont,User.allUsers);
        return "Personal Details Updated Successfully";
    }


    /**
     *
     * Handles Sign In of Users
     * @param uid
     * @param password
     * @return
     */
    static boolean signIn (long uid, int password){
        User loggedInUser = null;
        for (User user: allUsers){
            if(user.uid == uid && user.password == password){
                loggedInUser = user;
            }
        }
        if(loggedInUser != null){
            currentUser = loggedInUser;
            return  true;
        }
        return false;
    }


    /**
     * Generate a using access number or uid for user
     * @return
     */
    static long genUid(){
        long uid;
        while (true){
             uid = 1000000 + Math.round(Math.random() * 1000000);
             if(findUser(uid) == null){
                 break;
             }
        }
        return uid;
    }


    /**
     * generated 4 digit password for user
     * @return
     */
    static int genPassword(){
        return (int) (1000 + Math.round(Math.random() * 999));
    }

    /**
     *  Handle sign up of user
     * @param cont
     * @param name
     * @param contact
     * @param type
     * @return
     */
    static User signUp(Context cont, String name, long contact, String type){


        long uid = genUid();
        User  user = new User(uid,genPassword(),name,contact);
        user.accounts.add(Account.createNewAccount(user.uid,type));
        User.allUsers.add(user);
        Helpers.saveAllUsers(cont,User.allUsers);
        return user;
    }

    /**
     * Returns Account numbers of logged in user
     * @return
     */
    static ArrayList<Long> findUserAccountNumbers (){
        ArrayList<Long> names = new ArrayList<Long>();
        for(Account acc: currentUser.accounts){
            names.add(acc.accNum);
        }
        return names;
    }

    /**
     * Constructor
     * @param uid
     * @param password
     * @param name
     * @param phone
     */
    User(long uid, int password, String name, long phone){
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}
