package com.dikulous.ric.myapplication.backend.datastore;

import com.dikulous.ric.myapplication.backend.model.KeyWordEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.ObjectifyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by ric on 7/05/16.
 */
public class KeyWordDatastore {

    static {
        ObjectifyService.register(KeyWordEntity.class);
    }

    public static Boolean insertKeyWord(String keyWord, String category, String tone){
        Boolean success = false;
        if(!doesKeyWordExist(keyWord)) {
            KeyWordEntity keyWordEntity = new KeyWordEntity();
            keyWordEntity.setKeyWord(keyWord);
            keyWordEntity.setCategory(category);
            keyWordEntity.setTone(tone);
            keyWordEntity.setCreatedAt(new Date().getTime());
            keyWordEntity.setDateCount(new HashMap<String, Long>());
            ofy().save().entity(keyWordEntity).now();
            success = true;
        } else {
            System.out.println("Already exists or is not valid");
        }
        return success;
    }

    public static List<String> readAllKeyWords(){
        List<String> keyWords = new ArrayList<>();
        for(KeyWordEntity entity:ofy().load().type(KeyWordEntity.class).project("keyWord").list()){
            keyWords.add(entity.getKeyWord());
        }
        return keyWords;
    }

    public static List<KeyWordEntity> readAllKeyWordEntities(){
        return ofy().load().type(KeyWordEntity.class).list();
    }

    public static boolean doesKeyWordExist(String keyWord){
        if(ofy().load().type(KeyWordEntity.class).filter("keyWord", keyWord).first().now() != null){
            return true;
        } else {
            return false;
        }

    }

    public static void updateKeyWordEntity(KeyWordEntity keyWordEntity) {
        ofy().save().entity(keyWordEntity).now();
    }
}
