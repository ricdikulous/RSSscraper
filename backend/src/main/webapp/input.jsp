<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <head>
        <title>Add Item to Menu</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body style="padding-top: 70px;">
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
                   <form action="/inputssh" method="post" class="form-horizontal">
                       <div class="form-group">
                           <label for="urlInput">Enter RSS url</label>
                           <input id="urlInput" type="text" required name="url" class="form-control">
                       </div>
                       <div class="form-group">
                           <label for="countryInput">Country</label>
                           <select id="countryInput" name="country" class="form-control">
                                <option value="US">United States</option>
                                <option value="CA">Canada</option>
                                <option value="GB">United Kingdom</option>
                                <option value="AU">Australia</option>
                                <option value="other">Other</option>
                           </select>
                       </div>
                       <div class="form-group">
                          <label for="categoryInput">Category</label>
                          <select id="categoryInput" name="category" class="form-control">
                                <option value="headlines">Headlines</option>
                                <option value="business">Business</option>
                                <option value="finance">Finance</option>
                                <option value="politics">Politics</option>
                            </select>
                        </div>
                       <input type="submit" value="Submit" class="btn btn-default">
                   </form>
                </div>
            </div>
        </div>
    </body>
</html>