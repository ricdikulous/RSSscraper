package com.dikulous.ric.myapplication.backend.datastore;

import com.dikulous.ric.myapplication.backend.util.RssHelper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.com.google.common.base.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ric on 7/05/16.
 */
public class RssDatastore {
    private static final Long HOUR_IN_MILLI_SECONDS = 3600000L;

    private static final String KIND = "Rss";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_COUNTRY = "country";
    private static final String PROPERTY_CATEGORY = "category";
    private static final String PROPERTY_SCHEDULED_READ = "scheduledRead";
    private static final String PROPERTY_READ_FREQUENCY = "readFrequency";

    public static Boolean insertRss(String url, String country, String category){
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Boolean success = false;
        if(!doesUrlExist(url) && RssHelper.isUrlValid(url)) {
            Entity entity = new Entity(KIND);
            entity.setProperty(PROPERTY_URL, url);
            entity.setProperty(PROPERTY_COUNTRY, country);
            entity.setProperty(PROPERTY_CATEGORY, category);
            entity.setProperty(PROPERTY_SCHEDULED_READ, new Date().getTime());
            entity.setProperty(PROPERTY_READ_FREQUENCY, HOUR_IN_MILLI_SECONDS);
            datastoreService.put(entity);
            success = true;
            System.out.println("put in new");
        } else {
            System.out.println("Already exists or is not valid");
        }
        return success;
    }

    public static List<String> readAllUrls(){
        List<String> urls = new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query(KIND);
        PreparedQuery pq = datastore.prepare(q);
        for(Entity result:pq.asIterable()){
            urls.add((String)result.getProperty(PROPERTY_URL));
        }
        return urls;
    }

    public static boolean doesUrlExist(String url){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query(KIND);
        Query.Filter urlFilter = new Query.FilterPredicate(PROPERTY_URL, Query.FilterOperator.EQUAL, url);
        q.setFilter(urlFilter);
        PreparedQuery pq = datastore.prepare(q);
        for(Entity result:pq.asIterable()){
            return true;
        }
        return false;
    }
}
