<%-- 
    Document   : manage-categories
    Created on : Jul 25, 2024, 2:49:20 PM
    Author     : tongh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info mt-1">QUẢN LÝ DANH MỤC</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/categories" />">Add Category</a>
</div>


<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Tên danh mục</th>        
                        <th>Mô tả</th>
                        <th>Thao tác</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${categories}" var="c">
                        <tr>
                            <td>${c.id}</td>
                            <td>${c.name}</td>
                            <td>${c.description}</td>
                            <td>
                                <c:url value="/api/categories/${c.id}/" var="urlDelete" />
                                <a class="btn btn-info" href="<c:url value="/categories/${c.id}" />">Cập nhật</a>
                                <button onclick="eDelete('${urlDelete}',${c.id})" class="btn btn-danger">Xóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<script src="<c:url value="/js/script.js" />"></script>