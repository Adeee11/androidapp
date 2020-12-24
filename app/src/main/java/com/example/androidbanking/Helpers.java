package com.example.androidbanking;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Helpers {
    static void showToast(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * Helper Function to Save Users List FileStorage on Android
     * @param context
     * @param usersList
     */
    static void saveAllUsers(Context context,ArrayList<User> usersList){
        StringBuilder userString = new StringBuilder();
        StringBuilder accountString = new StringBuilder();
        for(User user: usersList) {
            String userText = user.uid + ","+ user.password + ","+ user.name + ","+ user.phone+ "\n";
            userString.append(userText);
            for (Account acc: user.accounts){
                String accText = acc.uid + ","+ acc.accNum
                        + ","+ acc.balance + ","+ acc.type + ","+ acc.lastTransaction+ "\n";
                accountString.append(accText);
            }
        }

        FileManager.writeFile(context,"users.txt", userString.toString());
        FileManager.writeFile(context,"accounts.txt", accountString.toString());
    }

    /**
     * Helper Function to get users android Storage
     * @param context
     * @return ArrayList<User>
     */
    static ArrayList<User> getAllUsers(Context context){
        ArrayList<User> usersList = new ArrayList<User>();
        String users = FileManager.readFile(context,"users.txt");
        String accounts = FileManager.readFile(context,"accounts.txt");
        if(users.equals("")){
            return usersList;
        }
        Log.d("UserList", users);
        Log.d("AccountsList", accounts);
        String[] allAccounts = accounts.split("\n");
        String[] allUsers = users.split("\n");
        for(int i = 0; i < allUsers.length; i++){
            String userText = allUsers[i];
            String[] userDetails = userText.split(",");
            usersList.add(new User(Long.parseLong(userDetails[0]),Integer.parseInt(userDetails[1]),
                    userDetails[2], Long.parseLong(userDetails[3])));

        }

        for(int i = 0; i < allAccounts.length; i++){
            String accountText = allAccounts[i];
            String[] accountDetails = accountText.split(",");
            Account acc = new Account(Long.parseLong(accountDetails[0]), Long.parseLong(accountDetails[1]),
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
