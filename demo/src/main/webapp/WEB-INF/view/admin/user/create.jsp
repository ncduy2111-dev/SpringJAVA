<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Create User</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h2>Create User</h2>
                            <hr class="my-4">

                            <form:form method="post" action="/admin/user/" modelAttribute="newUser">

                                <h5 class="fw-normal">Email:</h5>
                                <div class="input-group mb-3">
                                    <form:input path="email" class="form-control" type="email" />
                                </div>

                                <h5 class="fw-normal">Password:</h5>
                                <div class="input-group mb-3">
                                    <form:password path="password" class="form-control" />
                                </div>

                                <h5 class="fw-normal">Phone number:</h5>
                                <div class="input-group mb-3">
                                    <form:input path="phone" class="form-control" />
                                </div>

                                <h5 class="fw-normal">Fullname:</h5>
                                <div class="input-group mb-3">
                                    <form:input path="fullName" class="form-control" />
                                </div>

                                <h5 class="fw-normal">Address:</h5>
                                <div class="input-group mb-3">
                                    <form:input path="address" class="form-control" />
                                </div>

                                <button type="submit" class="btn btn-primary">Create</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>

            </html>