<%-- 
    Document   : manage-tagproducts
    Created on : Aug 17, 2024, 8:07:20 PM
    Author     : tongh
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info mt-1">QUẢN LÝ NHÃN SẢN PHẨM</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/tagProducts" />">Add TagProduct</a>
</div>
<table class="table table-striped mt-1">
    <tr>
        <th>Id</th>
        <th>Tên sản phẩm</th>        
        <th>Tên nhãn</th>    
        <th>Thao tác</th>

    </tr>
    <c:forEach items="${tagProducts}" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.productId.name}</td>
            <td>${c.tagId.tagName}</td>
            <td>
                <c:url value="/api/tagProducts/${c.id}/" var="urlDelete" />
<!--                <a class="btn btn-info" href="<c:url value="/tagProducts/${c.id}" />">Cập nhật</a>-->
                <button onclick="eDelete('${urlDelete}',${c.id})" class="btn btn-danger">Xóa</button>
            </td>
        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js" />"></script>
