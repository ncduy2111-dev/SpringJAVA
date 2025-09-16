<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Update Product</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <!-- Header -->
                <jsp:include page="../layout/header.jsp" />

                <div id="layoutSidenav">
                    <!-- Sidebar -->
                    <div id="layoutSidenav_nav">
                        <jsp:include page="../layout/sidebar.jsp" />
                    </div>

                    <!-- Main Content -->
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Manager Order</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Order</li>
                                </ol>
                                <div class="row justify-content-center">
                                    <div class="col-lg-8 col-md-10 col-12">
                                        <h2 class="mb-4 text-center ">Update Order</h2>

                                        <hr class="my-4">

                                        <form:form method="post" action="/admin/order/update" modelAttribute="newOrder"
                                            enctype="multipart/form-data">
                                            <div class="input-group mb-3" style="display:none;">
                                                <form:input path="id" class="form-control" />
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="mb-3 ">
                                                        <label class="form-label">User:</label>
                                                        <input path="name" class="form-control"
                                                            value="${newOrder.user.fullName}" disabled="true" />
                                                    </div>
                                                </div>

                                                <div class="col-md-6">
                                                    <label class="form-label">Status:</label>
                                                    <form:select class="form-select" path="status">
                                                        <form:option value="PENDING">PENDING</form:option>
                                                        <form:option value="PROCESSING">PROCESSING</form:option>
                                                        <form:option value="COMPLETED">COMPLETED</form:option>
                                                        <form:option value="CANCELLED">CANCELLED</form:option>
                                                    </form:select>
                                                </div>
                                            </div>

                                            <div class=" mt-4 text-center">
                                                <button type="submit" class="btn btn-warning px-5">Update</button>
                                            </div>

                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </main>

                        <!-- Footer -->
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>

                <!-- Scripts -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="/js/scripts.js"></script>
            </body>

            </html>