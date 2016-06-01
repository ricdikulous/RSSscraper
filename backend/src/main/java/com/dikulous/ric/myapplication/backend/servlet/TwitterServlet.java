package com.dikulous.ric.myapplication.backend.servlet;



import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import com.twitter.hbc.twitter4j.Twitter4jStatusClient;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import com.twitter.hbc.twitter4j.Twitter4jStatusClient;
import com.twitter.hbc.twitter4j.handler.StatusStreamHandler;
import com.twitter.hbc.twitter4j.message.DisconnectMessage;
import com.twitter.hbc.twitter4j.message.StallWarningMessage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;


/**
 * Created by ric on 10/05/16.
 */
public class TwitterServlet extends HttpServlet {

    // A bare bones listener
    private StatusListener listener1 = new StatusListener() {
        @Override
        public void onStatus(Status status) {}

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}

        @Override
        public void onTrackLimitationNotice(int limit) {}

        @Override
        public void onScrubGeo(long user, long upToStatus) {}

        @Override
        public void onStallWarning(StallWarning warning) {}

        @Override
        public void onException(Exception e) {}
    };

    // A bare bones StatusStreamHandler, which extends listener and gives some extra functionality
    private StatusListener listener2 = new StatusStreamHandler() {
        @Override
        public void onStatus(Status status) {}

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}

        @Override
        public void onTrackLimitationNotice(int limit) {}

        @Override
        public void onScrubGeo(long user, long upToStatus) {}

        @Override
        public void onStallWarning(StallWarning warning) {}

        @Override
        public void onException(Exception e) {}

        @Override
        public void onDisconnectMessage(DisconnectMessage message) {}

        @Override
        public void onStallWarningMessage(StallWarningMessage warning) {}

        @Override
        public void onUnknownMessageType(String s) {}
    };


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String consumerKey = "";
        String consumerSecret = "";
        String accessToken = "";
        String tokenSecret = "";

        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

        /** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        // Optional: set up some followings and track terms
        List<Long> followings = Lists.newArrayList();
        followings.add(1234L);
        followings.add(566788L);
        List<String> terms = Lists.newArrayList();
        terms.add("twitter");
        terms.add("api");
        hosebirdEndpoint.followings(followings);
        hosebirdEndpoint.trackTerms(terms);

        // These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1(consumerKey, consumerSecret, accessToken, tokenSecret);
        StatusesSampleEndpoint endpoint = new StatusesSampleEndpoint();


        // Create a new BasicClient. By default gzip is enabled.
        BasicClient client = new ClientBuilder()
                .hosts(Constants.STREAM_HOST)
                .endpoint(endpoint)
                .authentication(hosebirdAuth)
                .processor(new StringDelimitedProcessor(msgQueue))
                .build();

        // Create an executor service which will spawn threads to do the actual work of parsing the incoming messages and
        // calling the listeners on each message
        int numProcessingThreads = 1;
        ExecutorService service = Executors.newFixedThreadPool(numProcessingThreads);

        // Wrap our BasicClient with the twitter4j client
        Twitter4jStatusClient t4jClient = new Twitter4jStatusClient(
                client, msgQueue, Lists.newArrayList(listener1, listener2), service);

        // Establish a connection
        t4jClient.connect();
        for (int threads = 0; threads < numProcessingThreads; threads++) {
            // This must be called once per processing thread
            t4jClient.process();
        }

        client.stop();



        /*ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);                          // optional: use this if you want to process client events

        /*Twitter4jStatusClient t4jClient = new Twitter4jStatusClient(client, msgQueue, listeners, executorService);
        t4jClient.connect();

        Client hosebirdClient = builder.build();
        // Attempts to establish a connection.
        hosebirdClient.connect();
        int i=0;
        while (!hosebirdClient.isDone() && i<10) {
            String msg = null;
            try {
                msg = msgQueue.take();
                resp.getWriter().println(msg);
            } catch (InterruptedException e) {
                resp.getWriter().println("error");
                e.printStackTrace();
            }
            i++;
        }
        hosebirdClient.stop();

        resp.getWriter().print("here");*/
    }
}
