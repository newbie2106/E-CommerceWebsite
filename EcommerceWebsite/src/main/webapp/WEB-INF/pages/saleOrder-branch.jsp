<%-- 
    Document   : saleOrder-branch
    Created on : Oct 13, 2024, 10:49:35 PM
    Author     : tongh
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.tth.pojo.CustomUserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
    .order-item {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        border-radius: 5px;
        background-color: #ffffff;
    }
    .text-blue {
        color: blue; /* Màu xanh cho trạng thái đơn hàng */
    }
</style>
<c:url value="/updateStatus" var="action" />


<h1 class="text-center text-info mt-1">QUẢN LÝ ĐƠN HÀNG</h1>
<div class="container mt-5">
    <h2>Quản lý Đơn hàng</h2>
    <div class="order-list">
        <c:forEach items="${saleOrders}" var="order">
            <div class="order-item border p-3 mb-3">
                <div class="order-header d-flex justify-content-between">
                    <div>
                        <strong>Ngày đặt hàng:</strong>
                        <fmt:formatDate value="${order.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </div>
                    <div>
                        <strong>Trạng thái thanh toán:</strong>
                        <span class="text-blue">
                            <c:choose>
                                <c:when test="${order.isPaid}">
                                    Đã thanh toán
                                </c:when>
                                <c:otherwise>
                                    Chưa thanh toán
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                </div>

                <div class="order-details mt-3">
                    <c:forEach items="${order.orderDetails}" var="detail">
                        <div class="product-item d-flex align-items-center border-bottom py-2">
                            <img src="${detail.productImage}" alt="${detail.productName}" width="100" height="80" class="me-3"/>
                            <div>
                                <h5>${detail.productName}</h5>
                                <p>Giá: <span class="text-danger">${detail.unitPrice} VND</span></p>
                                <p>Số lượng: ${detail.quantity}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <hr>

                <div class="d-flex justify-content-between">
                    <div class="text-start">
                        <strong>Tổng tiền:</strong>
                        <span class="text-blue">${order.totalAmount} VND</span>
                        <br>
                        <strong>Trạng thái đơn hàng (hiện tại):</strong>
                        <span class="text-blue d-inline-block mt-4">${order.currentStatus}</span>

                    </div>
                    <div class="text-end">
                        <div style="margin-bottom: 12px;"><strong>Chọn trạng thái:</strong></div>
                        <form action="${action}" method="post">
                            <select name="selectedStatus" class="form-select text-blue" aria-label="Chọn trạng thái">
                                <option value="Pending" <c:if test="${order.currentStatus == 'Pending'}">selected</c:if>>Pending</option>
                                <option value="confirmed" <c:if test="${order.currentStatus == 'confirmed'}">selected</c:if>>Confirmed</option>
                                <option value="Shipped" <c:if test="${order.currentStatus == 'Shipped'}">selected</c:if>>Shipped</option>
                                <option value="Delivered" <c:if test="${order.currentStatus == 'Delivered'}">selected</c:if>>Delivered</option>
                                <option value="Cancelled" <c:if test="${order.currentStatus == 'Cancelled'}">selected</c:if>>Cancelled</option>
                                </select>
                                <input type="hidden" name="orderId" value="${order.id}" />
                            <button type="submit" class="btn btn-primary mt-2">Cập nhật</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
