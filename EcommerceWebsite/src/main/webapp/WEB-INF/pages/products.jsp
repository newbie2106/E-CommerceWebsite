<%-- 
    Document   : products
    Created on : Jul 23, 2024, 8:50:00 PM
    Author     : tongh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="/products" var="action" />

<div class="col-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h1 class="text-center text-info mt-1">QUẢN LÝ SẢN PHẨM</h1>
            <form:form class="forms-sample" method="post" action="${action}" modelAttribute="product" enctype="multipart/form-data">
                <form:errors path="*" element="div" cssClass="alert alert-danger" />

                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control"  id="name"  placeholder="Tên sản phẩm" path="name" />
                    <label for="name">Tên sản phẩm</label>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control"  id="price"  placeholder="Giá" path="price" />
                    <label for="name">Giá sản phẩm</label>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control"  id="description"  placeholder="Mô tả sản phẩm" path="description" />
                    <label for="name">Mô tả sản phẩm</label>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">Ảnh sản phẩm</label>
                    <input type="file" class="form-control p-2 mt-2 w-100" id="image" name="image" accept=".png,.jpg" multiple>
                </div>
                <div class="form-floating">
                    <form:select class="form-select" id="categoryId"  path="categoryId">
                        <c:forEach items="${categories}" var="c">
                            <c:choose>
                                <c:when test="${c.id==product.categoryId.id}">
                                    <option value="${c.id}" selected>${c.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${c.id}">${c.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="categoryId" class="form-label">Danh mục:</label>
                </div>
                <div class="form-floating">
                    <form:select class="form-select" id="brandId"  path="brandId">
                        <c:forEach items="${brands}" var="b">
                            <c:choose>
                                <c:when test="${b.id==product.brandId.id}">
                                    <option value="${b.id}" selected>${b.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${b.id}">${b.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="brandId" class="form-label">Nhãn hàng:</label>
                </div>
                <div class="form-floating">
                    <button class="btn btn-info mt-1" type="submit">
                        <c:choose>
                            <c:when test="${product.id > 0}"> Cập nhât sản phẩm</c:when>
                            <c:otherwise> Thêm sản phẩm</c:otherwise>
                        </c:choose>
                    </button>
                    <form:hidden path="id" />
                </div>
            </form:form>
        </div>
    </div>
</div>





