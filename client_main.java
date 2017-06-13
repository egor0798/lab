package com.company;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public class client_main {

    public static void main(String[] args) throws SQLException {
        LinkedHashMap<Integer, Item> stuff = new LinkedHashMap<>();
        Client_connection x = new Client_connection();
        DBconnection dbcon = null;
        try {dbcon = new DBconnection("localhost", 5432, "progdb", "admin", "615243ya", stuff);}
        catch (SQLException ex) {
            System.out.println("Something wrong with database");
        }
        dbcon.load(stuff);
        dbcon.remove(stuff, 2);
        dbcon.removeLower(stuff, 2);
        dbcon.insert(stuff, new Item("Fuck", 12, "Fuckfuck"));
        SWTBigWindow a = new SWTBigWindow(stuff, x);
        a.Bigwin();
        dbcon.connection.close();
    }
}
