package com.company;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
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
        //Color my = new Color(shell.getDisplay(), 0, 0, 255);


        //                              TREE

        final Tree tree = new Tree (shell, SWT.BORDER);
        FormData treeField = new FormData();
        treeField.left = new FormAttachment(5);
        treeField.right = new FormAttachment(95);
        treeField.top = new FormAttachment(3);
        treeField.bottom = new FormAttachment(50);
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


        //                              TOOLBAR

        ToolBar bar = new ToolBar (shell, SWT.FLAT | SWT.WRAP | SWT.CENTER);
        FormData tooldata = new FormData();
        tooldata.bottom = new FormAttachment(98);
        tooldata.top = new FormAttachment(92);
        tooldata.left = new FormAttachment(24);
        tooldata.right = new FormAttachment(76);
        for (int i=0; i<9; i++) {
            ToolItem item = new ToolItem (bar, SWT.PUSH|SWT.FILL);
            item.setText ("Item " + i);
        }
        bar.setLayoutData(tooldata);
        bar.pack();
        //bar.setBackground(my);


        //                              SCALE

        FormData sc_data = new FormData();
        sc_data.left = new FormAttachment(72);
        sc_data.right = new FormAttachment(90);
        sc_data.bottom = new FormAttachment(65);
        sc_data.top = new FormAttachment(60);

        Scale scale = new Scale (shell, SWT.BORDER);
        scale.setLayoutData(sc_data);
        //Rectangle clientArea = shell.getClientArea ();
        //scale.setBounds (clientArea.x, clientArea.y, 200, 64);
        scale.setMaximum (40);
        scale.setPageIncrement (5);


        //                              SCALE LABEL

        Label sc_label = new Label(shell, SWT.CENTER);
        sc_label.setText("Count of items");
        FormData sc_lab_data = new FormData();
        sc_lab_data.top = new FormAttachment(scale, 15);
        sc_lab_data.left = new FormAttachment(scale, -90);
        sc_label.setLayoutData(sc_lab_data);


        //                              SPINNER

        FormData spin_data = new FormData();
        spin_data.left = new FormAttachment(92);
        spin_data.right = new FormAttachment(98);
       // spin_data.bottom = new FormAttachment(65);
        spin_data.top = new FormAttachment(60);

        Spinner spinner = new Spinner (shell, SWT.BORDER);
        spinner.setMinimum(0);
        spinner.setMaximum(100);
        spinner.setSelection(0);
        spinner.setIncrement(1);
        spinner.setPageIncrement(5);
        spinner.setLayoutData(spin_data);
        //Rectangle clientArea = shell.getClientArea();
        //spinner.setLocation(clientArea.x, clientArea.y);
        //spinner.pack();


        //                              TEXT DESC

        final Text desc_text = new Text(shell, SWT.BORDER | SWT.MULTI);
        FormData desc_text_data = new FormData();
        desc_text_data.top = new FormAttachment(70);
        desc_text_data.bottom = new FormAttachment(85);
        desc_text_data.left = new FormAttachment(13);
        desc_text_data.right = new FormAttachment(60);
        desc_text.setLayoutData(desc_text_data);


        //                              TEXT DESC LABEL

        Label desc_label = new Label(shell, SWT.LEFT);
        desc_label.setText("Short \ndescription:");
        FormData desc_lab_data = new FormData();
        desc_lab_data.right = new FormAttachment(12);
        desc_lab_data.left = new FormAttachment(2);
        desc_lab_data.top = new FormAttachment(76);
        desc_label.setLayoutData(desc_lab_data);


        //                              TEXT NAME

        final Text name_text = new Text(shell, SWT.BORDER | SWT.MULTI);
        FormData name_text_data = new FormData();
        name_text_data.top = new FormAttachment(56);
        name_text_data.bottom = new FormAttachment(64);
        name_text_data.left = new FormAttachment(13);
        name_text_data.right = new FormAttachment(60);
        name_text.setLayoutData(name_text_data);


        //                              TEXT NAME LABEL

        Label name_label = new Label(shell, SWT.LEFT);
        name_label.setText("Name:");
        FormData name_label_data = new FormData();
        name_label_data.right = new FormAttachment(12);
        name_label_data.left = new FormAttachment(2);
        name_label_data.top = new FormAttachment(59);
        name_label.setLayoutData(name_label_data);


        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
    }
}



/*Group gr = new Group(shell, SWT.NONE);
        FormData group = new FormData();
        group.left = new FormAttachment(10);
        group.right = new FormAttachment(90);
        group.top = new FormAttachment(70);
        group.bottom = new FormAttachment(99);
        gr.setLayoutData(group);*/


//Button bar = new Button(shell, SWT.PUSH);
//tooldata.left = new FormAttachment(gr, 10);
//tooldata.right = new FormAttachment(gr, 10);
//Rectangle clientArea = shell.getClientArea ();
//bar.setLocation (clientArea.x, clientArea.y);/

