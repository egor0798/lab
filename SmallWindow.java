package com.company;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.*;

/**
 * Created by egorka on 17.05.17.
 */
/*public class SmallWindow {
    Display disp;
    Shell sh;
    public SmallWindow(Display d, Shell s){
        disp=d;
        sh=s;
        class MyThread extends Thread{
            public void run() {
                final Shell small = new Shell(sh, SWT.CLOSE);
                small.setLocation(120, 120);
                small.setSize(400, 200);
                small.setText("Please enter a key");
                //final Text text = new Text(small, SWT.NONE);
                FormData text_data = new FormData();
                //text.setText("fdgdfgfddf");
                text_data.top = new FormAttachment(small,10);
                //text.setLayoutData(text_data);
                Label a = new Label(small, SWT.NONE);
                a.setText("fddfgdfgfdgfdf");
                a.setLayoutData(text_data);
                small.open();
                while (!small.isDisposed()) {
                    if (!disp.readAndDispatch()) disp.sleep();
                }
                disp.dispose();
            }
        }
        MyThread aa = new MyThread();
        aa.run();
    }
    /*public void show(){

        class MyThread extends Thread{
            public void run() {
                final Shell small = new Shell(sh, SWT.CLOSE);
                small.setLocation(120, 120);
                small.setSize(400, 200);
                small.setText("Please enter a key");
                    //final Text text = new Text(small, SWT.NONE);
                    FormData text_data = new FormData();
                    //text.setText("fdgdfgfddf");
                    text_data.top = new FormAttachment(small,10);
                    //text.setLayoutData(text_data);
                Label a = new Label(small, SWT.NONE);
                a.setText("fddfgdfgfdgfdf");
                a.setLayoutData(text_data);
                small.open();
                while (!small.isDisposed()) {
                    if (!disp.readAndDispatch()) disp.sleep();
                }
                disp.dispose();
            }
        }
        MyThread aa = new MyThread();
        aa.run();

    }*/
//}
public class SmallWindow {
    Display display;
    String a;
    public SmallWindow(Display d) {
        display = d;
    }
    public String show(){
                a = "";
                final Shell small = new Shell(display, SWT.CLOSE);
                small.setLocation(220, 220);
                small.setSize(300, 160);
                Button yes = new Button(small, SWT.PUSH);
                Image image = display.getSystemImage(SWT.ICON_WARNING);
                yes.setImage(image);
                yes.setBounds(100, 70, 80, 60);
                small.setText("Please enter a key");
                final Text text = new Text(small, SWT.NONE);
                text.setText("");
                text.setBounds(40, 20, 220, 40);
                text.setFocus();
                small.addListener(SWT.Close, new Listener() {
                    @Override
                    public void handleEvent(Event event) {
                        a = null;
                        small.dispose();
                    }
                });
                yes.addListener(SWT.Selection, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    if (event.type == SWT.Selection) {
                        a = text.getText();
                        small.dispose();
                    }

                }});
                small.open();
                while (!small.isDisposed()) {
                    if (!display.readAndDispatch()) display.sleep();
                }
                small.dispose();
                return a;
    }
}
