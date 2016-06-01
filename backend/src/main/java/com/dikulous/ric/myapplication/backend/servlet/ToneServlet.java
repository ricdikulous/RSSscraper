package com.dikulous.ric.myapplication.backend.servlet;



import com.ibm.watson.developer_cloud.tone_analyzer.v3_beta.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3_beta.model.ToneAnalysis;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ric on 11/05/16.
 */
public class ToneServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String username = "0242b0bc-146b-419d-9827-0f471a4bf0fb";
        String password = "E2YRuvsBBDnp";
        String url = "https://gateway.watsonplatform.net/tone-analyzer-beta/api";
        ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_02_11);
        service.setUsernameAndPassword(username, password);


        String text =
                "A word is dead when it is said, some say. Emily Dickinson";

        // Call the service and get the tone
        ToneAnalysis tone = service.getTone(text).execute();
        System.out.println(tone);
        resp.getWriter().print(tone);
    }
}
