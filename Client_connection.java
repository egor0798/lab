package com.company;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by egorka on 12.06.17.
 */
public class Client_connection {
    private InetAddress a;
    private Socket s;
    ObjectInputStream in;
    ObjectOutputStream out;
    Client_connection(){
        try {
            a = InetAddress.getLocalHost();
            s = new Socket(a,8189);
            s.setSoTimeout(10000);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        }catch (IOException ee){
            Display d = Display.getDefault();
            Shell sh = new Shell(d);
            msg.show(sh,"Unable to connect server please try later or\n write to <egorbirukov1234@gmail.com>");
            sh.dispose();
            d.dispose();
        }
    }

    public String send(Object ob){
        try {
            this.out.writeObject(ob);
            return null;
        }catch (IOException e){
            return "Something went wrong, can't access server";
        }
    }

}
