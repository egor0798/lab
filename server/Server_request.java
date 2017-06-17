package com.company;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by egorka on 11.06.17.
 */
public class Server_request implements Serializable{
    public LinkedHashMap<Integer, Item> col;
    public boolean err;
    Server_request(LinkedHashMap<Integer, Item> c){
        this.col = c;
        err = false;
    }
    Server_request(){
        err = false;
    }

    public LinkedHashMap<Integer, Item> getCol() {
        return col;
    }

    public void setCol(LinkedHashMap<Integer, Item> col) {
        this.col = col;
    }

    public boolean isErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }
}
