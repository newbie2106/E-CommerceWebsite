<%-- 
    Document   : brands
    Created on : Jul 25, 2024, 3:03:25 PM
    Author     : tongh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ NHÃN HÀNG</h1>

<c:url value="/brands" var="action" />
<form:form method="post" action="${action}" modelAttribute="brand" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <div class="mt-1">
            <label for="name">Tên nhãn hàng</label>
            <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên nhãn hàng" name="name" />
        </div>
        <div class="form-floating mb-3 mt-3">
            <div class="mt-1">
                <label for="file">Ảnh logo thương hiệu</label>
                <form:input type="file" accept=".png,.jpg" class="form-control" path="file" id="file" name="file" />
                <c:if test="${brand.logo != null}">
                    <img src="${brand.logo}" width="200" />
                </c:if>
            </div>

        </div>
        <div class="form-floating">
            <button class="btn btn-info mt-1" type="submit">
                <c:choose>
                    <c:when test="${brand.id > 0}"> Cập nhât nhãn hàng</c:when>
                    <c:otherwise> Thêm nhãn hàng</c:otherwise>
                </c:choose>
            </button>
            <form:hidden path="id" />
        </div>
    </div>
</form:form>

