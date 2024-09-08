<%-- 
    Document   : manage-products
    Created on : Jul 24, 2024, 9:58:15 PM
    Author     : tongh
--%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.tth.pojo.CustomUserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<h1 class="text-center text-info mt-1">QUẢN LÝ SẢN PHẨM</h1>


<ul class="pagination mt-1">
    <c:forEach begin="1" end="${count}" var="i">
        <c:url value="/manage-products" var="pageAction">
            <c:param name="page" value="${i}"/>
        </c:url>
        <li class="page-item">
            <a class="page-link" href="${pageAction}">${i}</a>
        </li>
    </c:forEach>
</ul>

<div>
</div>

<sec:authorize access="hasRole('ROLE_SUPER_ADMIN')">
    <div class="row">
        <div class="col-lg-6 grid-margin stretch-card">

            <form class="pt-3" method="POST" action="${pageContext.request.contextPath}/manage-products">
                <div class="form-group position-relative">
                    <select name="username" class="form-control fw-bold text-primary text-center">
                        <c:forEach items="${branches}" var="branch">
                            <option value="${branch.adminUser.username}" ${branch.adminUser.username == currentUsername ? 'selected' : ''}>
                                ${branch.adminUser.username}
                            </option>
                        </c:forEach>
                    </select>
                    <i class="fa-solid fa-caret-down position-absolute" 
                       style="top: 50%; right: 15px; transform: translateY(-50%); pointer-events: none;"></i>
                </div>
                <button type="submit" class="btn btn-primary">Lọc chi nhánh</button>
            </form> 
        </div>
    </div> 

    <div class="row">

        <div class="col-lg-12 grid-margin stretch-card">

            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Ảnh</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Số lượng trong kho</th>
                                    <th>Giá</th>
                                    <th>Mô tả</th>        
                                    <th>Thao tác</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${productsWithInventory}" var="row">
                                    <tr>
                                        <c:set var="p" value="${row[0]}" />
                                        <c:set var="q" value="${row[1]}" />

                                        <td>${p.id}</td>
                                        <td class="d-flex justify-content-center align-items-center gap-3">
                                            <c:if test="${not empty p.imageSet}">
                                                <c:forEach items="${p.imageSet}" var="i" varStatus="status">
                                                    <c:if test="${status.index == 0}">
                                                        <a href="${i.url}" data-toggle="lightbox" data-gallery="example-gallery">
                                                            <img src="${i.url}" width="150" height="100"/>
                                                        </a>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </td>
                                        <td>${p.name}</td>
                                        <td>${q}</td>
                                        <td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="VNĐ" maxFractionDigits="0" /></td>
                                        <td>${p.description}</td>
                                        <td>${quantity}</td> <!-- Hiển thị số lượng sản phẩm từ inventory -->
                                        <td>
                                            <c:url value="/api/products/${p.id}/" var="urlDelete" />
                                            <a class="btn btn-info" href="<c:url value="/products/${p.id}" />">Cập nhật</a>
                                            <sec:authorize access="hasRole('ROLE_SUPER_ADMIN')">
                                                <button onclick="eDelete('${urlDelete}',${p.id})" class="btn btn-danger">Xóa</button>
                                            </sec:authorize>
                                        </td>
                                    </tr>
                                </c:forEach>
                            <a class="btn btn-success" href="<c:url value="/products" />">Thêm sản phẩm</a>

                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="row">

        <div class="col-lg-12 grid-margin stretch-card">

            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Ảnh</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Số lượng trong kho</th>
                                    <th>Giá</th>
                                    <th>Mô tả</th>        
                                    <th>Thao tác</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${productsWithInventory}" var="row">
                                    <tr>
                                        <c:set var="p" value="${row[0]}" />
                                        <c:set var="q" value="${row[1]}" />

                                        <td>${p.id}</td>
                                        <td class="d-flex justify-content-center align-items-center gap-3">
                                            <c:if test="${not empty p.imageSet}">
                                                <c:forEach items="${p.imageSet}" var="i" varStatus="status">
                                                    <c:if test="${status.index == 0}">
                                                        <a href="${i.url}" data-toggle="lightbox" data-gallery="example-gallery">
                                                            <img src="${i.url}" width="150" height="100"/>
                                                        </a>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </td>
                                        <td>${p.name}</td>
                                        <td>${q}</td>
                                        <td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="VNĐ" maxFractionDigits="0" /></td>
                                        <td>${p.description}</td>
                                        <td>${quantity}</td> <!-- Hiển thị số lượng sản phẩm từ inventory -->
                                        <td>
                                            <c:url value="/api/products/${p.id}/" var="urlDelete" />
                                            <a class="btn btn-info" href="<c:url value="/products/${p.id}" />">Cập nhật</a>
                                            <sec:authorize access="hasRole('ROLE_SUPER_ADMIN')">
                                                <button onclick="eDelete('${urlDelete}',${p.id})" class="btn btn-danger">Xóa</button>
                                            </sec:authorize>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</sec:authorize>


