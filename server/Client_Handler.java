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
    private Server_request req_out = new Server_request();
    private Client_request req_in = new Client_request();
    private Socket s;
    private DBconnection db;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private notifayer ntf;
    Client_Handler(Socket incoming, DBconnection d, notifayer ntf) throws IOException{
        s = incoming;
        db = d;
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());
        this.ntf = ntf;
    }


    public synchronized void run(){
        handle();
    }

    private void handle() {
        try {
            while (s.isBound()) {
                req_in = (Client_request) in.readObject();
                req_out = choose_action(req_in);
                ntf.sendAll(req_out);
            }
            s.close();
            out.close();
            in.close();
        } catch (SQLException ee) {
            System.out.println("Something wrong with database");
        } catch (EOFException | SocketException e11) {
            System.out.println("Client disconnected");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Object doesn't suite client-server exchange protocol");
        }
    }

    private Server_request choose_action(Client_request r) throws SQLException{
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
            case 5:
                db.update(r.getItem(),r.getKey());
                req_out.setCol(db.load());
                break;
            default:
                req_out.setErr(true);
        }
        return req_out;
    }

    public void send(Server_request sr) throws IOException{
        out.writeObject(sr);
        out.reset();
    }
}
