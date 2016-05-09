<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="com.dikulous.ric.myapplication.backend.util.AccountsUtil"%>
<%@ page import="com.dikulous.ric.myapplication.backend.util.ChartHelper"%>
<%@ page import="com.dikulous.ric.myapplication.backend.datastore.KeyWordDatastore"%>
<%@ page import="com.dikulous.ric.myapplication.backend.model.KeyWordEntity"%>
<%@ page import="java.util.HashMap"%>

<%
    KeyWordEntity keyWordEntity = KeyWordDatastore.readById(request.getParameter("id"));
    String chartMap = ChartHelper.prepareDateCountForChart(keyWordEntity.getDateCount());
%>
<html>
    <head>
        <title>Chart</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
    </head>
    <body style="padding-top: 70px;">
    <t:navbar signInLink="<%=AccountsUtil.getSignInString(request.getRequestURI())%>"/>
        <div class="container">
            <div id="chart" style="width:100%; height:400px;"></div>
            <%= keyWordEntity.getDateCount()%>
        </div>
    </body>
    <script>

        var data = <%= chartMap%>
        var keyWord = "<%=keyWordEntity.getKeyWord()%>"
    </script>
    <script src="/js/chart.js"></script>
<html>