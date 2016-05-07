package com.dikulous.ric.myapplication.backend.util;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;

/**
 * Created by ric on 7/05/16.
 */
public class RssHelper {

    public static Boolean isUrlValid(String url){
        try {
            //URL feedUrl = new URL("http://finance.yahoo.com/rss/headline?s=PEP");
                URL feedUrl = new URL(url);

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));
                return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
