package com.company;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by egorka on 12.06.17.
 */
public class Client_connection {
    InetAddress a;
   // Client_request r;
    Socket s;
    ObjectInputStream in;
    ObjectOutputStream out;
    Client_connection(){
        try {
            a = InetAddress.getByName("192.168.1.14");
            s = new Socket(a,8189);
            //s.setSoTimeout(10000);
            if(!s.isBound())
                System.out.println("aaaaaa, there's no server");
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
            //Item at = new Item("name", 32, "dsdfs");
            //out.writeObject(at);
        }catch (IOException ee){
            Display d = Display.getDefault();
            Shell sh = new Shell(d);
            msg.show(sh,"Unable to connect server please try later or\n write to <egorbirukov1234@gmail.com>");
            sh.dispose();
            d.dispose();
            System.exit(0);
        }
    }

    public String send(Client_request r){
        try {
            this.out.writeObject(r);
            out.reset();
            System.out.println(r.toString());
            System.out.println("I'm sending");
            return null;
        }catch (IOException e){
            return "Something went wrong, can't access server";
        }
    }

}
