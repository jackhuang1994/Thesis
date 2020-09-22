package com.jsontest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsontest.ScriptRuntime;
import com.jsontest.SimpleErrorReporter;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GsonConvert{

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //ScriptRuntime script = createScriptRuntime();
        
        List<SimpleErrorReporter> list = new ArrayList<SimpleErrorReporter>();
        
        SimpleErrorReporter s1 = createSimpleErrorReporter();
        setSimpleErrorReporter(s1, "are you ok?", "server", 1, 1);
        
        SimpleErrorReporter s2 = createSimpleErrorReporter();
        setSimpleErrorReporter(s2, "I am fine.", "server", 2, 1);
        
        SimpleErrorReporter s3 = createSimpleErrorReporter();
        setSimpleErrorReporter(s3, "Everything ok? ", "server", 3, 1);
        
        SimpleErrorReporter s4 = createSimpleErrorReporter();
        setSimpleErrorReporter(s4, "All good.", "server", 4, 1);
        
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);

        // Java objects to String
        // String json = gson.toJson(allias);

        // Java objects to File
        try (FileWriter writer = new FileWriter("E:\\Learning\\CWRU\\thesis\\JSON\\example.json")) {
            //gson.toJson(script, writer);
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //private static ScriptRuntime createScriptRuntime() {

      //ScriptRuntime script = new ScriptRuntime();


      //return script;

    //}
    
    private static SimpleErrorReporter createSimpleErrorReporter() {
      SimpleErrorReporter simple = new SimpleErrorReporter();
      //simple.warning("are you ok?", "server", 1, 1);
      return simple;
    }
    
    private static SimpleErrorReporter setSimpleErrorReporter(SimpleErrorReporter simple, String message, String sourceName, int line, int lineOffset) 
    {
      //SimpleErrorReporter simple = new SimpleErrorReporter();
      simple.warning(message, sourceName, line, lineOffset);
      return simple;
    }
      
}