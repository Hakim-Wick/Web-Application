<%-- 
    Document   : All_Jobs
    Created on : Apr 28, 2022, 11:33:10 PM
    Author     : thinh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="keywords" content="Bootstrap, Landing page, Template, Registration, Landing">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/line-icons.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/css/owl.theme.default.css">
        <link rel="stylesheet" href="assets/css/slicknav.min.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <header id="home" class="hero-area">
            <jsp:include page="Menu.jsp"></jsp:include>
            </header>
            <div class="page-header">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="inner-header">
                                <h3>Find Job</h3>
                            </div>
                            <div class="job-search-form">
                                <form action="searchjob" method="post">
                                    <div class="row">
                                        <div class="col-lg-5 col-md-5 col-xs-12">
                                            <div class="form-group">
                                                <input class="form-control" type="text" name="tittle" placeholder="Job Title">
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-5 col-xs-12">
                                            <div class="form-group">
                                                <div class="search-category-container">
                                                    <label class="styled-select">
                                                        <select name="location">
                                                            <option value="NONE">Locations</option>
                                                            <option value="HA NOI">HA NOI</option>
                                                            <option value="HO CHI MINH">HO CHI MINH</option>
                                                            <option value="HAI PHONG">HAI PHONG</option>
                                                            <option value="CAN THO">CAN THO</option>
                                                            <option value="DA NANG">DA NANG</option>
                                                            <option value="BIEN HOA">BIEN HOA</option>
                                                            <option value="THU DUC">THU DUC</option>
                                                            <option value="HAI DUONG">HAI DUONG</option>
                                                            <option value="THUAN AN">THUAN AN</option>
                                                            <option value="HUE">HUE</option>
                                                        </select>
                                                    </label>
                                                </div>
                                                <i class="lni-map-marker"></i>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-5 col-xs-12">
                                            <div class="form-group">
                                                <div class="search-category-container">
                                                    <label class="styled-select">
                                                        <select name="category">
                                                            <option value="0">All Categories</option>
                                                        <c:forEach items="${allCategory}" var="o">
                                                            <option value="${o.id}">${o.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </label>
                                            </div>
                                            <i class="lni-layers"></i>
                                        </div>
                                    </div>
                                    <div class="col-lg-1 col-md-2 col-xs-12">
                                        <button type="submit" class="button"><i class="lni-search"></i></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section id="job-listings" class="section">
            <div class="container">
                <div class="section-header">
                    <h2 class="section-title">Featured Jobs</h2>
                    <p>Ghi gi vao day thi ghi</p>
                </div>
                <div class="row">
                    <div class="col-xl-3 col-lg-3 col-md-4">
                        <div class="row">
                            <div class="col-12">
                                <div class="small-section-tittle2 mb-45">
                                    <div class="ion"> <svg 
                                            xmlns="http://www.w3.org/2000/svg"
                                            xmlns:xlink="http://www.w3.org/1999/xlink"
                                            width="20px" height="12px">
                                        <path fill-rule="evenodd"  fill="rgb(27, 207, 107)"
                                              d="M7.778,12.000 L12.222,12.000 L12.222,10.000 L7.778,10.000 L7.778,12.000 ZM-0.000,-0.000 L-0.000,2.000 L20.000,2.000 L20.000,-0.000 L-0.000,-0.000 ZM3.333,7.000 L16.667,7.000 L16.667,5.000 L3.333,5.000 L3.333,7.000 Z"/>
                                        </svg>
                                    </div>
                                    <h4>Filter Jobs</h4>
                                </div>
                            </div>
                        </div>
                        <form action="filterjob" method="post">
                            <div class="job-category-listing mb-50">
                                <div class="single-listing">
                                    <div class="small-section-tittle2">
                                        <h4>Job Category</h4>
                                    </div>
                                    <div class="select-job-items2">
                                        <select name="category">
                                            <option value="0">All Category</option>
                                            <c:forEach items="${allCategory}" var="o">
                                                <option value="${o.id}">${o.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="select-Categories pt-80 pb-50">
                                        <div class="small-section-tittle2">
                                            <h4>Job Type</h4>
                                        </div>
                                        <label class="container">Full Time
                                            <input type="checkbox" name="type1" value="FULL TIME" checked>
                                            <span class="checkmark"></span>
                                        </label>
                                        <label class="container">Part Time
                                            <input type="checkbox" name="type2" value="PART TIME" checked>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="single-listing">
                                    <div class="small-section-tittle2">
                                        <h4>Job Location</h4>
                                    </div>
                                    <div class="select-job-items2">
                                        <select name="location">
                                            <option value="NONE">Anywhere</option>
                                            <option value="HA NOI">HA NOI</option>
                                            <option value="HO CHI MINH">HO CHI MINH</option>
                                            <option value="HAI PHONG">HAI PHONG</option>
                                            <option value="CAN THO">CAN THO</option>
                                            <option value="DA NANG">DA NANG</option>
                                            <option value="BIEN HOA">BIEN HOA</option>
                                            <option value="THU DUC">THU DUC</option>
                                            <option value="HAI DUONG">HAI DUONG</option>
                                            <option value="THUAN AN">THUAN AN</option>
                                            <option value="HUE">HUE</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="submit" class="btn-dark" style="margin-top: 20px;font-size: 15px;" value="Filter"/>
                            </div>
                        </form>
                    </div>
                    <div class="col-xl-9 col-lg-9 col-md-8">
                        <span style="font-weight: bold; font-size: 17px;">${count} Jobs found</span>
                        <div class="row" style="margin-top: 40px">
                            <c:forEach items="${listJ}" var="o">
                                <div class="col-xl-6 col-lg-6 col-md-12">
                                    <a class="job-listings-featured" href="detail?JobID=${o.jId}">
                                        <div class="row">
                                            <div class="col-lg-6 col-md-6 col-xs-12">
                                                <div class="job-company-logo">
                                                    <img src="${o.logo}" style="max-width:40px;max-height:40px;" alt="">
                                                </div>
                                                <div class="job-details">
                                                    <h3>${o.jName}</h3>
                                                    <span class="company-neme">${o.company}</span>
                                                    <div class="tags">
                                                        <span><i class="lni-map-marker"></i>${o.location}</span>
                                                        <span><i class=""></i>$${o.salary}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-xs-12 text-right">
                                                <div class="tag-type">
                                                    <span class="full-time">Full Time</span>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        <ul class="pagination">
                            <c:forEach begin="1" end="${end}" var="i">
                                <li class="${tag == i?"active":""}"><a href="alljob?index=${i}">${i}</a></li>
                                </c:forEach>
                        </ul>
                    </div>            
                </div>
            </div>
        </section>
        <jsp:include page="Footer.jsp"></jsp:include>
        <a href="#" class="back-to-top">
            <i class="lni-arrow-up"></i>
        </a>
        <div id="preloader">
            <div class="loader" id="loader-1"></div>
        </div>

        <script src="assets/js/jquery-min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/color-switcher.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/jquery.slicknav.js"></script>
        <script src="assets/js/jquery.counterup.min.js"></script>
        <script src="assets/js/waypoints.min.js"></script>
        <script src="assets/js/form-validator.min.js"></script>
        <script src="assets/js/contact-form-script.js"></script>
        <script src="assets/js/main.js"></script>
    </body>
</html>