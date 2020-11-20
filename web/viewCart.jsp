<%-- 
    Document   : viewCart
    Created on : Oct 30, 2020, 10:37:26 PM
    Author     : visug
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>Your Items Cart</h1>
        <c:set var="cart" value="${sessionScope.CUSTCART}"/>
        <c:if test="${not empty cart}" >
            <c:set var="result" value="${cart.items}"/>
            <c:if test="${not empty result}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Item</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="removeItem">

                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.key}
                                </td>
                                <td>
                                    ${dto.value}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkRemove" value="${dto.key}" />
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3">
                                <a href="shopping">Add more item cart</a>
                            </td>
                            <td>
                                <input type="submit" value="Remove select item" name="btAction" />
                            </td>
                        </tr>
                        <c:url var="urlRewriting" value="checkOut">
                            <%--<c:param name="btAction" value="Check Out" />--%>
                        </c:url>
                        <tr>
                            <td colspan="4">
                                <a href="${urlRewriting}">Check out</a>
                            </td>
                        </tr>

                    </form>

                </tbody>
            </table>

        </c:if>
    </c:if>    

    <c:if test="${empty result}">
        <h2>No record is matched!</h2>
        <a href="shopping">Continue shopping</a>
    </c:if>

</body>
</html>
