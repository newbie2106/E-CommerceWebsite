<%-- 
    Document   : index
    Created on : Jul 22, 2024, 8:06:19 PM
    Author     : tongh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
    .card-img-top {
        transition: transform 0.5s ease, filter 0.5s ease;
    }

    .card-img-top:hover {
        transform: scale(1.1); /* Zoom ảnh */
        filter: brightness(70%) sepia(100%) hue-rotate(200deg); /* Thay đổi màu sắc */
    }
</style>

<div class="page-header">
    <h3 class="page-title">ĐÂY LÀ TRANG CHỦ</h3>
</div>
<sec:authorize access="hasRole('ROLE_SUPER_ADMIN')">
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card bg-gradient-danger card-img-holder text-white" style="width: 18rem;">

                <div class="card-body">
                    <h4 class="font-weight-normal mb-3">${countProduct} Sản phẩm</h4>
                    <a href="<c:url value="/admin-manage-products" />" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card bg-gradient-danger card-img-holder text-white" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="font-weight-normal mb-3">${countCate} Danh mục</h4>
                    <a href="<c:url value="/manage-categories" />" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card bg-gradient-danger card-img-holder text-white" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="font-weight-normal mb-3">${countUser} Tài khoản</h4>
                    <a href="<c:url value="/manage-users"/>" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card bg-gradient-danger card-img-holder text-white" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="font-weight-normal mb-3">${countBrand} Nhãn hàng</h4>

                    <a href="<c:url value="/manage-brands"/>" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card bg-gradient-danger card-img-holder text-white" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="font-weight-normal mb-3">Thống kê</h4>
                    <a href="<c:url value="/stats"/>" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card bg-gradient-danger card-img-holder text-white" style="width: 18rem;">

                <div class="card-body">
                    <h4 class="font-weight-normal mb-3">${countProduct} Sản phẩm</h4>
                    <a href="<c:url value="/manage-products" />" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card bg-gradient-danger card-img-holder text-white" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="font-weight-normal mb-3">Quản lý đơn hàng</h4>
                    <a href="<c:url value="/saleOrder"/>" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card bg-gradient-danger card-img-holder text-white" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="font-weight-normal mb-3">Thống kê</h4>
                    <a href="<c:url value="/stats"/>" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>





