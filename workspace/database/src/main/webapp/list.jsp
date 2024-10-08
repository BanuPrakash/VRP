<%--
  Created by IntelliJ IDEA.
  User: banuprakash
  Date: 26/09/24
  Time: 12:46 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <STYLE>
        .tbl {
            border: 2px solid bisque;
        }
    </STYLE>
</head>
<body>
    <h1>Product List</h1>
    Welcome ${user} <br />
    <a href="logout">Logout</a>
    <table class="tbl">
            <thead>
            <tr>
                <th>ID</th> <th>Name</th><th>Price</th> <th>Delete</th>
            </tr>
            </thead>
        <tbody>
                <c:forEach items="${products}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.price}</td>
                        <td><button type="submit">Delete</button></td>
                    </tr>
                </c:forEach>
        </tbody>
    </table>

<a href="index.jsp">Back</a>
</body>
</html>
