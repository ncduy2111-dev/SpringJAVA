<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <title>Login - LaptopShop</title>
                    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
                    <link rel="stylesheet" href="/client/css/styleLogin.css">
                    </head>

                    <body>
                        <div class="container">
                            <!-- Sign In Form -->
                            <div class="form-box login">
                                <h2 class="title animation" style="--i: 10; --j: 0">Sign In</h2>

                                <div class="social-login">
                                    <button><i class='bx bxl-google'></i></button>
                                    <button><i class='bx bxl-facebook'></i></button>
                                    <button><i class='bx bxl-github'></i></button>
                                    <button><i class='bx bxl-linkedin'></i></button>
                                </div>

                                <form method="post" action="/login">
                                    <c:if test="${param.error!= null}">
                                        <div class="my-2" style="color: red;">Invalid email or
                                            password.</div>
                                    </c:if>
                                    <c:if test="${param.logout!= null}">
                                        <div class="my-2" style="color: green;">Logout
                                            successful.
                                        </div>
                                    </c:if>
                                    <div class="input-box animation" style="--i: 11; --j: 1">
                                        <input id="inputEmail" type="email" name="username" placeholder=" " />
                                        <label>Email</label>
                                        <i class="bx bxs-envelope"></i>
                                    </div>

                                    <div class="input-box animation" style="--i: 12; --j: 2">
                                        <input id="inputPassword" type="password" name="password" placeholder=" " />
                                        <label>Password</label>
                                        <i class="bx bxs-lock-alt"></i>
                                    </div>

                                    <div>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    </div>

                                    <a href="#" class="forgot-pass">Forgot Your Password?</a>

                                    <button type="submit" class="btn animation" style="--i: 13; --j: 3">
                                        Sign In
                                    </button>
                                </form>
                            </div>

                            <!-- Overlay Right Section -->
                            <div class="overlay">
                                <div class="overlay-panel">
                                    <h2>Hello, Friend!</h2>
                                    <p>Đăng ký thông tin cá nhân của bạn để sử dụng tất cả các tính năng của trang web
                                    </p>
                                    <a href="${pageContext.request.contextPath}/register" class="btn-outline">Sign
                                        Up</a>
                                </div>
                            </div>
                        </div>
                    </body>