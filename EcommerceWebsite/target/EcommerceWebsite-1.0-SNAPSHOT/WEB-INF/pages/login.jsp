<%-- 
    Document   : login
    Created on : Aug 24, 2024, 5:22:58 PM
    Author     : tongh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
            <div class="row flex-grow">
                <div class="col-lg-6 mx-auto">
                    <div class="auth-form-light text-left p-5">
                        <div class="brand-logo">
                            <img src="\assets\images\auth\logo-ecommerce.png" >
                        </div>
                        <h4>Chào bạn!</h4>
                        <h6 class="font-weight-light">Đăng nhập để tiếp tục.</h6>
                        <form class="pt-3" action="<c:url value="/login"/>" method="post">
                            <div class="form-group">
                                <input type="text" name="username" class="form-control form-control-lg" id="username" placeholder="Tài khoản" required="true">
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Mật khẩu" required="true">
                            </div>
                            <div class="mt-3 d-grid gap-2">
                                <button type="submit"class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">ĐĂNG NHẬP</button>
                            </div>
                            <div class="my-2 d-flex justify-content-between align-items-center">
                                <div class="form-check">
                                    <label class="form-check-label text-muted">
                                        <input type="checkbox" class="form-check-input">Ghi nhớ tôi</label>
                                </div>
                                <a href="#" class="auth-link text-primary">Quên mật khẩu?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
