package com.example.androidbanking;


import android.content.Context;

public class Account {

    long accNum;
    Double balance;
    String type;
    Double lastTransaction;
    long uid;


    /**
     * Constructor
     * @param uid
     * @param accNum
     * @param balance
     * @param type
     * @param lastTransaction
     */
    Account(long uid, long accNum, Double balance, String type, Double lastTransaction){
        this.uid = uid;
        this.accNum = accNum;
        this.balance = balance;
        this.type = type;
        this.lastTransaction = lastTransaction;
    }

    /**
     *
     * Handles Utility Payments
     * @param cont
     * @param utilName
     * @param amount
     * @return
     */
    public String payUtils(Context cont,String utilName,Double amount){
        if(this.balance < amount){
            return "Insufficient Balance in "+ this.accNum;
        }
        this.balance -= amount;
        this.lastTransaction = amount;
        Helpers.saveAllUsers(cont,User.allUsers);
        return "$" +amount + " paid for util" + utilName;
    }


    /**
     * Handle Deposit Of Money
     * @param cont
     * @param amount
     * @return
     */
    public String depositMoney(Context cont, Double amount){
        this.balance += amount;
        this.lastTransaction += amount;
        Helpers.saveAllUsers(cont,User.allUsers);
        return "$"+ amount + " deposited to Account No. "+ this.accNum;
    }

    /**
     * Handles Generation Of New Account Number
     * @return Unique AccNumber
     */
    static long genAccNumber(){
        long uid;
        while (true){
            uid = 1000000 + Math.round(Math.random() * 1000000);
            if(findAccount(uid) == null){
                break;
            }
        }
        return uid;
    }


    /**
     * Returns a New Created Account
     * @param uid
     * @param type
     * @return
     */
    static Account createNewAccount(long uid, String type){
        return new Account(uid,genAccNumber(),0d,type,0d);
    }


    /**
     * Search for an account in all accounts
     *
     * @param accNum
     * @return
     */
    static Account findAccount(long accNum){
        Account acc = null;
        for(User user: User.allUsers){
            for(Account account: user.accounts) {
                if (account.accNum == accNum) {
                    acc = account;
                    break;
                }
            }
        }
        return acc;
    }

    /**
     * Transfer money from source to destination account
     * @param cont
     * @param fromAcc
     * @param destAcc
     * @param amount
     * @return
     */
    static String transferMoney(Context cont, long fromAcc, long destAcc, Double amount){
        Account from = findAccount(fromAcc);
        Account to = findAccount(destAcc);

        if(from == null){
            return "From Account Doesn't Exist";
        }
        if(to == null){
            return "Destination Account Doesn't Exist";
        }
        if(amount == 0){
            return "Please Enter An Amount Greater Than 0";
        }

        if(from.balance < amount){
            return "Insufficient Balance in "+ fromAcc;
        }

        from.balance -= amount;
        to.balance += amount;
        from.lastTransaction = amount;
        to.lastTransaction = amount;
        Helpers.saveAllUsers(cont,User.allUsers);
        return "Amount $"+ amount +" transferred from "+ fromAcc + " to "+ destAcc;

    }



}
