<%-- 
    Document   : confirmtransaction
    Created on : Oct 8, 2020, 4:20:25 PM
    Author     : Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>E B@nking Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script>
            addEventListener("load", function () {
                setTimeout(hideURLbar, 0);
            }, false);

            function hideURLbar() {
                window.scrollTo(0, 1);
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
                            <a href="index.html"><span class="fa fa-university"></span>
                                E-Banking </a>
                        </h1>
                    </div>
                    <label for="drop" class="toggle"><span class="fa fa-bars"></span></label>
                    <input type="checkbox" id="drop" />
                    <ul class="menu ml-auto mt-1">
                        <li class="active"><a href="index.html">Trang chủ</a></li>
                        <li class=""><a href="about.html">Về chúng tôi</a></li>
                        <li class=""><a href="services.html">Các dịch vụ</a></li>
                        <li class=""><a href="contact.html">Liên hệ</a></li>
                            <c:choose>
                                <c:when test="${chucaidau != null}">
                                <li><a class="circle-avatar" href="<c:url value = ''/>">${chucaidau}</a></li>
                                </c:when>
                                <c:otherwise>
                                <li class="last-grid"><a href="#">Bắt đầu ngay</a></li>
                                </c:otherwise>
                            </c:choose>
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
                        <li class="breadcrumb-item"><a href="index.html">Welcome, ${name}</a></li>
                        <li class="breadcrumb-item" aria-current="page">Thống kê giao dịch</li>
                    </ol>
                </nav>
            </div>
        </div>
        <!-- //breadcrumb -->

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3 col-sm-3">
                    <div class="sidebar">
                        <a href="<c:url value="/trangchu"/>">Home</a>
                        <a href="<c:url value="/customer/info"/>">View Customer Profile</a>
                        <a href="<c:url value="/customer/account/list"/>">View Account Information</a>
                        <a href="#" >Change Password</a>
                        <a class="active" href="<c:url value="/customer/account/transaction/search"/>">View Transaction</a>
                        <a href="<c:url value="/customer/internaltransfermoney"/>" >Internal Transfer Money</a>
                        <a href="#" >External Transfer Money</a>
                    </div>
                </div>
                <div class="mt-md-0 mt-sm-5 mt-4" style="width: 70%;">
                    <h4 class="mb-4 w3f_title title_center">Chuyển tiền nội bộ</h4>
                    <table class="table table-bordered">
                        <tr>
                            <td colspan="4" style="background-color: greenyellow;">Chuyển khoản</td>
                        </tr>
                        <form:form name="contactform" method="POST" modelAttribute="internalTransferModel" action="${pageContext.request.contextPath}/customer/confirmTransaction">
                            <form:hidden path="accountFromNo" value="${internalTransferModel.getAccountFrom().getAccountNo()}" />
                            <form:hidden path="accountToNo" value="${internalTransferModel.getAccountTo().getAccountNo()}" />
                            <form:hidden path="amount" value="${internalTransferModel.getAmount()}" />
                            <form:hidden path="message" value="${internalTransferModel.getMessage()}" />
                            <form:hidden path="feeCarier" value="${internalTransferModel.getFeeCarier()}" />
                            <form:hidden path="fee" value="${internalTransferModel.getFee()}" />
                            <tr>
                                <td>
                                    <label>Tài khoản chuyển tiền</label>
                                </td>
                                <td colspan="3">${internalTransferModel.getAccountFrom().getAccountNo()}</td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Số dư tài khoản chuyển tiền</label>
                                </td>
                                <td colspan="3">
                                    ${internalTransferModel.getAccountFrom().getBalance()} VNĐ
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Số tiền chuyển khoản</label>
                                </td>
                                <td colspan="3" style="color: red;">
                                    ${internalTransferModel.getAmount()} VNĐ
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Số tiền bằng chữ</label>
                                </td>
                                <td colspan="3">

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Tài khoản thụ hưởng</label>
                                </td>
                                <td colspan="3" style="color: red;">
                                    ${internalTransferModel.getAccountTo().getAccountNo()}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Tên chủ tài khoản thụ hưởng</label>
                                </td>
                                <td colspan="3" style="color: red;">
                                    ${internalTransferModel.getAccountTo().getCustomer().getName()}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Nội dung chuyển tiền</label>
                                </td>
                                <td colspan="3">
                                    ${internalTransferModel.getMessage()}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Phí</label>
                                </td>
                                <c:choose>
                                    <c:when test='${internalTransferModel.getFeeCarier() == "nguoichuyen"}'>
                                        <td colspan="3">
                                            Phí người chuyển trả
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td colspan="3">
                                            Phí nhận chuyển trả
                                        </td>
                                    </c:otherwise>
                                </c:choose>

                            </tr>
                            <tr>
                                <td>
                                    <label>Số tiền phí</label>
                                </td>
                                <td colspan="3">
                                    10000 VNĐ
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Email nhận mã giao dịch</label>
                                </td>
                                <td colspan="3">
                                    ${internalTransferModel.getAccountFrom().getCustomer().getEmail()}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Nhập mã giao dịch</label>
                                </td>
                                <td colspan="3">
                                    <form:input type="text" name="otp" class="form-control" required="required" style="margin-top: 20px;" path="otp"/>
                                    <br>
                                    <p style=" color: red;">${error}</p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <form:button type="submit" class="btn btn-default" style="margin-top: 20px;">Chuyển khoản</form:button>
                                    <form:button type="submit" class="btn btn-default" style="margin-top: 20px; margin-left: 50px; "><a style="color: black;" href="<c:url value="/trangchu"/>">Cancel</a></form:button>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4" style="text-align: center;">Mã giao dịch đã được gửi đến email của quý khách.</td>
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
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </body>
</html>

