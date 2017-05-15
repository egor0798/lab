package com.company;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by egorka on 30.03.17.
 */
class Collection extends LinkedHashMap<String, Item> {

    /**
     * @author Egor
     * @version 1.0
     * @returns nothing
     */

    void sort(){
        Map<String, Item> sortedMap = new TreeMap<>();
        sortedMap.putAll(this);
        this.clear();
        this.putAll(sortedMap);
    }


    /**
     * @author Egor
     * @version 1.0
     * @returns nothing
     */
    void help(){                                  //Method summary
        System.out.println("\nYou can use commands:");
        System.out.println("show - Show elements in collection ");
        System.out.println("close - close program and save collection to file \"data.txt\" ");
        System.out.println("insert {String key} {element} -  add new element with adjusted key");
        System.out.println("import {String path} - fulfill collection with elements from file");
        System.out.println("remove_lower {String key} - remove elements what key lower than adjusted");
        System.out.println("load - re-read collection from file\n");
        System.out.println("Write a command: ");
    }

    /**
     * @author Egor
     * @version 1.0
     * @returns nothing
     */
    void show(){
        if(this.isEmpty())
            System.out.println("-----------\n\tCollection is empty");
        for (String key : this.keySet())
        System.out.println("key: " + key);
    }

    /**
     * @author Egor
     * @version 1.0
     * @param key - Element'key
     * @param element - Element in JSON
     * @returns nothing
     */
    void insert(String key, String element){
        try{
            ObjectMapper rd = new ObjectMapper();
            this.put(key, rd.readValue(element, Item.class));
            System.out.println("-----------\n\tNew element added.");
        }catch (IOException e){
            System.out.println("Wrong object format!");
        }

    }

    /**
     * @author Egor
     * @version 1.0
     * @param path - path to input file
     * @returns nothing
     */
    void load(String path){
        try(Scanner in1 = new Scanner(new File(path))) {
            this.clear();
            ObjectMapper rd = new ObjectMapper();
            Item n;
            if (in1.hasNextLine())
                System.out.println("-----------\n\tLoading collection");
            else
                System.out.println("-----------\n\tFile is empty\n");
            while (in1.hasNextLine()) {
                n = rd.readValue(in1.nextLine(), Item.class);
                this.put(n.getName(), n);
            }
        }catch (JsonMappingException |JsonParseException e){
            System.out.println("-----------\n\tWrong input format\n");
            e.getMessage();
            e.printStackTrace();
        }catch (NullPointerException|IOException ex){
            ex.getMessage();
            System.out.println("-----------\n\tWrong filepath! Please check the environment variable and file. ");
            System.out.println("\tIf you're can't handle it by yourself, please write feedback to <egorbirukov1234@gmail.com> ");
        }
    }

    /**
     * @author Egor
     * @version 1.0
     * @param key - Element's key
     * @returns nothing
     */
    void remove_lower(String key){
        int i = 0;
        String[] ar = new String[this.size()];
        for (String k : this.keySet())
            if (key.length() - k.length() > 0) {
                ar[i] = k;
                i++;
            }
        if(ar[0] != null )
            System.out.println("-----------\n\tRemoving elements");
        else
            System.out.println("-----------\n\tNothing to remove");
        for (int j = 0; j < i; j++)
            this.keySet().remove(ar[j]);
    }
}
