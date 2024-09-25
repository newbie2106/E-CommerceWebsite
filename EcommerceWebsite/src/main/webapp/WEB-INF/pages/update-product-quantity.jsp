<%-- 
    Document   : update-quantity-product
    Created on : Sep 14, 2024, 9:09:41 PM
    Author     : tongh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:url value="/update-product-quantity" var="action" />

<div class="col-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h1 class="text-center text-info mt-1">QUẢN LÝ CẬP NHẬT SỐ LƯỢNG SẢN PHẨM</h1>
            <form class="forms-sample" method="post" action="${action}" modelAttribute="inventory">

                <input type="hidden" name="productId" value="${product.id}">
                <div class="form-floating mb-3 mt-3">
                    <p class="form-control" id="productName" name="productName"placeholder="Tên sản phẩm">${product.name}</p> 
                    <label for="name">Tên sản phẩm</label>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <p class="form-control" id="price" placeholder="Giá">${product.price}</p> 
                    <label for="name">Giá sản phẩm</label>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <p class="form-control" id="description" placeholder="Mô tả sản phẩm">${product.description}</p> 
                    <label for="name">Mô tả sản phẩm</label>
                </div>
                <div class="form-floating">
                    <c:forEach items="${categories}" var="b">
                        <c:if test="${b.id==product.categoryId.id}">
                            <p class="form-control" id="categoryId" >
                                ${b.name}
                            </p>
                        </c:if>

                    </c:forEach>

                    <label for="categoryId" class="form-label">Danh mục:</label>
                </div>
                <div class="form-floating">
                    <c:forEach items="${brands}" var="b">
                        <c:if test="${b.id==product.brandId.id}">
                            <p class="form-control" id="categoryId" >
                                ${b.name}
                            </p>
                        </c:if>
                    </c:forEach>

                    <label for="brandId" class="form-label">Nhãn hàng:</label>
                </div>

                <div class="form-floating mb-3 mt-3">
                    <input class="form-control"  id="availableQuantity"  name ="availableQuantity" placeholder="Tên sản phẩm" path="availableQuantity" />
                    <label for="name">Số lượng sản phẩm</label>
                </div>
                <input type="hidden" name="branchId" value="${pageContext.request.userPrincipal.name}" />

                <div class="form-floating">
                    <button class="btn btn-info mt-1" type="submit">Cập nhật</button>
                </div>
            </form>
        </div>
    </div>
</div>
