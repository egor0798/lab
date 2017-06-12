package com.company;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by Sekvoya on 012 12.05.17.
 */

class SWTBigWindow {


    private LinkedHashMap<Integer, Item> curcol;
    private static String err;
    private Display display;
    private Shell shell;
    private int pressed;
    private Client_connection con;
    private Server_request req_in;
    private Client_request req_out;

    SWTBigWindow(LinkedHashMap<Integer, Item> col, Client_connection c){
        curcol = col;
        con = c;
    }
    public void Bigwin() {
        req_in = new Server_request(curcol);
        req_out = new Client_request();
        new Display();
        display = Display.getDefault();
        shell = new Shell(display);
        shell.setLocation(120, 120);
        shell.setSize(960, 540);
        shell.setLayout(new FormLayout());
        Color my = new Color(shell.getDisplay(), 225, 252, 152);
        shell.setBackground(my);
        shell.setText("Окно в Европу");

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
        scale.setSelection(0);
        scale.setIncrement(1);
        scale.setMaximum(1000000);
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
        spin_data.left = new FormAttachment(86);
        spin_data.right = new FormAttachment(95);
        spin_data.top = new FormAttachment(60);
        Spinner spinner = new Spinner(shell, SWT.BORDER);
        scale.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (event.type == SWT.Selection) {
                    spinner.setSelection(scale.getSelection());
                }

            }
        });
        spinner.setMinimum(0);
        spinner.setMaximum(1000000);
        spinner.setSelection(0);
        spinner.setIncrement(1);
        spinner.setPageIncrement(5);
        spinner.setLayoutData(spin_data);


        //                              TEXT DESC

        final Text desc_text = new Text(shell, SWT.BORDER | SWT.WRAP );
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

        final Text name_text = new Text(shell, SWT.BORDER | SWT.WRAP);
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

        bar.setLayoutData(tooldata);
        bar.pack();
        shell.setMinimumSize(750,500);


        //                              TOOLITEM

        ToolItem insert = new ToolItem(bar, SWT.PUSH);
        insert.setText("Insert");
        insert.setToolTipText("Write at least element's name so you can add it to collection");
        insert.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (event.type == SWT.Selection) {
                    Item i =  new Item(name_text.getText().replaceAll("  ",""), Integer.parseInt(spinner.getText()), desc_text.getText().trim());
                    //fill_tree(tree, curcol);
                    name_text.setText("");
                    desc_text.setText("");
                    spinner.setSelection(0);
                    scale.setSelection(0);
                    req_out.setCode(4);
                    req_out.setItem(i);
                    err = con.send(req_out);
                    if(err!=null)
                        msg.show(shell, err);
                    try {
                        req_in = (Server_request) con.in.readObject();
                    }catch (IOException|ClassNotFoundException e1){
                        msg.show(shell, "Something wrong with connection, please check server");
                    }
                }
            }
        });


        ToolItem remove = new ToolItem(bar, SWT.PUSH);
        remove.setText("Remove");
        remove.setToolTipText("Removes selected element");
        remove.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event)  {
                if (event.type == SWT.Selection && tree.getSelectionCount() > 0 ) {
                    TreeItem t = tree.getSelection()[0];
                    String s = t.getItems()[0].toString();
                    s = s.substring(s.indexOf(':')+2, s.indexOf('}'));
                    curcol.remove(s);
                    msg.show(shell, "Element removed");
                    fill_tree(tree, curcol);
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
                    fill_tree(tree, curcol);
                }

            }
        });

        ToolItem sort = new ToolItem(bar, SWT.PUSH);
        sort.setText("ASCII art");
        sort.setToolTipText("Sorts collection elements by name");
        sort.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (event.type == SWT.Selection) {

                }

            }
        });

        ToolItem import_ = new ToolItem(bar, SWT.PUSH);
        import_.setText("Import");
        import_.setToolTipText("Loads collection from file with choosing filepath");
        import_.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (event.type == SWT.Selection) {
                    FileDialog fd = new FileDialog(shell, SWT.OPEN);
                    fd.setText("Open");
                    String filterExt ="*.txt";
                    fd.setFileName(filterExt);
                    String selected = fd.open();
                    if(selected != null) {
                        //msg.show(shell, curcol.load(selected));
                        fill_tree(tree, curcol);
                    }
                }

            }
        });
        ToolItem remove_l = new ToolItem(bar, SWT.PUSH);
        remove_l.setText("Remove Lower");
        remove_l.setToolTipText("Removes elements which ket smaller than specified key");
        remove_l.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (event.type == SWT.Selection && display.getShells().length < 2) {{
                        SmallWindow sm = new SmallWindow(display);
                        pressed = sm.show();
                        if(pressed > 0)
                            pressed++; // ПЕРЕДЕЛЕТЬ.
                    }
                }

            }
        });


        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }


    private void fill_tree(Tree tr, LinkedHashMap<Integer, Item> col) {
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