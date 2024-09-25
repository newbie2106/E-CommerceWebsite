<%-- 
    Document   : tagproducts
    Created on : Aug 17, 2024, 8:07:48 PM
    Author     : tongh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ROLE_SUPER_ADMIN')">
    <h1 class="text-center text-info mt-1">QUẢN LÝ NHÃN SẢN PHẨM</h1>

    <form:form method="post" action="${action}" modelAttribute="tagProduct">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />


        <div class="form-floating mb-3">
            <input type="hidden" id="username" name="username" value="${pageContext.request.userPrincipal.name}">
            <form:select class="form-select" id="productId"  path="productId" name="productId">
                <c:choose>
                    <c:when test="${empty tagProduct.id}">
                        <option value="">Chọn sản phẩm</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${b.id}">${tagProduct.productId.name}</option>
                    </c:otherwise>
                </c:choose>

                <c:forEach items="${products}" var="b">
                    <option value="${b.id}">${b.name}</option>
                </c:forEach>
            </form:select>
        </div>

        <div class="form-floating mb-3 mt-3">
            <form:select class="form-select" id="tagId"  path="tagId" name="tagId">
                <c:choose>
                    <c:when test="${empty tagProduct.id}">
                        <option value="">Chọn nhãn</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${b.id}">${tagProduct.tagId.tagName}</option>
                    </c:otherwise>
                </c:choose>
                <c:forEach items="${tags}" var="b">
                    <option value="${b.id}">${b.tagName}</option>
                </c:forEach>
                <label for="name">Nhãn:</label>
            </form:select>
        </div>
        <div class="form-floating">
            <button class="btn btn-info mt-1" type="submit">
                <c:choose>
                    <c:when test="${tagProduct.id > 0}"> Cập nhât nhãn sản phẩm</c:when>
                    <c:otherwise> Thêm nhãn sản phẩm</c:otherwise>
                </c:choose>
            </button>
            <form:hidden path="id" />
        </div>
    </form:form>

</sec:authorize>