package com.company;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

import java.util.LinkedHashMap;

/**
 * Created by Sekvoya on 012 12.05.17.
 */
public class SWTBigWindow {
    public static void Bigwin(Collection curcol) {
        LinkedHashMap collect = curcol; // вот в этом и был весь косяк
        Display display = new Display ();
        Shell shell = new Shell (display);
        shell.setLocation(120, 120);
        shell.setSize (960, 540);
        shell.setLayout(new FormLayout());
        final Tree tree = new Tree (shell, SWT.BORDER);

        FormLayout layout = new FormLayout();

        FormData treeField = new FormData();
        treeField.left = new FormAttachment(5);
        treeField.right = new FormAttachment(5);
        treeField.top = new FormAttachment(5);
        treeField.bottom = new FormAttachment(40);
        tree.setLayoutData(treeField);

        for (Item i: curcol.values()) {
            TreeItem iItem = new TreeItem (tree, 0);
            iItem.setText (i.getName()); //заголовок
                TreeItem nameItem = new TreeItem(iItem, 0);
                nameItem.setText("Name: " + i.getName());

                TreeItem descItem = new TreeItem(iItem, 0);
                descItem.setText("Short decription: " + i.getDesc());

                TreeItem numbItem = new TreeItem(iItem, 0);
                numbItem.setText("Count of items: " + i.getNumber());
        }

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
    }
}
