<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="com.dikulous.ric.myapplication.backend.util.AccountsUtil"%>



<html>
    <head>
        <title>Add Item to Menu</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body style="padding-top: 70px;">
    <t:navbar signInLink="<%=AccountsUtil.getSignInString(request.getRequestURI())%>"/>
        <div class="container">
            <%
                if(request.getParameter("success") != null){
            %>
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">

                <%
                    if(request.getParameter("success").equals("true")){
                %>
                    Successfully saved baby
                <%
                    } else {
                %>
                    Fucked up save because either the URL didn't work or it already exists
                <%
                    }
                %>
                </div>
            </div>
            <%
                }
            %>

            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                   <form action="/inputkeyword" method="post" class="form-horizontal">
                       <div class="form-group">
                           <label for="keyWordInput">Enter Key Word</label>
                           <input id="keyWordInput" type="text" required name="keyWord" required class="form-control">
                       </div>
                       <div class="form-group">
                          <label for="categoryInput">Category</label>
                          <select id="categoryInput" name="category" class="form-control">
                              <option value="commodity">Commodity</option>
                              <option value="modifier">Modifier</option>
                              <option value="business">Business</option>
                              <option value="economic">Economic</option>
                              <option value="finance">Finance</option>
                              <option value="politics">Politics</option>
                          </select>
                       </div>
                       <div class="form-group">
                           <label for="toneInput">Tone</label>
                           <select id="toneInput" name="tone" class="form-control">
                                <option value="neutral">Neutral</option>
                                <option value="positive">Positive</option>
                                <option value="negative">Negative</option>
                           </select>
                       </div>
                       <input type="submit" value="Submit" class="btn btn-default">
                   </form>
                </div>
            </div>
        </div>
    </body>
</html>