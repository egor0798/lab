package com.company;

import java.io.Serializable;

/**
 * Created by User on 26.12.2016.
 */
class Item implements Serializable{
    private String desc; //Short description
    private String name;
    private int number;
    String getDesc() {   return desc;    }
    int getNumber() {    return number;  }
    String getName() { return name;   }
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
    void setDesc(String s ) {   desc = s;    }
    void setNumber(int a) {    number=a;  }
    void setName(String s) { name=s;   }
}



