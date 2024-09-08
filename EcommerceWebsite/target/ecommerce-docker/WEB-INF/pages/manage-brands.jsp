<%-- 
    Document   : manage-brands
    Created on : Jul 25, 2024, 2:49:28 PM
    Author     : tongh
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info mt-1">QUẢN LÝ NHÃN HÀNG</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/brands" />">Add Brand</a>
</div>



<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Logo</th>
                        <th>Tên sản phẩm</th>
                        <th>Thao tác</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${brands}" var="b">
                        <tr class="mt-2">
                            <td>${b.id}</td>
                            <td class="d-flex justify-content-center align-items-center gap-3">
                                <img class="rounded img-fluid" src="${b.logo}" width="40" alt="${b.name}">
                            </td>
                            <td>${b.name}</td>
                            <td>
                                <c:url value="/api/brands/${b.id}/" var="urlDelete" />
                                <a class="btn btn-info" href="<c:url value="/brands/${b.id}" />">Cập nhật</a>
                                <button onclick="eDelete('${urlDelete}',${b.id})" class="btn btn-danger">Xóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<script src="<c:url value="/js/script.js" />"></script>
