package com.example.androidbanking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AccSummaryListAdapter extends BaseAdapter {

    private ArrayList<Account> accounts;
    private LayoutInflater inflater;
    public AccSummaryListAdapter (Context cont, ArrayList<Account> accs){
        this.accounts =accs;
        this.inflater = LayoutInflater.from(cont);
    }
    @Override
    public int getCount() {
        return accounts.size();
    }

    @Override
    public Object getItem(int position) {
        return accounts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        if(view==null){
            view =inflater.inflate(R.layout.account_summer_list_item,null);
            holder.accNum= view.findViewById(R.id.accnumlist);
            holder.balance= view.findViewById(R.id.balancelist);
            holder.lastTransaction = view.findViewById(R.id.lasttranslist);
            holder.type = view.findViewById(R.id.acctypelist);
            view.setTag(holder);
        }
        else
            view.getTag();

        holder.accNum.setText(String.valueOf(accounts.get(i).accNum));
        holder.balance.setText(String.valueOf(accounts.get(i).balance));
        holder.lastTransaction.setText(String.valueOf(accounts.get(i).lastTransaction));
        holder.type.setText(String.valueOf(accounts.get(i).type));



        return view;
    }

    static class ViewHolder{
        TextView accNum, balance, lastTransaction, type;

    }

}
