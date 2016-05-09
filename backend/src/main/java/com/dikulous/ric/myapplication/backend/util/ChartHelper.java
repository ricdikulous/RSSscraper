package com.dikulous.ric.myapplication.backend.util;

import com.google.appengine.repackaged.com.google.api.client.util.DateTime;
import com.google.gson.JsonPrimitive;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ric on 9/05/16.
 */
public class ChartHelper {
    public static String prepareDateCountForChart(Map<String, Long> dateCount){
        HashMap<Long, Long> timestampMap = new HashMap<>();
        if(dateCount != null){
            Logger log = Logger.getLogger("Chart");
            log.setLevel(Level.INFO);
            for(String key:dateCount.keySet()){
                timestampMap.put(new DateTime(key).getValue(), dateCount.get(key));
            }
            SortedSet<Long> keys = new TreeSet<Long>(timestampMap.keySet());
            JsonArray jsonArray = new JsonArray();
            JsonArray jsonInnerArray;
            for(Long key:keys){
                jsonInnerArray = new JsonArray();
                jsonInnerArray.add(new JsonPrimitive(key));
                jsonInnerArray.add(new JsonPrimitive(timestampMap.get(key)));
                jsonArray.add(jsonInnerArray);
            }
            return jsonArray.toString();
        } else {
            return "";
        }
    }
}
