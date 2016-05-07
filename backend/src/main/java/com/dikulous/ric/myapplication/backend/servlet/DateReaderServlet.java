package com.dikulous.ric.myapplication.backend.servlet;

import com.google.appengine.repackaged.com.google.api.client.util.DateTime;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ric on 7/05/16.
 */
public class DateReaderServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Same as before but the key words are broken up into dates that they are mentioned on");
        String[] urls = {"http://www.smh.com.au/rssheadlines/business/article/rss.xml","http://www.economist.com/sections/economics/rss.xml","http://www.economist.com/sections/business-finance/rss.xml", "http://www.ft.com/rss/companies/mining", "http://www.itnews.com.au/RSS/rss.ashx?type=Category&ID=37",
                "http://www.abc.net.au/news/feed/51892/rss.xml", "http://www.abc.net.au/news/feed/51120/rss.xml", "http://rfs.oxfordjournals.org/rss/current.xml", "http://rss.nytimes.com/services/xml/rss/nyt/InternationalHome.xml", "http://feeds.feedburner.com/AICPA_FinancialReportingCenter", "http://feeds.feedburner.com/AICPA_BusinessIndustryGovt", "http://feeds.feedburner.com/AICPA_Tax", "http://feeds.feedburner.com/AICPA_Newsroom", "http://www.journalofaccountancy.com/news.xml"};


        try {
            //URL feedUrl = new URL("http://finance.yahoo.com/rss/headline?s=PEP");
            for(String url:urls) {
                URL feedUrl = new URL(url);

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));

                //resp.getWriter().println(feed.getTitleEx().toString());
                for (SyndEntry syndEntry : feed.getEntries()) {
                    DateTime date = new DateTime(syndEntry.getPublishedDate());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
                    resp.getWriter().println(syndEntry.getPublishedDate());
                    /*resp.getWriter().println(date.toString());
                    resp.getWriter().println(simpleDateFormat.format(date.getValue()));
                    resp.getWriter().println(date.toStringRfc3339());*/
                    String simpleDate = simpleDateFormat.format(date.getValue());
                    //resp.getWriter().println(syndEntry.getTitle().toLowerCase().contains("profit"));
                    if(date.getValue()>1462340063000L) {
                        resp.getWriter().println(syndEntry.getTitle());
                    }
                }
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
            resp.getWriter().print("Error");
        }
    }
}
