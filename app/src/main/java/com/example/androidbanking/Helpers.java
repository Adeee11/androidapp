package com.example.androidbanking;


import android.content.Context;

import java.util.ArrayList;

public class Helpers {

    static void saveAllUsers(Context context,ArrayList<User> usersList){
        StringBuilder userString = new StringBuilder();
        StringBuilder accountString = new StringBuilder();
        for(User user: usersList) {
            String userText = String.format("", );
        }
        FileManager.writeFile(context,"users.txt", "");
        FileManager.writeFile(context,"accounts.txt", "");
    }
    static ArrayList<User> getAllUsers(Context context){
        ArrayList<User> usersList = new ArrayList<User>();
        String users = FileManager.readFile(context,"users.txt");
        String accounts = FileManager.readFile(context,"accounts.txt");
        String[] allAccounts = accounts.split("\n");
        String[] allUsers = users.split("\n");
        for(int i = 0; i < allUsers.length; i++){
            String userText = allUsers[i];
            String[] userDetails = userText.split(",");
            usersList.add(new User(Integer.parseInt(userDetails[0]),Integer.parseInt(userDetails[1]),
                    userDetails[2], Integer.parseInt(userDetails[3])));

        }

        for(int i = 0; i < allAccounts.length; i++){
            String accountText = allAccounts[i];
            String[] accountDetails = accountText.split(",");
            Account acc = new Account(Integer.parseInt(accountDetails[0]), Integer.parseInt(accountDetails[1]),
                    Double.parseDouble(accountDetails[2]),accountDetails[3], Double.parseDouble(accountDetails[4]));
            for(User user: usersList){
                if(user.uid == acc.uid){
                    user.accounts.add(acc);
                }
            }
        }
        return usersList;
    }


}
