/**
 * Created by sekvoya on 11.06.17.
 */
package com.company;

import java.sql.*;

public class DBconnection {

    static protected Connection connection;
    protected String url, hostname, database, password, username, tableName;
    protected int port;

    public DBconnection(String hostname, int port, String database, String username, String password) throws SQLException {
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
    }

    public void saveInto(Collection col) throws NullPointerException, SQLException {
        Statement statement = null;
        try {statement = connection.createStatement();}
        catch (SQLException | NullPointerException ex){
            System.out.println("I can't execute your query");
        }
        for (Item i: col.values()) {
            String str = "insert into ITEMS(NAME, DESCRIPT, NUMBER) values (" + "\'" + i.getName() + "\', \'" +
                    i.getDesc() + "\', " + Integer.toString(i.getNumber()) + ");";
    //        System.out.println(str);
            try {statement.executeUpdate(str);}
            catch (SQLException ex){
                System.out.println("Item with equal name already exists");
            }
        }
    }

    public void getColl(Collection col) throws  NullPointerException, SQLException {
        Statement statement = null;
        try {statement = connection.createStatement();}
        catch (SQLException | NullPointerException ex){
            System.out.println("I can't execute your query");
        }
        ResultSet ans = statement.executeQuery("SELECT * FROM ITEMS;");
        while (ans.next()){
 /* отладочный
            System.out.println(ans.getString("name") + ' ' + ans.getString("descript") + ' ' +
                ans.getString("number"));
 */
            col.put(ans.getString("name"), new Item(ans.getString("name"),
                    Integer.parseInt(ans.getString("number")),
                    ans.getString("descript")));
        }
/*  отладочный
        for (Item i: col.values()){
            System.out.println(i.getName() + ' ' + i.getDesc() + ' ' + i.getNumber());
        }
*/
    }

}
