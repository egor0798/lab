package com.company;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by egorka on 12.06.17.
 */
class Client_connection {
    private InetAddress a;
    Socket s;
    ObjectInputStream in;
    ObjectOutputStream out;
    Client_connection(){
        try {
            a = InetAddress.getByName("192.168.1.4");
            s = new Socket(a,8189);
            s.setSoTimeout(7000);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        }catch (IOException ee){
            Display d = Display.getDefault();
            Shell sh = new Shell(d);
            ee.printStackTrace();
            msg.show(sh,"Unable to connect server please try later or\n write to <egorbirukov1234@gmail.com>");
            sh.dispose();
            d.dispose();
            System.exit(0);
        }
    }

    String send(Client_request r){
        try {
            this.out.writeObject(r);
            out.reset();
            return null;
        }catch (IOException e){
            return "Something went wrong, can't access server";
        }
    }

}
