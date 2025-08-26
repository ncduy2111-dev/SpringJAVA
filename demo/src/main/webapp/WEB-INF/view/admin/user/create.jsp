<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <title>Create User</title>
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
                                <div class="row justify-content-center">
                                    <div class="col-lg-8 col-md-10 col-12">
                                        <h2 class="mb-4 text-center ">Create User</h2>

                                        <form:form method="post" action="/admin/user/" modelAttribute="newUser"
                                            enctype="multipart/form-data">

                                            <div class="row">
                                                <!-- Cột 1 -->
                                                <div class="col-md-6">
                                                    <div class="mb-3 ">
                                                        <label class="form-label">Email:</label>
                                                        <form:input path="email" class="form-control " type="email" />
                                                    </div>

                                                    <div class="mb-3 ">
                                                        <label class="form-label">Phone number:</label>
                                                        <form:input path="phone" class="form-control " />
                                                    </div>
                                                </div>

                                                <!-- Cột 2 -->
                                                <div class="col-md-6">
                                                    <div class="mb-3 ">
                                                        <label class="form-label">Password:</label>
                                                        <form:password path="password" class="form-control " />
                                                    </div>

                                                    <div class="mb-3 ">
                                                        <label class="form-label">Fullname:</label>
                                                        <form:input path="fullName" class="form-control " />
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="mb-3 ">
                                                <label class="form-label">Address:</label>
                                                <form:input path="address" class="form-control " />
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 ">
                                                    <label class="form-label">Role:</label>
                                                    <form:select class="form-select" path="role.name">
                                                        <form:option value="ADMIN">ADMIN</form:option>
                                                        <form:option value="USER">USER</form:option>
                                                    </form:select>
                                                </div>

                                                <div class="col-md-6">
                                                    <div class="mb-3">
                                                        <label for="avatarFile" class="form-label">Avatar:</label>
                                                        <input class="form-control" type="file" id="avatarFile"
                                                            name="imgAvatar" accept=".png, .jpg, .jpeg" />
                                                    </div>
                                                </div>

                                                <div class="col-12 mb-3">
                                                    <div class="d-flex justify-content-center">
                                                        <img id="avatarPreview" alt="avatar preview"
                                                            style="max-height: 250px; display: none;"
                                                            class="img-fluid rounded shadow" />
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