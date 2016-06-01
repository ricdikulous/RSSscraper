package com.dikulous.ric.myapplication.backend.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by ric on 15/05/16.
 */
public class TwitterSearchServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String consumerKey = "";
        String consumerSecret = "";
        String accessToken = "";
        String tokenSecret = "";

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(tokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());

        Twitter twitter = tf.getInstance();
        //StanfordSentiment sentimentAnalyzer = new StanfordSentiment();
        try {
            Query query = new Query(req.getParameter("arg"));
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    //resp.getWriter().println(sentimentAnalyzer.getSentiment(tweet.getText()));
                    System.out.println(tweet.getCreatedAt().toString()+"@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    resp.getWriter().println(tweet.getCreatedAt().toString()+"@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
    }
}
