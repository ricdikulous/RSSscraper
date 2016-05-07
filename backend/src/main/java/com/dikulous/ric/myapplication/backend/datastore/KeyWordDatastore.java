package com.dikulous.ric.myapplication.backend.datastore;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ric on 7/05/16.
 */
public class KeyWordDatastore {
    public final static String KIND = "KeyWords";
    public final static String PROPERTY_KEY_WORD = "keyWord";
    public final static String PROPERTY_CATEGORY = "category";
    public final static String PROPERTY_TONE = "tone";
    public final static String PROPERTY_CREATED_AT = "createdAt";

    public static Boolean insertKeyWord(String keyWord, String category, String tone){
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Boolean success = false;
        if(!doesKeyWordExist(keyWord)) {
            Entity entity = new Entity(KIND);
            entity.setProperty(PROPERTY_KEY_WORD, keyWord);
            entity.setProperty(PROPERTY_CATEGORY, category);
            entity.setProperty(PROPERTY_TONE, tone);
            entity.setProperty(PROPERTY_CREATED_AT, new Date().getTime());
            datastoreService.put(entity);
            success = true;
            System.out.println("put in new");
        } else {
            System.out.println("Already exists or is not valid");
        }
        return success;
    }

    public static List<String> readAllKeyWords(){
        List<String> keyWords = new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query(KIND);
        PreparedQuery pq = datastore.prepare(q);
        for(Entity result:pq.asIterable()){
            keyWords.add((String)result.getProperty(PROPERTY_KEY_WORD));
        }
        return keyWords;
    }

    public static boolean doesKeyWordExist(String keyWord){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query(KIND);
        Query.Filter keyWordFilter = new Query.FilterPredicate(PROPERTY_KEY_WORD, Query.FilterOperator.EQUAL, keyWord);
        q.setFilter(keyWordFilter);
        PreparedQuery pq = datastore.prepare(q);
        for(Entity result:pq.asIterable()){
            return true;
        }
        return false;
    }
}
