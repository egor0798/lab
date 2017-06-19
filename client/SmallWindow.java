package com.company;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

/**
 * Created by egorka on 17.05.17.
 */

class SmallWindow {
    private Display display;
    private int a;
    SmallWindow(Display d) {
        display = d;
    }
    int show(){
                a = 0;
                final Shell small = new Shell(display, SWT.CLOSE);
                small.setLocation(220, 220);
                small.setSize(300, 160);
                Button yes = new Button(small, SWT.PUSH);
                Image image = display.getSystemImage(SWT.ICON_WARNING);
                yes.setImage(image);
                yes.setBounds(100, 70, 80, 60);
                small.setText("Please enter a key (INTEGER)");
                final Text text = new Text(small, SWT.BORDER);
                text.setText("");
                text.setBounds(40, 20, 220, 40);
                text.setFocus();
                text.addVerifyListener(event -> {
                    String allowedCharacters = "0123456789";
                    String text1 = event.text;
                    for (int index = 0; index < text1.length(); index++) {
                        char character = text1.charAt(index);
                        boolean isAllowed = allowedCharacters.indexOf(character) > -1;
                        if (!isAllowed) {
                            event.doit = false;
                            return;
                        }
                    }
                });
                small.addListener(SWT.Close, event -> {
                    a = -1;
                    small.dispose();
                });
                yes.addListener(SWT.Selection, event -> {
                    if (event.type == SWT.Selection) {
                        a = Integer.parseInt(text.getText()) ;
                        small.dispose();
                    }

                });
                small.open();
                while (!small.isDisposed()) {
                    if (!display.readAndDispatch()) display.sleep();
                }
                small.dispose();
                return a;
    }
}
