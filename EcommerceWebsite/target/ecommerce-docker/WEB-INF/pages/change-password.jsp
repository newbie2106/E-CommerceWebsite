<%-- 
    Document   : change-password
    Created on : Aug 27, 2024, 8:49:55 PM
    Author     : tongh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h2>Đổi Mật Khẩu</h2>

<!-- Hiển thị thông báo lỗi nếu có -->
<c:if test="${not empty errorMessage}">
    <div style="color: red;">
        <p>${errorMessage}</p>
    </div>
</c:if>

<!-- Hiển thị thông báo thành công nếu có -->

<c:if test="${not empty successMessage}">
    <script>
        window.onload = function () {
            var successModal = new bootstrap.Modal(document.getElementById('successModal'));
            successModal.show();

            var countdownElement = document.getElementById('countdown');
            var countdownTime = 10; // Thời gian đếm ngược bắt đầu từ 10 giây

            function updateCountdown() {
                if (countdownTime <= 0) {
                    document.getElementById("logoutForm").submit();
                } else {
                    countdownElement.textContent = countdownTime;
                    countdownTime--;
                }
            }

            // Cập nhật đếm ngược mỗi giây
            var countdownInterval = setInterval(updateCountdown, 1000);

            // Nếu người dùng nhấn nút Đăng xuất ngay, hủy bỏ đếm ngược
            document.getElementById('logoutNowButton').addEventListener('click', function () {
                clearInterval(countdownInterval);
            });
        }
    </script>

    <!-- Modal thông báo đổi mật khẩu thành công -->
    <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="successModalLabel">Thông báo</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>${successMessage}</p>
                    <p style="color: red;">Bạn sẽ tự động đăng xuất sau <span id="countdown">10</span> giây hoặc click <strong id="logoutNowButton">Đăng xuất ngay</strong>!</p>
                </div>
                <div class="modal-footer">
                    <form id="logoutForm" action="${pageContext.request.contextPath}/logout" method="post">
                        <button type="submit" class="btn btn-danger">Đăng xuất ngay</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:if>
    
<form:form modelAttribute="changePassword" method="post" action="${pageContext.request.contextPath}/change-password">

    <div class="col-lg-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">ĐỔI MẬT KHẨU</h4>
                </p>
                <table class="table table-bordered">

                    <tbody>
                        <!--lay username an de thực hiện thay đổi mk mà ko có username hiện lên URL-->
                    <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}" /> 

                    <tr>
                        <td>Mật khẩu cũ:</td>
                        <td><form:password path="oldPassword" /></td>
                    </tr>
                    <tr>
                        <td>Mật khẩu mới:</td>
                        <td><form:password path="newPassword" /></td>
                    </tr>
                    <tr>
                        <td>Xác nhận mật khẩu mới:</td>
                        <td><form:password path="rePassword" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit" class="btn btn-gradient-success btn-fw">Xác nhận</button>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form:form>