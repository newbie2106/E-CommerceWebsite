<%-- 
    Document   : stats-branch
    Created on : Sep 16, 2024, 9:35:18 PM
    Author     : tongh
--%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<h1 class="text-center text-info mt-1">THÔNG KÊ BÁO CÁO THEO CHI NHÁNH</h1>

<h3 class="text-info mt-1">THÔNG KÊ THEO SẢN PHẨM</h3>


<sec:authorize access="hasRole('ROLE_SUPER_ADMIN')">
    <div class="row">
        <div class="col-lg-6 grid-margin stretch-card">

            <form class="pt-3" method="GET" action="${pageContext.request.contextPath}/stats-branch">
                <button type="submit" class="btn btn-primary">Lọc chi nhánh</button>

                <div class="form-group position-relative boxed-layout">
                    <select name="branch" class="form-control fw-bold text-primary text-center">
                        <c:forEach items="${branches}" var="branch">
                            <option value="${branch.adminUser.username}" ${branch.adminUser.username == currentUsername ? 'selected' : ''}>
                                ${branch.adminUser.username}
                            </option>
                        </c:forEach>
                    </select>
                    <i class="fa-solid fa-caret-down position-absolute" 
                       style="top: 50%; right: 15px; transform: translateY(-50%); pointer-events: none;"></i>
                </div>
            </form> 
        </div>
    </div> 
</sec:authorize>
<div class="row">
    <div class="col-md-5 col-12">
        <table class="table table-striped">
            <tr>
                <th>Id</th>
                <th>Tên sản phẩm</th>
                <th>Doanh thu</th>
            </tr>
            <c:forEach items="${revenueByProducts}" var="p">
                <tr>
                    <td>${p[0]}</td>
                    <td>${p[1]}</td>
                    <td><fmt:formatNumber value="${p[2]}" type="currency" currencySymbol="VNĐ" maxFractionDigits="0" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-md-7 col-12">
        <canvas id="myChart"></canvas>
    </div>
</div>
<hr class="hr" />
<h3 class="text-info mt-1">THÔNG KÊ THEO THÁNG/QUÝ</h3>
<div class="row">
    <div class="col-md-5 col-12">
        <form>
            <div class="form-floating mb-3 mt-3">
                <input type="text" value="${param.year}" class="form-control" id="year" placeholder="Năm" name="year" required="true">
                <label for="year">Năm</label>
            </div>
            <div class="form-floating mb-3 mt-3">
                <select class="form-select" id="period" name="period">
                    <option value="MONTH" selected>Theo tháng</option>
                    <c:choose>
                        <c:when test="${param.period=='QUARTER'}">
                            <option value="QUARTER" selected>Theo quý</option>
                        </c:when>
                        <c:otherwise>
                            <option value="QUARTER">Theo quý</option>
                        </c:otherwise>
                    </c:choose>
                </select>
                <label for="period" class="form-label">Chọn thời gian:</label>
            </div>
            <div class="form-floating mb-3 mt-3">
                <button class="btn btn-success">Lọc</button>
            </div>
        </form>
        <%@ page import="java.util.Date" %>
        <jsp:useBean id="now" class="java.util.Date" />
        <c:if test="${param.year != null}">
            <div class="alert alert-info">
                <h4>Năm: ${param.year}</h4>
                <h4>Thời gian: ${param.period}</h4>
            </div>
        </c:if>
        <table class="table table-striped">
            <tr>
                <th>Thời gian</th>
                <th>Doanh thu</th>
            </tr>
            <c:forEach items="${revenueByPeriod}" var="p">
                <tr>
                    <td>${p[0]}</td>
                    <td><fmt:formatNumber value="${p[1]}" type="currency" currencySymbol="VNĐ" maxFractionDigits="0" /></td>
                </tr>
            </c:forEach>
        </table>


    </div>

    <div class="col-md-7 col-12">
        <canvas id="myChart2"></canvas>
    </div>
</div>
<hr class="hr" />


<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="<c:url value="js/script.js" />"></script>
<script>
    let labels = [];
    let data = [];
    <c:forEach items="${revenueByProducts}" var="p">
labels.push('${p[1]}');
data.push(${p[2]});
</c:forEach>

    let label2 = [];
    let data2 = [];
    <c:forEach items="${revenueByPeriod}" var="p">
label2.push(${p[0]});
data2.push(${p[1]});
</c:forEach>
    window.onload = function () {
        let ctx1 = document.getElementById("myChart");
        drawChartRevenue(ctx1, labels, data);

        let ctx2 = document.getElementById("myChart2");
        drawChartRevenue(ctx2, label2, data2);
    }
</script>
