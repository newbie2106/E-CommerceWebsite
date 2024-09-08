<%-- 
    Document   : header
    Created on : Apr 25, 2024, 7:38:26 PM
    Author     : tongh
--%>

<%@page import="com.tth.pojo.CustomUserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<nav class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
    <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-start">
        <a class="navbar-brand brand-logo" href="<c:url value="/dashboard" />"><img src="assets/images/logo-ngang.png" ></a>
        <a class="navbar-brand brand-logo-mini" href="<c:url value="/dashboard" />"><img src="assets/images/logo.png"></a>
    </div>
    <div class="navbar-menu-wrapper d-flex align-items-stretch">
        <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
            <span class="mdi mdi-menu"></span>
        </button>
        <div class="search-field d-none d-md-block">
            <form class="d-flex align-items-center h-100" action="#">
                <div class="input-group">
                    <div class="input-group-prepend bg-transparent">
                        <i class="input-group-text border-0 mdi mdi-magnify"></i>
                    </div>
                    <input type="text" class="form-control bg-transparent border-0" placeholder="Search projects">
                </div>
            </form>
        </div>
        <ul class="navbar-nav navbar-nav-right">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <li class="nav-item nav-profile dropdown">

                        <a class="nav-link dropdown-toggle show" id="profileDropdown" href="#" data-bs-toggle="dropdown" aria-expanded="false">
                            <div class="d-flex align-items-center">
                                <div class="nav-profile-img">
                                    <img src="<sec:authentication property="principal.avatar"/>" alt="image">
                                    <span class="availability-status online"></span>
                                </div>
                                    <div class="nav-profile-text ms-2" style="margin-top: 20px">
                                    <p class="mb-1 text-black"><sec:authentication property="principal.firstName"/> <sec:authentication property="principal.lastName"/></p>
                                    <p class="text-black text-muted small">
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            ADMIN
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ROLE_SUPER_ADMIN')">
                                            SUPER_ADMIN
                                        </sec:authorize>
                                    </p>
                                </div>
                            </div>
                        </a>
                        <div class="dropdown-menu navbar-dropdown" aria-labelledby="profileDropdown">
                            <a class="dropdown-item" href="<c:url value="/update-user/${pageContext.request.userPrincipal.name}" />">
                                <i class="mdi mdi-cached me-2 text-success"></i> Thay đổi thông tin </a>
                            <a class="dropdown-item" href="<c:url value="/change-password" />">
                                <i class="mdi mdi-cached me-2 text-success"></i> Thay đổi mật khẩu </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<c:url value="/logout"/>">
                                <i class="mdi mdi-logout me-2 text-primary"></i> Đăng xuất </a>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <h4><a class="nav-link text-danger" href="<c:url value="/login" />">Đăng nhập</a></h4>
                    </li>
                </c:otherwise>
            </c:choose>

            <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
                <span class="mdi mdi-menu"></span>
            </button>
            <li class="nav-item nav-settings d-none d-lg-block">
                <a class="nav-link" href="#">
                    <i class="mdi mdi-format-line-spacing"></i>
                </a>
            </li>
        </ul>
    </div>
</nav>