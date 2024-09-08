<%-- 
    Document   : categories
    Created on : Jul 25, 2024, 3:03:22 PM
    Author     : tongh
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ DANH MỤC</h1>

<c:url value="/categories" var="action" />
<form:form method="post" action="${action}" modelAttribute="category">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <div class="mt-1">
            <label for="name">Tên danh mục</label>
            <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên danh mục" name="name" />
        </div>
        <div class="mt-1">
            <label for="name">Mô tả</label>
            <form:input type="text" class="form-control" path="description" id="description" placeholder="Mô tả" name="description" />
        </div>

        <div class="form-floating">
            <button class="btn btn-info mt-1" type="submit">
                <c:choose>
                    <c:when test="${category.id > 0}"> Cập nhât danh mục</c:when>
                    <c:otherwise> Thêm danh mục</c:otherwise>
                </c:choose>
            </button>
            <form:hidden path="id" />
        </div>
    </div>
</form:form>
