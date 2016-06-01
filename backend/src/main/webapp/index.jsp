<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="com.dikulous.ric.myapplication.backend.util.AccountsUtil"%>


<html>
    <head>
        <title>Commodity Scraper</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body style="padding-top: 70px;">
    <t:navbar signInLink="<%=AccountsUtil.getSignInString(request.getRequestURI())%>"/>
        <div class="container theme-showcase" role="main">
            <div class="jumbotron">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>What it does</h2>
                        <p>
                        The application serves the general purposes of scraping RSS feeds from new websites
                        for key words to record basic data about how many times each keyword was read on each
                        day. The use of the application neither indicates or recommends to buy or sell, it
                        is merely a platform to aid research and further investigation.
                        </p>
                        <p>
                        The RSS feeds are polled using a chronological job every 10 minutes. An exponential back off algorithm
                        is used to avoid unnecessary toll on resources that the polls are subject to. Maximum back off being 1 week,
                        minimum is 5 minutes. Keywords and RSS feeds can be added by anyone, they will be subject to a very simple
                        uniqueness check it will also check that the feed is valid.
                        </p>
                        <p>
                        The use of the large groups of targeted key words and RSS feeds leverage the
                        system above to be compared with financial metrics to perform market analysis.
                        Also paying attention to the relationship and correlation of these keywords for further analysis of possible
                        trends by collecting the data and displaying it in a graph which can be accessed next to the key word.
                        </p>
                        <p>
                        All the keywords with a link to their stats can be viewed <a href="/savedkeywords.jsp">here</a><br>
                        All the RSS feeds can be viewed <a href="/savedfeeds.jsp">here</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>