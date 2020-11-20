<%-- 
    Document   : search
    Created on : Oct 9, 2020, 11:43:16 PM
    Author     : visug
--%>

<%@page import="danhnpc.tblusers.TblUsersDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Search Result</title>
    </head>
    <body>
        <c:set var="att" value="${sessionScope.USERNAME}" />
        <c:if test="${empty att}" >
            <h2>You need to Login before search</h2>
            <a href="try">Click here to Login</a>
        </c:if>
            
        <c:if test="${not empty att}" >
        <form action="search">
            Welcome,
            <font color="red" >
            ${sessionScope.USERNAME}
            </font>
            <a href="logOut">Log Out</a>
            <h1>Search Page</h1>

            Search full name <input type="text" name="txtSearchValue" 
                                    value="${param.txtSearchValue}" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}" >
            <c:set var="result" value="${requestScope.SEARCHRESULT}" />
            <c:if test="${not empty result}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Status</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="update">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.userID}
                                    <input type="hidden" name="txtUserID" value="${dto.userID}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ADMIN" 
                                           <c:if test="${dto.status}">
                                               checked ="checked"
                                           </c:if>
                                           />
                                </td>
                                <c:url var="urlRewriting" value="deleteAccount">
                                    <c:param name="btAction" value="delete" />
                                    <c:param name="pk" value="${dto.userID}" />
                                    <c:param name="lastSearchValue" value="${searchValue}" />
                                </c:url>
                                <td>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>No record is matched</h2>
        </c:if>

    </c:if>

        </c:if>
     </body>   
</html>
