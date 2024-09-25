<%-- 
    Document   : login
    Created on : Aug 24, 2024, 5:22:58 PM
    Author     : tongh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="shortcut icon" href="<c:url value='assets/images/logo.png' />">

        <script src="<c:url value="/js/script.js" />"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css' />">

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
                                <h4>Chào bạn!</h4>
                                <h6 class="font-weight-light">Đăng nhập để tiếp tục.</h6>
                                <form class="pt-3" action="<c:url value='/login'/>" method="post">
                                    <div class="form-group">
                                        <input type="text" name="username" class="form-control form-control-lg" id="username" placeholder="Tài khoản" required="true">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Mật khẩu" required="true">


                                    </div>
                                    <div class="form-group position-relative">
                                        <select name="role" class="form-control fw-bold text-primary text-center">
                                            <option value="ROLE_ADMIN">Đăng nhập với Admin</option>
                                            <option value="ROLE_SUPER_ADMIN">Đăng nhập với Super Admin</option>
                                        </select>
                                        <i class="fa-solid fa-caret-down position-absolute" 
                                           style="top: 50%; right: 15px; transform: translateY(-50%); pointer-events: none;"></i>
                                    </div>
                                    <c:if test="${param.error != null}">
                                        <span class="text-danger">Sai tài khoản hoặc mật khẩu</span>
                                    </c:if>
                                    <div class="mt-3 d-grid gap-2">
                                        <button type="submit" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">ĐĂNG NHẬP</button>
                                    </div>
                                    <div class="my-2 d-flex justify-content-between align-items-center">
                                        <div class="form-check">
                                            <label class="form-check-label text-muted">
                                                <input type="checkbox" class="form-check-input">Ghi nhớ tôi
                                            </label>
                                        </div>
                                        <a href="<c:url value='/verify-account'/>" class="auth-link text-primary">Quên mật khẩu?</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

