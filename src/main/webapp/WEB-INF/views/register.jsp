<%-- 
    Document   : register
    Created on : Sep 30, 2020, 4:14:22 PM
    Author     : Huy Hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>E B@nking - Đăng ký</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script>
            addEventListener("load", function () {
                setTimeout(hideURLbar, 0);
            }, false);

            function hideURLbar() {
                window.scrollTo(0, 1);
            }
            function cancel() {
                window.location.href = 'http://localhost:8080/ebanking/';
            }
        </script>
        <jsp:include page="/WEB-INF/views/header.jsp" />

    </head>
    <body>
        <!-- header -->
        <header>
            <div class="container">
                <!-- nav -->
                <nav class="py-3 d-lg-flex">
                    <div id="logo">
                        <h1>
                            <a href="<c:url value="/"/>"><span class="fa fa-university"></span>
                                E-Banking </a>
                        </h1>
                    </div>
                    <label for="drop" class="toggle"><span class="fa fa-bars"></span></label>
                    <input type="checkbox" id="drop" />
                    <ul class="menu ml-auto mt-1">
                        <li class="active"><a href="<c:url value="/"/>">Trang chủ</a></li>
                        <li class=""><a href="<c:url value="/"/>">Về chúng tôi</a></li>
                        <li class=""><a href="<c:url value="/"/>">Các dịch vụ</a></li>
                        <li class=""><a href="<c:url value="/"/>">Liên hệ</a></li>
                        <li class="last-grid"><a href="<c:url value="/login"/>">Bắt đầu ngay</a></li>
                    </ul>
                </nav>
                <!-- //nav -->
            </div>
        </header>
        <!-- //header -->

        <!-- inner-banner -->
        <section class="inner-banner" id="home">
            <div class="inner-layer">
                <div class="container"></div>
            </div>
        </section>
        <!-- //inner-banner -->

        <!-- breadcrumb -->
        <div class="breadcrumb-w3pvt">
            <div class="container">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="<c:url value="/"/>">Trang chủ</a></li>
                        <li class="breadcrumb-item" aria-current="page">Đăng ký</li>
                    </ol>
                </nav>
            </div>
        </div>
        <!-- //breadcrumb -->

        <div class="container">
            <div class="row">
                <div class="contact-form mt-md-0 mt-sm-5 mt-4" style="width: 100%;">
                    <h4 class="mb-4 w3f_title title_center">ĐĂNG KÝ TÀI KHOẢN</h4>
                    <table class="table">
                        <form:form name="contactform" id="contactform" method="POST" action="${pageContext.request.contextPath}/register/confirmRegister" modelAttribute="registerModel">
                            <tr>
                                <td>
                                    <div>
                                        <label>Họ và tên</label> <form:input type="text" class="form-control" path="name" required="required"
                                                    id="name" placeholder="Nhập họ và tên" name="name"/>
                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <label>Giới tính</label> 
                                        <div>
                                            <form:radiobutton path="gender" required="required"
                                                              value="Nam"/> Nam
                                            <form:radiobutton path="gender" required="required" style="margin-left: 50px;"
                                                              value="Nữ"/> Nữ
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div>
                                        <label>Ngày sinh</label> <form:input type="date" class="form-control" path="birthdate" required="required"
                                                    id="birthdate" name="birthdate"/>
                                        
                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <label>Tên đăng nhập</label> <form:input type="text" class="form-control" path="username" required="required"
                                                    id="username" placeholder="Nhập tên đăng nhập" name="username"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div>
                                        <label>Mật khẩu</label> <form:input type="password" class="form-control" path="password" required="required"
                                                    id="password" placeholder="Nhập mật khẩu" name="password"/>
                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <label>Nhập lại mật khẩu</label> <form:input type="password" class="form-control" path="rePassword" required="required"
                                                    id="rePassword" placeholder="Nhập lại mật khẩu" name="rePassword"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div>
                                        <label>Địa chỉ</label> <form:input type="text" class="form-control" path="address" required="required"
                                                    id="address" placeholder="Nhập địa chỉ" name="address"/>
                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <label>Quận</label> <form:input type="text" class="form-control" path="district" required="required"
                                                    id="district" placeholder="Nhập quận" name="district"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div>
                                        <label>Thành phố</label> <form:input type="text" class="form-control" path="city" required="required"
                                                    id="city" placeholder="Nhập thành phố" name="city"/>
                                    </div>
                                </td>
                                <td>
                                    <label>Quốc tịch</label> <form:input type="text" class="form-control" path="nationality" required="required"
                                                id="nationality" placeholder="Nhập quốc tịch" name="nationality"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div>
                                        <label>CMND</label> <form:input type="text" class="form-control" path="cmnd" required="required"
                                                    id="cmnd" placeholder="Nhập CMND" name="cmnd"/>
                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <label>Số điện thoại</label> <form:input type="text" class="form-control" path="phone" required="required"
                                                    id="phone" placeholder="Nhập số điện thoại" name="phone"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div>
                                        <label style="margin-top: 9px; margin-bottom: 26px;">Email</label> <form:input type="text" class="form-control" path="email" required="required"
                                                    id="email" placeholder="Nhập Email" name="email"/>
                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <label>Captcha</label> 
                                        <img src="${pageContext.request.contextPath }/captcha">
                                        <br>
                                        <form:input type="text" name="captcha" class="form-control" required="required" style="margin-top: 20px;" path="captcha"/>
                                        <br>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div style="margin-top: 50px; float: right;">
                                        <form:button type="submit" class="btn btn-default" style="margin-right: 20px;">Đăng ký</form:button>
                                        <button type="button" class="btn btn-primary" onclick="cancel()" style="margin-left: 20px;">Hủy bỏ</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"><p style=" color: red;">${error}</p></td>
                            </tr>
                        </form:form>
                    </table>
                </div>
            </div>
        </div>



        <!-- footer -->
        <footer class="footer py-5">
            <div class="container pt-lg-4">
                <div class="row">
                    <div class="col-lg-3 col-sm-6 footer-top">
                        <h4 class="mb-4 w3f_title">Contact Info</h4>
                        <ul class="list-w3">
                            <li><span class="fa mr-1 fa-map-marker"></span>123 Ông Ích
                                Khiêm, Hải Châu Đà Nẵng</li>
                            <li class="my-2"><span class="fa mr-1 fa-phone"></span>1900
                                1010</li>
                            <li class="my-2"><span class="fa mr-1 fa-phone"></span>1900
                                1001</li>
                            <li class=""><span class="fa mr-1 fa-envelope"></span><a
                                    href="mailto:ebanking@gmail.com">ebanking@gmail.com</a></li>
                        </ul>
                    </div>
                    <div class="col-lg-3 col-sm-6 footv3-left mt-sm-0 mt-4">
                        <h4 class="mb-4 w3f_title">Chương trình khách hàng</h4>
                        <ul class="list-w3">
                            <li class="my-2"><a href="#"> Chương trình khuyến mãi </a></li>
                            <li class="mb-2"><a href="#"> Tài khoản và tiết kiệm </a></li>
                            <li class="my-2"><a href="#"> Chuyển và nhận tiền </a></li>
                            <li class="my-2"><a href="#"> Thẻ ghi nợ Credit Cards </a></li>
                        </ul>

                    </div>
                    <div class="col-lg-2 col-sm-4 mt-lg-0 mt-sm-5 mt-4">
                        <h4 class="mb-4 w3f_title">Giới thiệu</h4>
                        <ul class="list-w3">
                            <li class="my-2"><a href="#"> Lịch sử phát triển </a></li>
                            <li class="mb-2"><a href="#"> Tầm nhìn chiến lược </a></li>
                            <li class="my-2"><a href="#"> Giá trị cốt lõi </a></li>
                            <li class="my-2"><a href="#"> Cơ cấu bộ máy quản lý </a></li>
                        </ul>
                    </div>

                    <div class="col-lg-2 col-sm-4 mt-lg-0 mt-sm-5 mt-4">
                        <h4 class="mb-4 w3f_title">Thông tin</h4>
                        <ul class="list-w3">
                            <li class="my-2"><a href="#"> Đường dây nóng 24/7 </a></li>
                            <li class="mb-2"><a href="#"> Các chi nhánh ngân hàng </a></li>
                            <li class="my-2"><a href="#"> Ứng dụng di động </a></li>
                        </ul>
                    </div>

                    <div class="col-lg-2 col-sm-4 mt-lg-0 mt-sm-5 mt-4">
                        <h4 class="mb-4 w3f_title">Nhà đầu tư</h4>
                        <ul class="list-w3">
                            <li class="my-2"><a href="#"> Đại hội cổ đông </a></li>
                            <li class="mb-2"><a href="#"> Điều lệ quy chế </a></li>
                            <li class="my-2"><a href="#"> Báo cáo tài chính </a></li>
                            <li class="my-2"><a href="#"> Báo cáo thường niên </a></li>
                        </ul>
                    </div>

                </div>
            </div>
            <!-- //footer bottom -->
        </footer>
        <!-- //footer -->
        <!-- move top -->
        <div class="move-top text-right">
            <a href="#home" class="move-top"> <span
                    class="fa fa-angle-up  mb-3" aria-hidden="true"></span>
            </a>
        </div>
        <!-- move top -->
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
    </body>
</html>