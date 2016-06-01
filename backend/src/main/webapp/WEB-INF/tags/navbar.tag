<%@tag description="Nav bar template" pageEncoding="UTF-8"%>
<%@attribute name="signInLink" required="true"%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index.jsp">Commodity Scraper</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/input.jsp" class="navbar-link">Add RSS feeds</a></li>
                <li><a href="/inputkeywords.jsp" class="navbar-link">Add Keywords</a></li>
                <li><a href="/savedfeeds.jsp" class="navbar-link">Show all RSS feed</a></li>
                <li><a href="/savedkeywords.jsp" class="navbar-link">Show all keywords</a></li>
                <li><a href="/about.jsp" class="navbar-link">About</a></li>
            </ul>
            <p class="navbar-text navbar-right">${signInLink}</p>
        </div>
    </div>
</div>