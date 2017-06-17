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
    public static void main(String[] args) throws IOException,SQLException {
        DBconnection dbcon = null;
        LinkedHashMap<Integer, Item> stuff = new LinkedHashMap<>();
        try {
            dbcon = new DBconnection("localhost", 5432, "progdb", "admin", "615243ya", stuff);
            ServerSocket ss = new ServerSocket(8189);
            while(true) {
                Socket incoming = ss.accept();
                //incoming.isConnected();
                System.out.println("Here's our new client");
                Client_Handler c = new Client_Handler(incoming, dbcon);
                Thread t = new Thread(c);
                t.start();
                if(Thread.activeCount()  < 2)
                    dbcon.connection.close();
            }
        }
        catch (SQLException ex) {
            System.out.println("Something wrong with database");
        }
    }
}
