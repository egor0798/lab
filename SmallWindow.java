package com.company;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

/**
 * Created by egorka on 17.05.17.
 */

class SmallWindow {
    private Display display;
    private String a;
    private SmallWindow(Display d) {
        display = d;
    }
    String show(){
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
