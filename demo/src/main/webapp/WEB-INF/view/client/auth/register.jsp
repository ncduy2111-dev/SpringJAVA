<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Login & Signup Form</title>
                        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                            rel="stylesheet">

                        <link rel="stylesheet" href="/client/css/styleLogin.css">
                    </head>

                    <body>
                        <div class="container">
                            <!-- Sign In Form -->
                            <div class="form-box login">
                                <h2 class="title animation" style="--i: 10; --j: 0">Create Account</h2>

                                <form:form method="post" modelAttribute="registerUser" action="/register">
                                    <c:set var="errorFirstName">
                                        <form:errors path="firstName" cssClass="invalid-feedback" />
                                    </c:set>
                                    <c:set var="errorConfirmPassword">
                                        <form:errors path="confirmPassword" cssClass="invalid-feedback" />
                                    </c:set>
                                    <c:set var="errorPassword">
                                        <form:errors path="password" cssClass="invalid-feedback" />
                                    </c:set>
                                    <c:set var="errorEmail">
                                        <form:errors path="email" cssClass="invalid-feedback" />
                                    </c:set>

                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="input-box animation" style="--i: 11; --j: 1">
                                                <form:input type="text"
                                                    class="${not empty errorFirstName ? 'is-invalid' : ''}"
                                                    id="inputFirstName" path="firstName" placeholder="." />
                                                <label>First name</label>
                                                <i class='bx bx-user'></i>
                                                ${errorFirstName}
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-box animation" style="--i: 11; --j: 1">
                                                <form:input id=" inputLastName" type="text" placeholder="."
                                                    path="lastName" />
                                                <label>Last name</label>
                                                <i class='bx bx-user'></i>
                                                ${errorLastName}
                                            </div>
                                        </div>
                                    </div>

                                    <div class=" input-box animation" style="--i: 11; --j: 1">
                                        <form:input class="${not empty errorEmail ? 'is-invalid' : ''}" id="inputEmail"
                                            type="email" placeholder="." path="email" />
                                        <label>Email</label>
                                        <i class='bx bxs-envelope'></i>
                                        ${errorEmail}
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="input-box animation" style="--i: 12; --j: 2">
                                                <form:input class="${not empty errorPassword ? 'is-invalid' : ''}"
                                                    id="inputPassword" type="password" placeholder="."
                                                    path="password" />
                                                <label>Password</label>
                                                <i class="bx bxs-lock-alt"></i>
                                                ${errorPassword}
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-box animation" style="--i: 12; --j: 2">
                                                <form:input
                                                    class="form-control ${not empty errorConfirmPassword ? 'is-invalid' : ''}"
                                                    id="inputPasswordConfirm" type="password" placeholder="."
                                                    path="confirmPassword" />
                                                <label>Confirm Password</label>
                                                <i class="bx bxs-lock-alt"></i>
                                                ${errorConfirmPassword}
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row mb-3 align-items-center">
                                        <div class="col-md-6 d-flex justify-content-start">
                                            <a href="/login" class="btn-lg" style="--i: 13; --j: 3">
                                                <i class='bx bx-chevron-left'></i>Quay lại đăng nhập
                                            </a>
                                        </div>
                                        <div class="col-md-6 d-flex justify-content-end">
                                            <button type="submit" class="btn animation" style="--i: 13; --j: 3">
                                                Hoàn tất đăng ký
                                            </button>
                                        </div>
                                    </div>

                                </form:form>
                            </div>
                        </div>
                    </body>

                    </html>