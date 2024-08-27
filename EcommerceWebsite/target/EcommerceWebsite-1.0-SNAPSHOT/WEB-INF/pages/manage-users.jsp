<%-- 
    Document   : manage-users
    Created on : Jul 26, 2024, 5:28:25 PM
    Author     : tongh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info mt-1">QUẢN LÝ TÀI KHOẢN</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/users" />">Add User</a>
</div>


<ul class="pagination mt-1">
    <c:forEach begin="1" end="${count}" var="i">
        <c:url value="/manage-users" var="pageAction">
            <c:param name="page" value="${i}"/>
        </c:url>
        <li class="page-item">
            <a class="page-link" href="${pageAction}">${i}</a>
        </li>
    </c:forEach>
</ul>


<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Tên tài khoản</th>
                        <th>Ảnh đại diện</th>
                        <th>Họ</th>
                        <th>Tên</th>        
                        <th>Ngày tạo</th>
                        <th>Vai trò</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="p">
                        <tr>

                            <td>${p.username}</td>
                            <td class="d-flex justify-content-center align-items-center gap-3">
                                <img class="rounded img-fluid" src="${p.avatar}" width="80" alt="${p.lastName}">
                            </td>
                            <td>${p.firstName}</td>
                            <td>${p.lastName}</td>
                            <td>${p.createdDate}</td>
                            <td>${p.role.roleName}</td>
                            <td>
                                <c:url value="/api/users/${p.username}/" var="urlDelete" />
                                <a class="btn btn-info" href="<c:url value="/update-user/${p.username}" />">Cập nhật</a>
                                <button onclick="eDelete('${urlDelete}', '${p.username}')" class="btn btn-danger">Xóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>



<script src="<c:url value="/js/script.js" />"></script>

