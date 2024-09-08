<%-- 
    Document   : create-password
    Created on : Sep 3, 2024, 10:21:05 PM
    Author     : tongh
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lấy lại mật khẩu</title>
        <script src="<c:url value="/js/script.js" />"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="<c:url value='/assets/vendors/mdi/css/materialdesignicons.min.css' />">
        <link rel="stylesheet" href="<c:url value='/assets/vendors/ti-icons/css/themify-icons.css' />">
        <link rel="stylesheet" href="<c:url value='/assets/vendors/css/vendor.bundle.base.css' />">
        <link rel="stylesheet" href="<c:url value='/assets/vendors/font-awesome/css/font-awesome.min.css' />">
        <!-- endinject -->
        <!-- Plugin css for this page -->
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="<c:url value='/assets/images/favicon.png' />">

        <!-- container-scroller -->
        <!-- plugins:js -->
        <script src="<c:url value='/assets/vendors/js/vendor.bundle.base.js' />"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <script src="<c:url value='/assets/vendors/chart.js/chart.umd.js' />"></script>
        <script src="<c:url value='/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js' />"></script>

        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="<c:url value='/assets/js/off-canvas.js' />"></script>
        <script src="<c:url value='/assets/js/misc.js' />"></script>
        <script src="<c:url value='/assets/js/settings.js' />"></script>
        <script src="<c:url value='/assets/js/todolist.js' />"></script>
        <script src="<c:url value='/assets/js/jquery.cookie.js' />"></script>
        <!-- endinject -->
        <script src="<c:url value='/assets/js/dashboard.js' />"></script>
    </head>
    <body class="hold-transition login-page">
        <div class="container-scroller">
            <div class="container-fluid page-body-wrapper full-page-wrapper">
                <div class="content-wrapper d-flex align-items-center auth">
                    <div class="row flex-grow">
                        <div class="col-lg-6 mx-auto">
                            <div class="auth-form-light text-left p-5">
                                <div class="brand-logo">
                                    <img src="assets/images/logo.png">
                                </div>

                                <!-- Hiển thị thông báo lỗi nếu có -->
                                <c:if test="${not empty errorMessage}">
                                    <div style="color: red;">
                                        <p>${errorMessage}</p>
                                    </div>
                                </c:if>

                                <!-- Hiển thị thông báo thành công nếu có -->
                                <c:if test="${not empty successMessage}">
                                    <div style="color: green;">
                                        <p>${successMessage}</p>
                                    </div>
                                </c:if>
                                <c:url value="/create-password" var="action" />

                                <form:form method="post" action="${action}">

                                    <div class="form-group">
                                        <input type="hidden" name="username" value="${username}" class="form-control form-control-lg" id="username" placeholder="Tài khoản" required="true">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="newPassword" class="form-control form-control-lg" id="newPassword" placeholder="Nhập mật khẩu mới" required="true">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="rePassword" class="form-control form-control-lg" id="rePassword" placeholder="Nhập lại mật khẩu" required="true">
                                    </div>
                                    <button type="submit" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">
                                        Xác nhận</button>
                                    </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



    </body>
</html>


