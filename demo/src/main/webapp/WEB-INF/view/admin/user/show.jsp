<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>User Detail</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body>
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-10 col-12 mx-auto">
                        <div class="d-flex justify-content-between align-items-center">
                            <h3>User detail with id = ${user.id}</h3>
                        </div>

                        <hr class="my-4">

                        <div class="card" style="width: 60%;">
                            <div class="card-header">
                                User Information
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">ID: ${user.id}</li>
                                <li class="list-group-item">Email: ${user.email}</li>
                                <li class="list-group-item">Fullname: ${user.fullName}</li>
                                <li class="list-group-item">Password: ${user.password}</li>
                                <li class="list-group-item">Address: ${user.address}</li>
                            </ul>
                        </div>

                        <a href="/admin/user" type="button" class="btn btn-success mt-3">Back</a>
                    </div>
                </div>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>