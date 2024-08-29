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

<form:form modelAttribute="changePassword" method="post" action="${pageContext.request.contextPath}/change-password/${username}">

    <div class="col-lg-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">ĐỔI MẬT KHẨU</h4>
                </p>
                <table class="table table-bordered">

                    <tbody>
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
                            <td><form:password path="rePassword" /></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit" class="btn btn-gradient-success btn-fw">Xác nhận</button>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form:form>