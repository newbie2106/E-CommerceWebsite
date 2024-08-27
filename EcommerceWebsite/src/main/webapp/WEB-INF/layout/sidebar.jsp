<%-- 
    Document   : sidebar
    Created on : Aug 24, 2024, 9:51:58 PM
    Author     : tongh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">

        <nav class="sidebar sidebar-offcanvas" id="sidebar">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/manage-products' />">Quản lý sản phẩm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/manage-brands' />">Quản lý nhãn hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/manage-categories' />">Quản lý danh mục</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/manage-users' />">Quản lý tài khoản</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/manage-tags' />">Quản lý nhãn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/stats' />">Thống kê</a>
                </li>
            </ul>
        </nav>
    </c:when>
    <c:otherwise>
        <nav class="sidebar sidebar-offcanvas" id="sidebar"></nav>
        </c:otherwise>
    </c:choose>

