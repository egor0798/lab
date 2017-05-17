package com.company;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;


/**
 * Created by Sekvoya on 012 12.05.17.
 */

public class SWTBigWindow {

    private Collection curcol;
    private static String path;
    private Display display;
    private Shell shell;

    SWTBigWindow(String p, Collection col){
        path = p;
        curcol = col;

    }

    public void Bigwin() {
        display = new Display();
        shell = new Shell(display);
        shell.setLocation(120, 120);
        shell.setSize(960, 540);
        shell.setLayout(new FormLayout());
        Color my = new Color(shell.getDisplay(), 225, 252, 152);
        shell.setBackground(my);


        //                              TREE

        Tree tree = new Tree(shell, SWT.BORDER);
        FormData treeField = new FormData();
        treeField.left = new FormAttachment(5);
        treeField.right = new FormAttachment(95);
        treeField.top = new FormAttachment(3);
        treeField.bottom = new FormAttachment(50);
        tree.setLayoutData(treeField);
        fill_tree(tree, curcol);

        //                              TOOLBAR

        ToolBar bar = new ToolBar(shell, SWT.BORDER);
        FormData tooldata = new FormData();
        tooldata.bottom = new FormAttachment(100);
        tooldata.top = new FormAttachment(92);
        tooldata.left = new FormAttachment(24);
        tooldata.right = new FormAttachment(90);



        bar.setLayoutData(tooldata);
        bar.pack();
        bar.setBackground(my);
        shell.setMinimumSize(750, 500);

        //                              SCALE

        
        FormData sc_data = new FormData();
        sc_data.left = new FormAttachment(68);
        sc_data.right = new FormAttachment(85);
        sc_data.bottom = new FormAttachment(65);
        sc_data.top = new FormAttachment(60);

        Scale scale = new Scale(shell, SWT.BORDER);
        scale.setLayoutData(sc_data);
        scale.setMaximum(40);
        scale.setPageIncrement(5);


        //                              SCALE LABEL

        Label sc_label = new Label(shell, SWT.CENTER);
        sc_label.setText("Count of items");
        FormData sc_lab_data = new FormData();
        sc_lab_data.top = new FormAttachment(scale, 15);
        sc_lab_data.left = new FormAttachment(scale, -90);
        sc_label.setLayoutData(sc_lab_data);


        //                              SPINNER

        FormData spin_data = new FormData();
        spin_data.left = new FormAttachment(86);//непрочно
        spin_data.right = new FormAttachment(95);
        // spin_data.bottom = new FormAttachment(65);
        spin_data.top = new FormAttachment(60);

        Spinner spinner = new Spinner(shell, SWT.BORDER);
        spinner.setMinimum(0);
        spinner.setMaximum(10000);
        spinner.setSelection(0);
        spinner.setIncrement(1);
        spinner.setPageIncrement(5);
        spinner.setLayoutData(spin_data);


        //                              TEXT DESC

        final Text desc_text = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.WRAP);
         FormData desc_text_data = new FormData();
        desc_text_data.top = new FormAttachment(71);
        desc_text_data.bottom = new FormAttachment(85);
        desc_text_data.left = new FormAttachment(13);
        desc_text_data.right = new FormAttachment(60);
        desc_text.setLayoutData(desc_text_data);


        //                              TEXT DESC LABEL

        Label desc_label = new Label(shell, SWT.LEFT);
        desc_label.setText("Short \ndescription:");
        FormData desc_lab_data = new FormData();
        desc_lab_data.right = new FormAttachment(12);
        desc_lab_data.left = new FormAttachment(5);
        desc_lab_data.top = new FormAttachment(76);
        desc_label.setLayoutData(desc_lab_data);


        //                              TEXT NAME

        final Text name_text = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.WRAP);
        FormData name_text_data = new FormData();
        name_text_data.top = new FormAttachment(59,4);
        name_text_data.bottom = new FormAttachment(70);
        name_text_data.left = new FormAttachment(13);
        name_text_data.right = new FormAttachment(60);
        name_text.setLayoutData(name_text_data);


        //                              TEXT NAME LABEL

       Label name_label = new Label(shell, SWT.LEFT);
        name_label.setText("Name:");
        FormData name_label_data = new FormData();
        name_label_data.right = new FormAttachment(12);
        name_label_data.left = new FormAttachment(5);
        name_label_data.top = new FormAttachment(63,3);
        name_label.setLayoutData(name_label_data);


        //                              TOOLITEM

        ToolItem insert = new ToolItem(bar, SWT.PUSH);
        insert.setText("Insert");
        insert.setToolTipText("Write at least element's name so you can add it to collection");
        insert.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (event.type == SWT.Selection) {
                    curcol.put(name_text.getText(), new Item(name_text.getText(), spinner.getDigits(), desc_text.getText()));
                    curcol.write();
                    fill_tree(tree, curcol);
                    name_text.setText("");
                    desc_text.setText("");
                    spinner.setSelection(0);

                }

            }
        });

        bar.setLayoutData(tooldata);
        bar.pack();
        shell.setMinimumSize(750,500);

        ToolItem remove = new ToolItem(bar, SWT.PUSH);
        remove.setText("Remove");
        remove.setToolTipText("Removes selected element");
        remove.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (event.type == SWT.Selection) {
                    curcol.remove((tree.getSelection()));
                    //tree.get
                }
            }
        });

        ToolItem reload = new ToolItem(bar, SWT.PUSH);
        reload.setText("Reload");
        reload.setToolTipText("Reload collection from file");
        reload.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (event.type == SWT.Selection) {
                    curcol.load(path);
                    fill_tree(tree, curcol);
                    curcol.write();
                }

            }
        });

        ToolItem sort = new ToolItem(bar, SWT.PUSH);
        sort.setText("Sort");
        sort.setToolTipText("Sorts collection elements by name");

        ToolItem import_ = new ToolItem(bar, SWT.PUSH);
        import_.setText("Import");
        import_.setToolTipText("Loads collection from file with choosing filepath");

        ToolItem remove_l = new ToolItem(bar, SWT.PUSH);
        remove_l.setText("Remove Lower");
        remove_l.setToolTipText("Removes elements which ket smaller than specified key");


        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }


    public void fill_tree(Tree tr, Collection col) {
        tr.clearAll(true);
        tr.removeAll();
        for (Item i : col.values()) {
            TreeItem iItem = new TreeItem(tr, 0);
            iItem.setText(i.getName()); //заголовок
            TreeItem nameItem = new TreeItem(iItem, 0);
            nameItem.setText("Name: " + i.getName());

            TreeItem descItem = new TreeItem(iItem, 0);
            descItem.setText("Short decription: " + i.getDesc());

            TreeItem numbItem = new TreeItem(iItem, 0);
            numbItem.setText("Count of items: " + i.getNumber());
        }

    }
}
