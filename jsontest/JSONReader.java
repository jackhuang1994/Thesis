package com.jsontest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class JSONReader {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("E:\\Learning\\CWRU\\thesis\\JSON\\example.json")) {
            
            Object warningsObj = parser.parse(reader);
            JSONArray warnings = (JSONArray) warningsObj;
            //JSONObject jsonObject = (JSONObject) warningsObj;
            //JSONArray warnings = (JSONArray) jsonObject.get("warnings");
            for (Object w : warnings){
              System.out.println(w);
            }
            //System.out.println(warnings);
            //Iterator<String> warningsIterator = warnings.iterator();
            
            
            //JSONObject jsonObject = (JSONObject) parser.parse(reader);
            //System.out.println(jsonObject);

            //String warnings = (String) jsonObject.get("warnings");
            //System.out.println(warnings);

            //long age = (Long) jsonObject.get("age");
            //System.out.println(age);

            // loop array
            //JSONArray msg = (JSONArray) jsonObject.get("messages");
            //Iterator<String> iterator = msg.iterator();
            //while (iterator.hasNext()) {
                //System.out.println(iterator.next());
            //}

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}