<%-- 
    Document   : base
    Created on : Apr 25, 2024, 7:38:38 PM
    Author     : tongh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<c:url value='assets/images/logo.png' />">

        <title>
            <tiles:insertAttribute name="title" />
        </title>

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="<c:url value="/js/script.js" />"></script>

        <link rel="stylesheet" href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css' />">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="<c:url value='/assets/vendors/mdi/css/materialdesignicons.min.css' />">
        <link rel="stylesheet" href="<c:url value='/assets/vendors/ti-icons/css/themify-icons.css' />">
        <link rel="stylesheet" href="<c:url value='/assets/vendors/css/vendor.bundle.base.css' />">
        <link rel="stylesheet" href="<c:url value='/assets/vendors/font-awesome/css/font-awesome.min.css' />">
        <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />">
        <link rel="stylesheet" href="<c:url value='/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css'/>">
        <script src="<c:url value='/assets/vendors/js/vendor.bundle.base.js' />"></script>
        <script src="<c:url value='/assets/vendors/chart.js/chart.umd.js' />"></script>
        <script src="<c:url value='/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js' />"></script>
        <script src="<c:url value='/assets/js/off-canvas.js' />"></script>
        <script src="<c:url value='/assets/js/misc.js' />"></script>
        <script src="<c:url value='/assets/js/settings.js' />"></script>
        <script src="<c:url value='/assets/js/todolist.js' />"></script>
        <script src="<c:url value='/assets/js/jquery.cookie.js' />"></script>
        <script src="<c:url value='/assets/js/dashboard.js' />"></script>

    </head>
    <body> 

        <div class="container-scroller">
            <tiles:insertAttribute name="header" />
            <div class="container-fluid page-body-wrapper">
                <tiles:insertAttribute name="sidebar" />
                <div class="main-panel">
                    <div class="content-wrapper">
                        <tiles:insertAttribute name="content" />

                    </div>
                </div>
            </div>
            <tiles:insertAttribute name="footer" />

        </div>


    </body>
</html>