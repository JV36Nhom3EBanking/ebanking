<%-- 
    Document   : index
    Created on : Aug 3, 2020, 3:13:11 PM
    Author     : Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>E B@nking</title>
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
                        <li class="last-grid"><a href="<c:url value="/login" />">Bắt đầu ngay</a></li>
                    </ul>
                </nav>
                <!-- //nav -->
            </div>
        </header>
        <!-- //header -->


        <!-- banner -->
        <div class="banner" id="home">
            <div class="layer">
                <div class="container">
                    <div class="banner-text-w3pvt">
                        <!-- banner slider-->
                        <div class="csslider infinity" id="slider1">
                            <input type="radio" name="slides" checked="checked" id="slides_1" />
                            <input type="radio" name="slides" id="slides_2" /> <input
                                type="radio" name="slides" id="slides_3" />
                            <ul class="banner_slide_bg">
                                <li>
                                    <div class="w3ls_banner_txt">
                                        <h2 class="b-w3ltxt mt-md-4">
                                            Chuyển khoản <span>trực tiếp.</span>
                                        </h2>
                                        <h4 class="b-w3ltxt ">Đáp ứng nhu cầu của bạn</h4>
                                        <p class="w3ls_pvt-title my-3">Bạn có thể chuyển tiền cho bất kì 
                                            tài khoản nào thuộc bất kì ngân hàng nào bạn muốn ngay lập tức. Chỉ với vài
                                            bước đơn giản bạn đã có thể sử dụng dịch vụ này. Việc chuyển tiền của bạn 
                                            sẽ không còn gặp khó khăn vì lịch làm việc của ngân hàng.
                                        </p>
                                        <a href="services.html" class="btn btn-banner my-3 mr-2">Tìm hiểu thêm
                                        </a> <a href="contact.html" class="btn btn-banner1 my-3">Bắt đầu ngay</a>
                                    </div>
                                </li>
                                <li>
                                    <div class="w3ls_banner_txt">
                                        <h3 class="b-w3ltxt mt-md-4">
                                            Tài khoản <span>tiết kiệm.</span>
                                        </h3>
                                        <h4 class="b-w3ltxt">Bảo đảm tương lai của bạn</h4>
                                        <p class="w3ls_pvt-title my-3">Bạn không cần phải lo lắng tương lai khi về
                                            hưu của bản thân mình. Tạo một tài khoản và bắt đầu tiết kiệm tài sản ngày bây giờ.
                                            Tham gia ngay với lãi suất hấp dẫn.</p>
                                        <a href="services.html" class="btn btn-banner my-3 mr-2">Tìm hiểu thêm</a>
                                        <a href="contact.html" class="btn btn-banner1 my-3">Bắt đầu ngay</a>
                                    </div>
                                </li>
                                <li>
                                    <div class="w3ls_banner_txt">
                                        <h3 class="b-w3ltxt mt-md-4">
                                            Theo dõi <span>thông tin</span>
                                        </h3>
                                        <h4 class="b-w3ltxt ">Quản lý lịch sử giao dịch</h4>
                                        <p class="w3ls_pvt-title my-3">Xem thông tin tài khoản trực tiếp của bạn.
                                            Thống kê các giao dịch, xem số dư nhanh chóng tiện lợi. Bạn không cần phải đến
                                            ATM hay ngân hàng để xem các thông tin này giờ đây bạn có thể thực hiện nó tại mọi nơi.</p>
                                        <a href="services.html" class="btn btn-banner my-3 mr-2">Tìm hiểu thêm</a> 
                                        <a href="contact.html" class="btn btn-banner1 my-3">Bắt đầu ngay</a>
                                    </div>
                                </li>
                            </ul>
                            <div class="navigation">
                                <div>
                                    <label for="slides_1"></label> <label for="slides_2"></label> <label
                                        for="slides_3"></label>
                                </div>
                            </div>
                        </div>
                        <!-- //banner slider-->
                    </div>
                </div>
            </div>
        </div>
        <!-- //banner -->


        <!-- about -->
        <section class="banner-bottom pt-5">
            <div class="container">
                <div class="row bottom_grids text-center mt-lg-5 mt-sm-3">
                    <div class="col-md-4 grid1 mb-5">
                        <img src='<c:url value = "/resources/images/a1.png"/>' alt="" class="img-fluid">
                        <h3 class="my-3">Thống kê</h3>
                        <p class="">Quản lý các giao dịch của bạn đảm bảo việc chi tiêu đúng hạn mức.</p>
                    </div>
                    <div class="col-md-4 grid2 mb-5">
                        <img src='<c:url value = "/resources/images/a2.png"/>' alt="" class="img-fluid">
                        <h3 class="my-3">An ninh</h3>
                        <p class="">Đảm bảo sự an toàn tuyệt đối cho tài sản của bạn.</p>
                    </div>
                    <div class="col-md-4 grid3 mb-5">
                        <img src='<c:url value = "/resources/images/a3.png"/>' alt="" class="img-fluid">
                        <h3 class="my-3">Ưu đãi</h3>
                        <p class="">Luôn luôn có những chương trình ưu đãi hấp dẫn đang chờ đón bạn.</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- //about -->

        <!-- advantages and details -->
        <section class="advantages pt-lg-5 pt-3">
            <div class="container pb-lg-5">
                <div class="row advantages_grids">
                    <div class="col-lg-6">
                        <img src='<c:url value = "/resources/images/advantages.jpg"/>' alt="" class="img-fluid">
                    </div>
                    <div class="col-lg-6 mt-lg-0 mt-4">
                        <h3 class="mt-3">Dịch vụ ngân hàng trực tuyến e-Banking.</h3>
                        <p class="my-sm-4 my-3">Trên nền tảng công nghệ tiên tiến, hiện đại, các sản phẩm dịch vụ 
                            của ngân hàng e-Banking đã và đang làm hài lòng đông đảo quý vị khách hàng với nhiều tính năng,
                            tiện ích được cải tiến liên tục, mở rộng. Với hệ thống e-Banking, quý khách có thể dễ dàng thực
                            hiện các giao dịch truy vấn thông tin, truy vấn lịch sử giao dịch, chuyển khoản trong cùng hoặc
                            khác ngân hàng.</p>
                        <p>E-Banking trân trọng cảm ơn quý Khách hàng đã luôn tin yêu, lựa chọn chúng tôi!</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- //advantages and details -->

        <!-- products -->
        <section class="products py-5">
            <div class="container py-lg-5 py-3">
                <h3 class="heading mb-sm-5 mb-4">
                    Cải thiện cuộc sống <strong>của bạn</strong>
                </h3>
                <div class="row products_grids text-center mt-5">
                    <div class="col-md-3 col-6 grid4">
                        <div class="prodct1 border p-3">
                            <a href="#"> <img src='<c:url value = "/resources/images/a1.png"/>' alt="" class="img-fluid">
                                <h3 class="mt-2">Chuyển tiền nhanh chóng</h3> <span
                                    class="fa fa-long-arrow-right"></span>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-3 col-6 grid5">
                        <div class="prodct1 border p-3">
                            <a href="#"> <img src='<c:url value = "/resources/images/a2.png"/>' alt="" class="img-fluid">
                                <h3 class="mt-2">Bảo đảm an toàn thông tin</h3> <span
                                    class="fa fa-long-arrow-right"></span>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-3 col-6 grid6 mt-md-0 mt-3">
                        <div class="prodct1 border p-3">
                            <a href="#"> <img src='<c:url value = "/resources/images/a8.png"/>' alt="" class="img-fluid">
                                <h3 class="mt-2">Tiết kiệm cho tương lai</h3> <span
                                    class="fa fa-long-arrow-right"></span>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-3 col-6 grid7 mt-md-0 mt-3">
                        <div class="prodct1 border p-3">
                            <a href="#"> <img src='<c:url value = "/resources/images/a4.png"/>' alt="" class="img-fluid">
                                <h3 class="mt-2">Credit Cards - Dịch vụ trả sau</h3> <span
                                    class="fa fa-long-arrow-right"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- //products -->

        <!-- stats section -->
        <section class="middlesection-w3pvt">
            <div class="layer py-5">
                <div class="container py-lg-5 py-sm-3">
                    <h3 class="heading mb-sm-5 mb-4">
                        <strong>Thống kê tăng trưởng</strong> của chúng tôi
                    </h3>
                    <div class="row">
                        <div class="col-lg-8 left-build-wthree aboutright-w3pvtwthree">
                            <div class="row">
                                <div class="col-sm-3 col-6 w3layouts_stats_left w3_counter_grid">
                                    <span class="fa fa-credit-card"></span>
                                    <p class="counter">1568</p>
                                    <p class="para-text-w3ls">Tài khoản đã đăng ký</p>
                                </div>
                                <div class="col-sm-3 col-6 w3layouts_stats_left w3_counter_grid2">
                                    <span class="fa fa-users"></span>
                                    <p class="counter">1900</p>
                                    <p class="para-text-w3ls">Khách hàng</p>
                                </div>
                                <div
                                    class="col-sm-3 col-6 w3layouts_stats_left w3_counter_grid1 mt-sm-0 mt-4">
                                    <span class="fa fa-dollar"></span>
                                    <p class="counter">15954</p>
                                    <p class="para-text-w3ls">Giao dịch đã thực hiện</p>
                                </div>
                                <div
                                    class="col-sm-3 col-6 w3layouts_stats_left w3_counter_grid1 mt-sm-0 mt-4">
                                    <span class="fa fa-users"></span>
                                    <p class="counter">130</p>
                                    <p class="para-text-w3ls">Nhân viên</p>
                                </div>
                            </div>
                        </div>
                        <div
                            class="col-lg-4 text-lg-left text-stat text-center mt-lg-0 mt-5">
                            <h4 class="">Hãy trở thành một phần của chúng tôi. Chúng tôi luôn chào đón bạn!</h4>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- //stats section -->

        <!-- footer-top -->
        <section class="footer-top pt-5">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-sm-6 mb-4">
                        <h3 class="my-sm-3 mb-2">
                            <span class="fa mr-2 fa-mobile"></span> Mobile Apps
                        </h3>
                        <p class="mb-sm-4">Chúng tôi đang phát triển. Hy vọng bạn sẽ sớm được sử dụng.</p>
                    </div>
                    <div class="col-lg-3 col-sm-6 mb-4">
                        <h3 class="my-sm-3 mb-2">
                            <span class="fa mr-2 fa-shield"></span> Bảo mật
                        </h3>
                        <p class="mb-sm-4">Vui lòng bảo mật tài khoản cũng như email của bạn. Mọi giao dịch sẽ được
                            xác thực qua email.</p>
                    </div>
                    <div class="col-lg-3 col-sm-6 mb-4">
                        <h3 class="my-sm-3 mb-2">
                            <span class="fa mr-2 fa-globe"></span> Đa dạng ngân hàng
                        </h3>
                        <p class="mb-sm-4">Hệ thống hỗ trợ khách hàng làm việc với nhiều ngân hàng khác nhau.</p>
                    </div>
                    <div class="col-lg-3 col-sm-6 mb-4">
                        <h3 class="my-sm-3 mb-2">
                            <span class="fa mr-2 fa-phone"></span> Hỗ trợ 24/7 
                        </h3>
                        <p class="mb-4">Tổng đài 1900 1010 sẵn sàng 24/7 phục vụ quý khách hàng.</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- //footer-top -->

        <!-- footer -->
        <footer class="footer py-5">
            <div class="container pt-lg-4">
                <div class="row">
                    <div class="col-lg-3 col-sm-6 footer-top">
                        <h4 class="mb-4 w3f_title">Contact Info</h4>
                        <ul class="list-w3">
                            <li><span class="fa mr-1 fa-map-marker"></span>123 Ông Ích Khiêm, Hải Châu
                                Đà Nẵng</li>
                            <li class="my-2"><span class="fa mr-1 fa-phone"></span>1900 1010</li>
                            <li class="my-2"><span class="fa mr-1 fa-phone"></span>1900 1001</li>
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

    </body>
</html>