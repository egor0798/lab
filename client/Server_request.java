package com.company;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by egorka on 11.06.17.
 */
class Server_request implements Serializable{
    private LinkedHashMap<Integer, Item> col;
    private boolean err;
    Server_request(LinkedHashMap<Integer, Item> c){
        this.col = c;
        err = false;
    }
    Server_request(){
        err = false;
    }

    LinkedHashMap<Integer, Item> getCol() {
        return col;
    }
    void setCol(LinkedHashMap<Integer, Item> col) {
        this.col = col;
    }
    boolean isErr() {
        return err;
    }
    void setErr(boolean err) {
        this.err = err;
    }
}