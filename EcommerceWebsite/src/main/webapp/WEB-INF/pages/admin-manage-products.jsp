<%-- 
    Document   : admin-manage-products
    Created on : Sep 16, 2024, 8:03:59 PM
    Author     : tongh
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.tth.pojo.CustomUserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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
                                <th>Giá</th>
                                <th>Mô tả</th>        
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${products}" var="p">
                                <tr>
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
                                    <td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="VNĐ" maxFractionDigits="0" /></td>
                                    <td>${p.description}</td>
                                    <td>${quantity}</td> <!-- Hiển thị số lượng sản phẩm từ inventory -->
                                    <td>
                                        <c:url value="/api/products/${p.id}/" var="urlDelete" />
                                        <a class="btn btn-info" href="<c:url value="/products/${p.id}" />">Cập nhật</a>
                                        <button onclick="eDelete('${urlDelete}',${p.id})" class="btn btn-danger">Xóa</button>
                                    </td>
                                </tr>
                            </c:forEach>

                        <div class="col-lg-6 grid-margin stretch-card">
                            <a class="btn btn-success" href="<c:url value="/products" />">Thêm sản phẩm</a>

                        </div>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>