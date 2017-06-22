package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by egorka on 20.06.17.
 */
public class Server implements notifayer{
    private List<Client_Handler> ll = new LinkedList<>();
    public Server(){
        DBconnection dbcon;
        LinkedHashMap<Integer, Item> stuff = new LinkedHashMap<>();
        try(ServerSocket ss = new ServerSocket(8189)) {
            dbcon = new DBconnection("localhost", 5432, "progdb", "admin", "615243ya", stuff);

            while(true) {
                Socket incoming = ss.accept();
                System.out.println("New client");
                Client_Handler c = new Client_Handler(incoming, dbcon, this);
                ll.add(c);
                Thread t = new Thread(c);
                t.start();
            }
        }
        catch (SQLException ex) {
            System.out.println("Something wrong with database");
        }catch (IOException e){
            System.out.println("Cannot run server.");
        }
    }

    @Override
    public void sendAll(Server_request sr) {
        ll.forEach(el->{
            try {
                el.send(sr);
            }catch (IOException e){
                System.out.println("sendAll");
            }
        });
    }
}
