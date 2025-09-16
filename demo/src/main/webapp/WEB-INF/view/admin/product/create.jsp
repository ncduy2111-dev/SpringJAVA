<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <title>Create Product</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        $("#avatarFile").change(function (e) {
                            const file = e.target.files[0];
                            if (file) {
                                const imgURL = URL.createObjectURL(file);
                                $("#avatarPreview")
                                    .attr("src", imgURL)
                                    .css("display", "block");
                            }
                        });
                    });
                </script>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
                                    <li class="breadcrumb-item active">Product</li>
                                </ol>
                                <div class="row justify-content-center">
                                    <div class="col-lg-8 col-md-10 col-12">
                                        <h2 class="mb-4 text-center ">Create Product</h2>

                                        <form:form method="post" action="/admin/product" modelAttribute="newProduct"
                                            enctype="multipart/form-data">

                                            <div class="row">
                                                <!-- Cột 1 -->
                                                <div class="col-md-6">
                                                    <div class="mb-3 ">
                                                        <c:set var="nameError">
                                                            <form:errors path="name" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Name:</label>
                                                        <form:input path="name"
                                                            class="form-control ${not empty nameError ? 'is-invalid' : ''}" />
                                                        ${nameError}
                                                    </div>
                                                </div>

                                                <!-- Cột 2 -->
                                                <div class="col-md-6">
                                                    <div class="mb-3 ">
                                                        <c:set var="priceError">
                                                            <form:errors path="price" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Price:</label>
                                                        <form:input path="price"
                                                            class="form-control ${not empty priceError ? 'is-invalid' : ''}" />
                                                        ${priceError}
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="mb-3 ">
                                                <c:set var="detailDescError">
                                                    <form:errors path="detailDesc" cssClass="invalid-feedback" />
                                                </c:set>
                                                <label class="form-label">Detail description:</label>
                                                <form:input path="detailDesc"
                                                    class="form-control ${not empty detailDescError ? 'is-invalid' : ''}" />
                                                ${detailDescError}
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 ">
                                                    <div class="mb-3 ">
                                                        <c:set var="shortDescError">
                                                            <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Short description:</label>
                                                        <form:input path="shortDesc"
                                                            class="form-control ${not empty shortDescError ? 'is-invalid' : ''}" />
                                                        ${shortDescError}
                                                    </div>

                                                    <label class="form-label">Factory:</label>
                                                    <form:select class="form-select" path="factory">
                                                        <form:option value="APPLE">Apple(MacBook)</form:option>
                                                        <form:option value="ASUS">Asus</form:option>
                                                        <form:option value="LENOVO">Lenovo</form:option>
                                                        <form:option value="DELL">Dell</form:option>
                                                        <form:option value="LG">LG</form:option>
                                                        <form:option value="ACER">Acer</form:option>
                                                    </form:select>

                                                    <div class="mb-3">
                                                        <label for="avatarFile" class="form-label">Image:</label>
                                                        <input class="form-control" type="file" id="avatarFile"
                                                            name="imageProductFile" accept=".png, .jpg, .jpeg" />
                                                    </div>

                                                    <div class="mb-3 d-flex justify-content-center">
                                                        <img id="avatarPreview"
                                                            style="max-height: 250px; display: none;"
                                                            class="img-fluid rounded shadow mx-auto d-block" />
                                                    </div>
                                                </div>

                                                <div class="col-md-6">
                                                    <div class="mb-3 ">
                                                        <c:set var="quantityError">
                                                            <form:errors path="quantity" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Quantity:</label>
                                                        <form:input path="quantity"
                                                            class="form-control ${not empty quantityError ? 'is-invalid' : ''}" />
                                                        ${quantityError}
                                                    </div>

                                                    <div class="mb-3 ">
                                                        <c:set var="targetError">
                                                            <form:errors path="target" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Target:</label>
                                                        <form:input path="target"
                                                            class="form-control ${not empty targetError ? 'is-invalid' : ''}" />
                                                        ${targetError}
                                                    </div>
                                                </div>
                                            </div>

                                            <div class=" mt-4 text-center">
                                                <button type="submit" class="btn btn-primary px-5">Create</button>
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