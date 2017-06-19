package com.company;

import java.io.Serializable;

/**
 * Created by User on 26.12.2016.
 */
class Item implements Comparable<Item>, Serializable{
    private String desc; //Short description
    private String name;
    private int number;
    String getDesc() {   return desc;    }
    int getNumber() {    return number;  }
    String getName() { return name;   }
    @Override
    public int compareTo(Item b){
        return this.getName().compareTo(b.getName());
    }
    Item(String n){
        name = n;
        number = 1;
        desc = "-";
    }
    Item(String n, int c){
        name = n;
        number = c;
        desc = "-";
    }
    Item(String n, int c, String d){
        name = n;
        number = c;
        desc = d;
    }
}



