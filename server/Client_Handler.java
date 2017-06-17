package com.company;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by egorka on 13.06.17.
 */
public class Client_Handler implements Runnable {
    Server_request req_out = new Server_request();
    Client_request req_in = new Client_request();
    Socket s;
    DBconnection db;
    ObjectInputStream in;
    ObjectOutputStream out;
    Client_Handler(Socket incoming, DBconnection d) throws IOException{
        s = incoming;
        db = d;
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());
    }

    public synchronized void run(){
        try {
            if(in.available() > 0)
                System.out.println("eeee");
            handle();
        }catch (IOException|ClassNotFoundException e){
            System.out.println("SHIT !1!!!!!1111");
            e.printStackTrace();
        }

    }

    public void handle() throws IOException, ClassNotFoundException{
        try {
            while(s.isBound()) {
                out.flush();
                req_in = (Client_request) in.readObject();
                req_out = choose_action(req_in);
                out.writeObject(req_out);
                //out.writeUnshared(req_out);
                out.reset();
            }
            }catch (SQLException ee){
                    System.out.println("L - means LOSER");
                    ee.printStackTrace();
            }catch (EOFException|SocketException e11){
                    System.out.println("Client disconnected");
            }
        out.close();
        in.close();
    }

    public  Server_request choose_action(Client_request r) throws SQLException{
        req_out.setErr(false);
        switch (r.getCode()) {
            case 1:
                req_out.setCol(db.load());
                break;
            case 2:
                db.remove(r.getKey());
                req_out.setCol(db.load());
                break;
            case 3:
                db.removeLower(r.getKey());
                req_out.setCol(db.load());
                break;
            case 4:
                db.insert(r.getItem());
                req_out.setCol(db.load());
                break;
            default:
                req_out.setErr(true);
        }
        return req_out;
    }
}
