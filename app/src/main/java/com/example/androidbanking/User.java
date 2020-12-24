package com.example.androidbanking;

import java.util.ArrayList;

public class User {
    int uid;
    int password;
    String name;
    int phone;

    ArrayList<Account> accounts = new ArrayList<Account>();

    User(int uid, int password, String name, int phone){
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}
