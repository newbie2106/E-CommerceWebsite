<%-- 
    Document   : tags
    Created on : Aug 17, 2024, 7:02:53 PM
    Author     : tongh
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ DANH MỤC</h1>

<c:url value="/tags" var="action" />
<form:form method="post" action="${action}" modelAttribute="tag">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <div class="mt-1">
            <label for="name">Tên nhãn</label>
            <form:input type="text" class="form-control" path="tagName" id="tagName" placeholder="Tên Tag" name="tagName" />
        </div>
        
        <div class="form-floating">
            <button class="btn btn-info mt-1" type="submit">
                <c:choose>
                    <c:when test="${tag.id > 0}"> Cập nhât nhãn</c:when>
                    <c:otherwise> Thêm nhãn</c:otherwise>
                </c:choose>
            </button>
            <form:hidden path="id" />
        </div>
    </div>
</form:form>
