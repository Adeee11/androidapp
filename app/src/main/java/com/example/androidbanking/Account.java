package com.example.androidbanking;

import java.math.BigInteger;

public class Account {
    int accNum;
    Double balance;
    String type;
    Double lastTransaction;
    int uid;

    Account(int uid, int accNum, Double balance, String type, Double lastTransaction){
        this.uid = uid;
        this.accNum = accNum;
        this.balance = balance;
        this.type = type;
        this.lastTransaction = lastTransaction;
    }
}
