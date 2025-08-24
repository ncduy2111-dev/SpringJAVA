<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Delete User</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            </head>

            <body>
                <div class="container mt-5">
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
            </body>

            </html>