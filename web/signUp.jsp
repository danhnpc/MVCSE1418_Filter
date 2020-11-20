<%-- 
    Document   : signUp
    Created on : Nov 1, 2020, 3:22:00 PM
    Author     : visug
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Sign Up Page</h1>
        <form action="createAccount" method="POST">
            <c:set var="errors" value="${requestScope.CREATEERROR}"/>
            Username* <input type="text" name="txtUserID" value="" /><br/>
            <c:if test="${not empty errors.usernameLengthErr}" >
                <font color="red"> 
                ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty errors.passwordLengthErr}" >
                <font color="red"> 
                ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmErr}" >
                <font color="red"> 
                ${errors.confirmErr}
                </font><br/>
            </c:if>
            Fullname* <input type="text" name="txtFullname" value="" /><br/>
            <c:if test="${not empty errors.fullnameLengthErr}" >
                <font color="red"> 
                ${errors.fullnameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" /><br/>

        </form>
        <c:if test="${not empty errors.usernameIsExisted}" >
            <font color="red"> 
            ${errors.usernameIsExisted}
            </font><br/>
        </c:if>
    </body>
</html>
