package com.company;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by egorka on 25.05.17.
 */
public class msg {
    public static void show(Shell sh, String str){
        MessageBox messageBox = new MessageBox(sh, SWT.OK);
        messageBox.setMessage(str);
        messageBox.open();
    }
}
