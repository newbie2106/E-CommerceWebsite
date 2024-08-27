<%-- 
    Document   : change-password
    Created on : Aug 27, 2024, 8:49:55 PM
    Author     : tongh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h2>Đổi Mật Khẩu</h2>

<!-- Hiển thị thông báo lỗi nếu có -->
<c:if test="${not empty errorMessage}">
    <div style="color: red;">
        <p>${errorMessage}</p>
    </div>
</c:if>

<!-- Hiển thị thông báo thành công nếu có -->
<c:if test="${not empty successMessage}">
    <div style="color: green;">
        <p>${successMessage}</p>
    </div>
</c:if>

<form:form method="post" action="${pageContext.request.contextPath}/change-password/${username}">
    <table>
        <tr>
            <td>Mật khẩu cũ:</td>
            <td><form:password path="oldPassword" /></td>
        </tr>
        <tr>
            <td>Mật khẩu mới:</td>
            <td><form:password path="newPassword" /></td>
        </tr>
        <tr>
            <td>Xác nhận mật khẩu mới:</td>
            <td><form:password path="confirmPassword" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Đổi Mật Khẩu" />
            </td>
        </tr>
    </table>
</form:form>