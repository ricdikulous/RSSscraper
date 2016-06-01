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
        <div class="container">
            <div class="row">
                <div class="col-lg-10">
                    <h3>Our Objective</h3>
                    <p>
                        The idea was conceived by two university students with backgrounds in computer science and finance
                        as a fun and simple way to scrape twitter to display trending key words to aid research in ideas of
                        behavioural finance.
                    </p>
                    <h3>Our Approach</h3>
                    <p>
                        To serve the community by having a transparent research application that lets you customize and search
                        key words of your choice in order to see related trends. Additionally we are constantly innovating the
                        program and continuously work on developing new, valuable ideas that make research more efficient and
                        accurate.
                        Currently we are capturing data from Twitter and a number of RSS feeds. Both of which are hosted
                        on separate wesbites<br>
                        <a href="http://pythonscraper-dikulous.rhcloud.com">Twitter scraper</a><br>
                        <a href="http://commodityscraper.appspot.com">RSS scraper</a>
                    </p>
                    <h3>Why Two Websites?</h3>
                    <p>
                        The technical limitations of the first server we deployed on did not allow us to keep an open connection
                        to the Twitter stream so we decided to build a second website with a different host. Also a second
                        website in a different programming language allows us access to many more libraries for experimentation.
                    </p>
                    <h3>Our Team</h3>
                    <p><b>Jerome Collins - Founder</b><br>
                        Jerome holds an Honours Bachelors degree in commerce and a specialization in Finance and Economics. Interests
                        include an intrigue of the behavioural aspects and implications of the stock market and is always looking
                        for new ways to beat the market.
                    </p>
                    <p><b>Richard Watts-Seale - Founder</b><br>
                        Richard holds a Bachelors degree in Computer Science and has an deep intrigue of computer systems as well
                        as an impeccable set of technical skills that has been the backbone of the creation of the project.
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>