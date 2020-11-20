<%-- 
    Document   : shopping
    Created on : Nov 5, 2020, 3:20:37 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        <h1>Shopping Cart</h1><a href="try"><-Back to Login</a>
        <form action="cart">
            Choose your item <select name="itemList">
                <c:set var="items" value="${sessionScope.ITEMLIST}" />
                <c:if test="${not empty items}" >
                    <c:forEach var="dto" items="${items}" >
                        <option>${dto}</option>
                    </c:forEach>
                </c:if>
            </select><br/>
            <input type="submit" value="Add Item To Cart" name="btAction" />
            <input type="submit" value="View Your Cart" name="btAction" />
        </form>
    </body>
</html>
