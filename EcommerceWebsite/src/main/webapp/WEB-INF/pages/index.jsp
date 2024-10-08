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
        <div class="col-md-4 stretch-card grid-margin">
            <div class="card bg-gradient-danger card-img-holder text-white">

                <div class="card-body">
                    <img src="https://res.cloudinary.com/dsbkju7j9/image/upload/v1721727363/orftmiecdppk8hhlmwp6.jpg" class="card-img-top" alt="...">
                    <h4 class="font-weight-normal mb-3">${countProduct} Sản phẩm</h4>
                    <a href="<c:url value="/manage-products" />" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card" style="width: 18rem;">
                <img src="https://res.cloudinary.com/dsbkju7j9/image/upload/v1721727363/orftmiecdppk8hhlmwp6.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${countCate}</h5>
                    <p class="card-text">danh mục</p>
                    <a href="<c:url value="/manage-categories" />" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card" style="width: 18rem;">
                <img src="https://res.cloudinary.com/dsbkju7j9/image/upload/v1721727364/ziudjt4frhelyyrhwpsv.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${countUser}</h5>
                    <p class="card-text">Thành viên</p>
                    <a href="<c:url value="/manage-users"/>" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card" style="width: 18rem;">
                <img src="https://res.cloudinary.com/dsbkju7j9/image/upload/v1721993566/bpvbmqapmuedyxjvznq9.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${countBrand}</h5>
                    <p class="card-text">Nhãn hàng</p>
                    <a href="<c:url value="/manage-brands"/>" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card" style="width: 18rem;">
                <img src="https://res.cloudinary.com/dsbkju7j9/image/upload/v1721727365/f7ob16kiirnwatca6eid.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Thống kê</h5>
                    <p class="card-text">Bảng thống kê</p>
                    <a href="<c:url value="/stats"/>" class="btn btn-primary">Chi tiết</a>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="row">
        <div class="col-md-4 stretch-card grid-margin">
            <div class="card bg-gradient-danger card-img-holder text-white">
                <div class="card-body">
                    <img src="assets/images/dashboard/circle.svg" class="card-img-absolute" alt="circle-image" />
                    <h4 class="font-weight-normal mb-3">Weekly Sales <i class="mdi mdi-chart-line mdi-24px float-end"></i>
                    </h4>
                    <h2 class="mb-5">$ 15,0000</h2>
                    <h6 class="card-text">Increased by 60%</h6>
                </div>
            </div>
        </div>
        <div class="col-md-4 stretch-card grid-margin">
            <div class="card bg-gradient-info card-img-holder text-white">
                <div class="card-body">
                    <img src="assets/images/dashboard/circle.svg" class="card-img-absolute" alt="circle-image" />
                    <h4 class="font-weight-normal mb-3">Weekly Orders <i class="mdi mdi-bookmark-outline mdi-24px float-end"></i>
                    </h4>
                    <h2 class="mb-5">45,6334</h2>
                    <h6 class="card-text">Decreased by 10%</h6>
                </div>
            </div>
        </div>
        <div class="col-md-4 stretch-card grid-margin">
            <div class="card bg-gradient-success card-img-holder text-white">
                <div class="card-body">
                    <img src="assets/images/dashboard/circle.svg" class="card-img-absolute" alt="circle-image" />
                    <h4 class="font-weight-normal mb-3">Visitors Online <i class="mdi mdi-diamond mdi-24px float-end"></i>
                    </h4>
                    <h2 class="mb-5">95,5741</h2>
                    <h6 class="card-text">Increased by 5%</h6>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>





