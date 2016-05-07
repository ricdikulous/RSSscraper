package com.dikulous.ric.myapplication.backend.servlet;

import com.dikulous.ric.myapplication.backend.datastore.KeyWordDatastore;
import com.dikulous.ric.myapplication.backend.datastore.RssDatastore;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ric on 7/05/16.
 */
public class InputKeywordServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String keyWord = req.getParameter("keyWord");
        if(keyWord != null && !keyWord.equals("")){
            keyWord = keyWord.toLowerCase().trim();
            String category = req.getParameter("category");
            String tone = req.getParameter("tone");

            Boolean success = KeyWordDatastore.insertKeyWord(keyWord, category, tone);
            resp.sendRedirect("/inputkeywords.jsp?success="+success);
        } else {
            resp.sendRedirect("/inputkeywords.jsp?success=false");
        }
    }
}
