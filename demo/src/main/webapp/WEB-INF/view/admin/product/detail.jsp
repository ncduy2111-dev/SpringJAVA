<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="" />
            <meta name="author" content="" />
            <title>Detail Product</title>
            <link href="/css/styles.css" rel="stylesheet" />
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
                            <h1 class="mt-4">Manager Product</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active">User</li>
                            </ol>
                            <div class="row">
                                <div class="col-md-10 col-12 mx-auto">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <h3>Product detail with id = ${product.id}</h3>
                                    </div>

                                    <hr class="my-4">

                                    <div class="card" style="width: 60%;">
                                        <img class="card-img-top" src="/images/product/${product.image}"
                                            alt="Card image cap" \>
                                        <div class="card-header">
                                            Product Information
                                        </div>
                                        <ul class="list-group list-group-flush">


                                            <li class="list-group-item">ID: ${product.id}</li>
                                            <li class="list-group-item">Name: ${product.name}</li>
                                            <li class="list-group-item">Price: ${product.price}</li>
                                            <li class="list-group-item">Detail Description: ${product.detailDesc}</li>
                                            <li class="list-group-item">Short Description: ${product.shortDesc}</li>
                                            <li class="list-group-item">Quantity: ${product.quantity}</li>
                                            <li class="list-group-item">Target: ${product.target}</li>
                                            <li class="list-group-item">Factory: ${product.factory}</li>
                                            <li class="list-group-item">Sold: ${product.sold}</li>
                                        </ul>
                                    </div>

                                    <a href="/admin/product" type="button" class="btn btn-success mt-3">Back</a>
                                </div>
                            </div>
                        </div>
                    </main>

                    <!-- Footer -->
                    <jsp:include page="../layout/footer.jsp" />
                </div>
            </div>

            <!-- Scripts -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
            <script src="/js/scripts.js"></script>
        </body>

        </html>