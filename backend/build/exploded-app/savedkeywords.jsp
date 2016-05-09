<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="com.dikulous.ric.myapplication.backend.util.AccountsUtil"%>
<%@ page import="com.dikulous.ric.myapplication.backend.datastore.KeyWordDatastore"%>
<%@ page import="com.dikulous.ric.myapplication.backend.model.KeyWordEntity"%>
<%@ page import="java.util.List" %>

<%
    List<KeyWordEntity> keyWords = KeyWordDatastore.readAllKeyWordEntities();
%>


<html>
    <head>
        <title>Key Words</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
        <script type="text/javascript" src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
    </head>
    <body style="padding-top: 70px;">
        <t:navbar signInLink="<%=AccountsUtil.getSignInString(request.getRequestURI())%>"/>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <table id="keyWordTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>Key Word</th>
                                <th>Category</th>
                                <th>Tone</th>
                                <th>Chart</th>
                            </tr>
                        </thead>
                        <tbody>
                        <% for(KeyWordEntity keyWord:keyWords){ %>
                            <tr>
                                <td><%=keyWord.getKeyWord()%></td>
                                <td><%=keyWord.getCategory()%></td>
                                <td><%=keyWord.getTone()%></td>
                                <td><a href="/chart.jsp?id=<%=keyWord.getId()%>">Chart</a></td>
                            </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="/js/savedKeyWords.js"></script>
    </body>
</html>