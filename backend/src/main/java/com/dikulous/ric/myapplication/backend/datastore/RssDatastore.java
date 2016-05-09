package com.dikulous.ric.myapplication.backend.datastore;

import com.dikulous.ric.myapplication.backend.model.RssEntity;
import com.dikulous.ric.myapplication.backend.util.Globals;
import com.dikulous.ric.myapplication.backend.util.RssHelper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.com.google.common.base.Pair;
import com.googlecode.objectify.ObjectifyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by ric on 7/05/16.
 */
public class RssDatastore {

    static {
        ObjectifyService.register(RssEntity.class);
    }
    public static Boolean insertRss(String url, String country, String category){
        Boolean success = false;
        if(!doesUrlExist(url) && RssHelper.isUrlValid(url)) {
            RssEntity rssEntity = new RssEntity();
            rssEntity.setUrl(url);
            rssEntity.setCountryCode(country);
            rssEntity.setCategory(category);
            rssEntity.setScheduledRead(new Date().getTime());
            rssEntity.setReadFrequency(Globals.DEFAULT_RSS_BACKOFF);
            rssEntity.setLastRead(0L);
            ofy().save().entity(rssEntity);
            success = true;
            System.out.println("put in new");
        } else {
            System.out.println("Already exists or is not valid");
        }
        return success;
    }

    public static List<String> readAllUrls(){
        List<String> urls = new ArrayList<>();
        for(RssEntity rssEntity:ofy().load().type(RssEntity.class).list()){
            urls.add(rssEntity.getUrl());
        }
        return urls;
    }

    public static List<RssEntity> readRssEntities(){
        return ofy().load().type(RssEntity.class).list();
    }

    public static List<RssEntity> readScheduledRssEntities(){
        return ofy().load().type(RssEntity.class).filter("scheduledRead <=", new Date().getTime()).list();
    }

    public static boolean doesUrlExist(String url){
        if(ofy().load().type(RssEntity.class).filter("url", url).first().now() != null){
            return true;
        }
        return false;
    }

    public static void updateRssEntity(RssEntity rssEntity) {
        ofy().save().entity(rssEntity).now();
    }
}
