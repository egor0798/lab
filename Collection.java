package com.company;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by egorka on 30.03.17.
 */
class Collection extends LinkedHashMap<String, Item> {

    String mesg;
    /**
     * @author Egor
     * @version 1.0
     * @returns nothing
     */

    String sort(){
        Map<String, Item> sortedMap = new TreeMap<>();
        sortedMap.putAll(this);
        this.clear();
        this.putAll(sortedMap);
        return "Collection has been sorted";
    }


    /**
     * @author Egor
     * @version 1.0
     * @param path - path to input file
     * @returns nothing
     */
    String load(String path){
        mesg = "Collection loaded.";
        try(Scanner in1 = new Scanner(new File(path))) {
            this.clear();
            ObjectMapper rd = new ObjectMapper();
            Item n;
            if (!in1.hasNextLine())
                mesg = "File is empty";
            while (in1.hasNextLine()) {
                n = rd.readValue(in1.nextLine(), Item.class);
                this.put(n.getName(), n);
            }
        }catch (JsonMappingException |JsonParseException e){
            mesg = "Wrong input format";
        }catch (NullPointerException|IOException ex){
            mesg = "Wrong file format";
        }
        return mesg;
    }


    /**
     * @author Egor
     * @version 1.0
     * @param key - Element's key
     * @returns nothing
     */
    String remove_lower(String key){
        int i = 0;
        String[] ar = new String[this.size()];
        for (String k : this.keySet())
            if (key.length() - k.length() > 0) {
                ar[i] = k;
                i++;
            }
        int n = this.size();
        for (int j = 0; j < i; j++)
            this.keySet().remove(ar[j]);
        if(n == this.size())
            mesg = "Nothing to remove";
        else
            mesg = "Elements' removed.";
        return mesg;
    }

    /**
     * @author Egor
     * @version 1.0
     * @returns nothing
     */
    void write (){
        String path = "/home/egorka/Рабочий стол/file"; // Later need changing to getEnv
        try(PrintWriter out = new PrintWriter(path);) {
            ObjectMapper rd = new ObjectMapper();
            String s;
            for (String key : this.keySet()) {
                s = rd.writeValueAsString(this.get(key));
                out.append(s + "\n");
            }
        }catch (NullPointerException|IOException ex){
            System.out.println("-----------\n\tSomething wrong with file, please check it and environment variable!");
        }
    }
}
