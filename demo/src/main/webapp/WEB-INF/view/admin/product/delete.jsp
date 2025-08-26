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
                <title>Delete User</title>
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
                                <h1 class="mt-4">Manager Order</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">User</li>
                                </ol>
                                <div class="row">
                                    <div class="col-md-10 col-12 mx-auto">
                                        <h3>Delete the user with id = ${id}</h3>

                                        <hr class="my-4">

                                        <div class="alert alert-danger" role="alert">
                                            Are you sure to delete this user?
                                        </div>

                                        <form:form method="post" action="/admin/user/delete" modelAttribute="newUser">
                                            <div class="input-group mb-3" style="display:none;">
                                                <form:input path="id" class="form-control" value="${id}" />
                                            </div>

                                            <form:button type="submit" class="btn btn-danger">Confirm</form:button>
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
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>
            </body>

            </html>