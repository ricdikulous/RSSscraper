<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dikulous.ric.myapplication.backend.datastore.KeyWordDatastore"%>
<%@ page import="java.util.List" %>

<%
    List<String> keyWords = KeyWordDatastore.readAllKeyWords();
%>


<html>
    <head>
        <title>Key Words</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body style="padding-top: 70px;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <%
                        for(String keyWord:keyWords){
                    %>
                        <%=keyWord%><br>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>