package com.company;

import java.io.Serializable;

/**
 * Created by egorka on 10.06.17.
 */
public class Client_request implements Serializable{
    private int code;       //code for every command
    private Item item;
    private int key;
    Client_request(){
        code = 0;
        item = null;
        key = 0;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public Integer getKey() {
        return key;
    }
}

/*
Command list with codes:
1 - reload
2 - remove
3 - remove_lower
4 - insert
 */
