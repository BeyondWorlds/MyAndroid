package com.example;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by {wq} on 2018/6/28.
 */

public class Test {

    public static void main(String[] args) {

        Map map = new HashMap<String, String>();
        map.put("one", "A");
        map.put("two", "B");
        map.put("three", "C");
        map.put("center", "D");

        String name = "C";
        Iterator<String> dataIter = map.keySet().iterator();
        while (dataIter.hasNext()) {
            String key = dataIter.next();
            if (map.get(key).equals(name)) {
                map.put(key, map.get("center"));
                map.put("center", name);
                break;
            }
        }
        Iterator<String> dataIter1 = map.keySet().iterator();
        while (dataIter1.hasNext()) {
            String key = dataIter1.next();
            System.out.println("key="+key+" value="+map.get(key));
        }
    }

}
