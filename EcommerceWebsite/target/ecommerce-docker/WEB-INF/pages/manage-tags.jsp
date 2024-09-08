<%-- 
    Document   : manage-tags
    Created on : Aug 17, 2024, 7:03:10 PM
    Author     : tongh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info mt-1">QUẢN LÝ NHÃN</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/tags" />">Add Tag</a>
</div>



<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Tên nhãn</th>        
                        <th>Thao tác</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tags}" var="c">
                        <tr>
                            <td>${c.id}</td>
                            <td>${c.tagName}</td>
                            <td>
                                <c:url value="/api/tags/${c.id}/" var="urlDelete" />
                                <a class="btn btn-info" href="<c:url value="/tags/${c.id}" />">Cập nhật</a>
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