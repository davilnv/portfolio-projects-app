<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt_br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Portfolio Project Manager</title>

        <link href="<c:url value="/static/node_modules/bootstrap/dist/css/bootstrap.min.css"/>"
              rel="stylesheet"
        >
    </head>
    <body>
        <%@ include file="common/navigation.jspf" %>
        <div class="container row d-flex justify-content-center">
            <h1>Gerenciador de Portfólio de Projetos</h1>
            <h5>Este sistema consiste em gerenciar o portfólio de projetos de uma empresa!</h5>
        </div>
    </body>

    <script src="<c:url value="/static/node_modules/bootstrap/dist/js/bootstrap.min.js"/>"></script>
</html>