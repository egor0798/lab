package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedHashMap;

/**
 * Created by egorka on 13.06.17.
 */
public class Server_Main {
    public static void main(String[] args){
        DBconnection dbcon;
        LinkedHashMap<Integer, Item> stuff = new LinkedHashMap<>();
        try {
            dbcon = new DBconnection("localhost", 5432, "progdb", "admin", "615243ya", stuff);
            ServerSocket ss = new ServerSocket(8189);
            while(true) {
                Socket incoming = ss.accept();
                System.out.println("New client");
                Client_Handler c = new Client_Handler(incoming, dbcon);
                Thread t = new Thread(c);
                t.start(); // Не совсем понятно что делать с соединением, потому что при остановке сервера оно не закроется
                /*if(Thread.activeCount()  < 2)
                    dbcon.connection.close();*/
            }
        }
        catch (SQLException ex) {
            System.out.println("Something wrong with database");
        }catch (IOException e){
            System.out.println("Cannot run server.");
        }
    }
}
