package com.dikulous.ric.myapplication.backend.servlet;

import com.dikulous.ric.myapplication.backend.datastore.RssDatastore;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.repackaged.com.google.api.services.datastore.client.Datastore;
import com.google.appengine.repackaged.com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ric on 7/05/16.
 */
public class InputSshServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String url = req.getParameter("url");
        String country = req.getParameter("country");
        String category = req.getParameter("category");
        System.out.println(category);
        Boolean success = RssDatastore.insertRss(url, country, category);
        resp.sendRedirect("/input.jsp?success="+success);
    }
}
