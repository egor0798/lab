package com.company;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by egorka on 12.05.17.
 */
public class SWT_try {
    public static void run(){
        Display display = new Display();
        Shell shell = new Shell(display);/*
        shell.setText("Let me speak from my heart");
        shell.setSize(640,480);

        Color my = new Color(shell.getDisplay(), 0, 0, 255);
        shell.setLocation(10,0);
        Label label = new Label(shell, SWT.NONE);
        new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
        label.setText("This is fucked up.");
        new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
        label.setAlignment(SWT.RIGHT);
        label.setLocation(240,200);
        label.setForeground(my);
        //label.pack();
        shell.setLayout(new GridLayout());
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        shell.dispose();
        display.dispose();
        Shell shell = new Shell ();*/
        FormLayout layout = new FormLayout();
        layout.marginWidth = 5;
        layout.marginHeight = 5;
        shell.setLayout(layout);
        ToolBar bar = new ToolBar (shell, SWT.BORDER);
        for (int i=0; i<7; i++) {
            ToolItem item = new ToolItem (bar, SWT.PUSH);
            item.setText ("Item " + i);
        }
        //Button bar = new Button(shell, SWT.PUSH);
        FormData data3 = new FormData();
        data3.left = new FormAttachment(25);
        data3.right = new FormAttachment(75);
        data3.bottom = new FormAttachment(100,-5);
        data3.top = new FormAttachment(95,-5);
        bar.setLayoutData(data3);
        Rectangle clientArea = shell.getClientArea ();
        bar.setLocation (clientArea.x, clientArea.y);
        bar.pack ();
        shell.open ();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
    }


}
