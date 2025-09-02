<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

                    <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
                        <!-- Navbar Brand-->
                        <a class="navbar-brand ps-3" href="/admin">LaptopShop</a>
                        <!-- Sidebar Toggle-->
                        <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle"
                            href="#!"><i class="fas fa-bars"></i></button>
                        <!-- Navbar Search-->

                        <!-- Navbar-->
                        <div class="d-flex align-items-center ms-auto text-white">
                            <span class="me-2">Welcome, ${pageContext.request.userPrincipal.name}</span>
                            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle text-white" id="navbarDropdown" href="#"
                                        role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        <i class="fas fa-user fa-fw"></i>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                        <li><a class="dropdown-item" href="#">Settings</a></li>

                                        <form action="/logout" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <button type="submit" class="dropdown-item">Đăng
                                                xuất</button>
                                        </form>
                                    </ul>
                                </li>
                            </ul>
                        </div>

                    </nav>