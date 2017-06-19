/**
 * Created by sekvoya on 11.06.17.
 */
package com.company;

import java.sql.*;
import java.util.LinkedHashMap;

public class DBconnection {

    static protected Connection connection;
    protected String url, hostname, database, password, username;
    protected int port;
    protected LinkedHashMap<Integer, Item> col;

    public DBconnection(String hostname, int port, String database, String username, String password, LinkedHashMap<Integer, Item> col) throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + database +
                "?user=" + username + "&password=" + password;
        this.connection = DriverManager.getConnection(url);
        this.url = url;
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.col = col;
    }


    public LinkedHashMap<Integer, Item> load() throws  NullPointerException, SQLException {
        Statement statement = null;
        try {statement = connection.createStatement();}
        catch (SQLException | NullPointerException ex){
            System.out.println("I can't execute your query");
        }
        ResultSet ans = statement.executeQuery("SELECT * FROM ITEMS;");
        col.clear();
        System.out.println(ans.toString());
        while (ans.next()){
            col.put(Integer.parseInt(ans.getString("item_id")), new Item(ans.getString("name"),
                    Integer.parseInt(ans.getString("number")),
                    ans.getString("descript")));
            System.out.println(ans.getString("item_id"));
        }
        System.out.println("| " + col.size());
        return col;
    }

    public LinkedHashMap<Integer, Item> insert(Item it){
        Statement statement = null;
        try {statement = connection.createStatement();}
        catch (SQLException | NullPointerException ex){
            System.out.println("I can't execute your query");
        }
        try {statement.executeUpdate("insert into ITEMS(NAME, DESCRIPT, NUMBER) values (" + "\'" + it.getName() + "\', \'" +
                it.getDesc() + "\', " + Integer.toString(it.getNumber()) + ");");
        }
        catch (SQLException ex){
            System.out.println("Item with equal name already exists");
        }

        return col;
    }

    public LinkedHashMap<Integer, Item> remove(Integer k){
        Statement statement = null;
        try {statement = connection.createStatement();}
        catch (SQLException | NullPointerException ex){
            System.out.println("I can't execute your query");
        }
        try {statement.executeUpdate("DELETE FROM items WHERE item_id = " + Integer.toString(k) + ";");
        }
        catch (SQLException ex){
            System.out.println("Item with equal name already exists");
        }

        return col;
    }

    public LinkedHashMap<Integer, Item> removeLower(Integer k){
        Statement statement = null;
        try {statement = connection.createStatement();}
        catch (SQLException | NullPointerException ex){
            System.out.println("I can't execute your query");
        }
        try {statement.executeUpdate("DELETE FROM items WHERE item_id <= " + Integer.toString(k) + ";");
        }
        catch (SQLException ex){
            System.out.println("Item with equal name already exists");
        }

        return col;
    }
}
