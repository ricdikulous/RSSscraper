package com.dikulous.ric.myapplication.backend.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dikulous.ric.myapplication.backend.datastore.KeyWordDatastore;
import com.dikulous.ric.myapplication.backend.datastore.RssDatastore;
import com.dikulous.ric.myapplication.backend.model.KeyWordEntity;
import com.dikulous.ric.myapplication.backend.model.RssEntity;
import com.dikulous.ric.myapplication.backend.util.Globals;
import com.google.appengine.repackaged.com.google.api.client.util.DateTime;
import com.ibm.watson.developer_cloud.tone_analyzer.v1.ToneAnalyzer;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

/**
 * Created by ric on 5/05/16.
 */
public class BasicReaderServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Same as before but the key words are broken up into dates that they are mentioned on");
        //ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_02_11);
        //service.setUsernameAndPassword("", "");
        //String[] urls = {"http://www.smh.com.au/rssheadlines/business/article/rss.xml","http://www.economist.com/sections/economics/rss.xml","http://www.economist.com/sections/business-finance/rss.xml", "http://www.ft.com/rss/companies/mining", "http://www.itnews.com.au/RSS/rss.ashx?type=Category&ID=37",
        //"http://www.abc.net.au/news/feed/51892/rss.xml", "http://www.abc.net.au/news/feed/51120/rss.xml", "http://rfs.oxfordjournals.org/rss/current.xml", "http://rss.nytimes.com/services/xml/rss/nyt/InternationalHome.xml", "http://feeds.feedburner.com/AICPA_FinancialReportingCenter", "http://feeds.feedburner.com/AICPA_BusinessIndustryGovt", "http://feeds.feedburner.com/AICPA_Tax", "http://feeds.feedburner.com/AICPA_Newsroom", "http://www.journalofaccountancy.com/news.xml"};

        List<RssEntity> rssEntities = RssDatastore.readScheduledRssEntities();
        List<KeyWordEntity> keyWordEntities = KeyWordDatastore.readAllKeyWordEntities();
        int headlines = 0;
        boolean atLeastOneNewHeadline;
        boolean allNewHeadlines;
        try {
            //URL feedUrl = new URL("http://finance.yahoo.com/rss/headline?s=PEP");
            for(RssEntity rssEntity:rssEntities) {
                URL feedUrl = new URL(rssEntity.getUrl());

                if(rssEntity.getLastRead() == null){
                    rssEntity.setLastRead(0L);
                }

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));
                atLeastOneNewHeadline = false;
                allNewHeadlines = true;
                for (SyndEntry syndEntry : feed.getEntries()) {
                    if(syndEntry.getPublishedDate() != null && syndEntry.getPublishedDate().getTime() > rssEntity.getLastRead()
                            && syndEntry.getPublishedDate().getTime() < new Date().getTime()) {
                        headlines++;
                        resp.getWriter().println(syndEntry.getTitle());
                        resp.getWriter().println(syndEntry.getPublishedDate());
                        atLeastOneNewHeadline = true;
                        DateTime date = new DateTime(syndEntry.getPublishedDate());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String simpleDate = simpleDateFormat.format(date.getValue());
                        for (KeyWordEntity keyWordEntity : keyWordEntities) {
                            if (syndEntry.getTitle().toLowerCase().contains(keyWordEntity.getKeyWord())) {
                                if (keyWordEntity.getDateCount() == null) {
                                    keyWordEntity.setDateCount(new HashMap<String, Long>());
                                }
                                if (keyWordEntity.getDateCount().get(simpleDate) == null) {
                                    keyWordEntity.getDateCount().put(simpleDate, 1L);
                                } else {
                                    keyWordEntity.getDateCount().put(simpleDate, keyWordEntity.getDateCount().get(simpleDate) + 1);
                                }
                            }
                        }
                    } else {
                        allNewHeadlines = false;
                    }
                }
                if(allNewHeadlines && atLeastOneNewHeadline){
                    //inverse backoff
                    rssEntity.setReadFrequency(Math.max(rssEntity.getReadFrequency() / 2, Globals.MINIMUN_RSS_BACKOFF));
                } else if(!atLeastOneNewHeadline){
                    //backoff
                    rssEntity.setReadFrequency(Math.min(rssEntity.getReadFrequency()*2, Globals.MAXIMUM_RSS_BACKOFF));
                }
                Long now = new Date().getTime();
                rssEntity.setScheduledRead(now+rssEntity.getReadFrequency());
                rssEntity.setLastRead(now);
                RssDatastore.updateRssEntity(rssEntity);
            }
            resp.getWriter().println("From "+rssEntities.size()+" rss feeds and "+headlines+" headlines the following key words were mentioned this many times");
            resp.getWriter().println("");
            for(KeyWordEntity keyWordEntity:keyWordEntities){
                //save updated values
                KeyWordDatastore.updateKeyWordEntity(keyWordEntity);
                resp.getWriter().print(keyWordEntity.getKeyWord()+": ");
                resp.getWriter().println(keyWordEntity.getDateCount());
            }
            //resp.getWriter().println(totals);

        }
        catch (Exception ex) {
            resp.getWriter().println(ex.toString());
            resp.getWriter().print("Error");
        }
    }
}
