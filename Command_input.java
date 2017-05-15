package com.company;

import java.util.Scanner;

/**
 * Created by egorka on 23.04.17.
 */
class Command_input {
    private  String path;
    private Collection col;
    private Scanner usr;
    private String cmd;
    Command_input(String FilePath, Collection collection) {
        this.path = FilePath;
        this.col = collection;
        collection.load(FilePath);
        collection.help();
        start();
    }

    private void start(){
        usr = new Scanner(System.in);
        while (true) {
            cmd = usr.nextLine();
            cmd = cmd.trim();

            if (cmd.equals("close")){
                System.exit(0);
            }

            if (cmd.equals("sort")){
                col.sort();
                continue;
            }

            if (cmd.equals("load")){
                col.load(path);
                continue;
            }

            if (cmd.equals("help")) {
                col.help();
                continue;
            }

            if (cmd.equals("show")) {
                col.show();
                continue;
            }

            if (cmd.equals("remove_lower")) {
                System.out.println("-----------\n\tYou forgot about key!\n");
                continue;
            }

            if (cmd.equals("import")) {
                System.out.println("-----------\n\tYou entered \"import\" without parameters");
                continue;
            }

            if (cmd.equals("insert")) {
                System.out.println("-----------\n\tYou entered \"insert\" without parameters");
                continue;
            }

            if (cmd.length() >= 7) {
                if (cmd.substring(0, 7).equals("import ")) {
                    col.load(cmd.substring(7).trim());
                    continue;
                }

                if (cmd.substring(0, 7).equals("insert ")) {
                    int i;
                    i = cmd.indexOf('{');
                    if (i < 9 || cmd.substring(7, i - 1).trim().equals(""))
                        System.out.println("-----------\n\tPlease don't forget about key");
                    else
                        col.insert(cmd.substring(7, i - 1), cmd.substring(i, cmd.length()));
                    continue;
                    }


                if (cmd.length() > 12)
                    if (cmd.substring(0, 13).equals("remove_lower ")) {
                        String key = cmd.substring(cmd.indexOf(' '));
                        key = key.trim();
                        col.remove_lower(key);
                        continue;
                    }

            }
            System.out.println("-----------\n\tSomething wrong... To see command list again write \"help\"\n");
        }
    }
}

