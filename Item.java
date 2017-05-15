package com.company;

/**
 * Created by User on 26.12.2016.
 */
class Item implements Comparable<Item>{
    private String desc; //Short description
    private String name;
    private int number;
    public void setName(String name) {  this.name = name;   }
    public void setDesc(String desc){   this.desc = desc;  }
    public void setNumber(int number){  this.number = number;  }
    public Item() {}
    public String getDesc() {   return desc;    }
    public int getNumber() {    return number;  }
    public String getName() { return this.name;   }
    @Override
    public int compareTo(Item b){
        return this.getName().compareTo(b.getName());
    }
}


