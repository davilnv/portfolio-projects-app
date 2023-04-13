<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <link href="<c:url value="/static/node_modules/bootstrap-table/dist/bootstrap-table.css"/>"
          rel="stylesheet"
    >

    <link href="<c:url value="/static/node_modules/bootstrap-icons/font/bootstrap-icons.css"/>"
          rel="stylesheet"
    >

    <script src="<c:url value="/static/node_modules/jquery/dist/jquery.js"/>"></script>
</head>
    <body>
        <%@ include file="../common/navigation.jspf" %>

        <div id="toolbar">
            <button
                    type="button"
                    class="btn btn-outline-secondary"
                    data-bs-toggle="modal"
                    data-bs-target="#modalAddProject"
            >
                Cadastrar Projeto
            </button>
        </div>

        <div class="modal fade" id="modalAddProject" tabindex="-1" role="dialog" aria-labelledby="modalAddProjectLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalAddProjectLabel">Cadastrar Projeto</h5>
                    </div>
                    <div class="modal-body">
                        <%--@elvariable id="addProjectSucess" type="java.lang.Boolean"--%>
                        <c:if test="${addProjectSucess}">
                            <div>Cadastrado com sucesso!</div>
                        </c:if>

                        <c:url var="add_project_url" value="/project/addProject" />
                        <%--@elvariable id="project" type="br.com.davilnv.portfolio.model.ProjectModel"--%>
                        <form:form id="projectForm" action="${add_project_url}" method="post" modelAttribute="project">
                            <div class="form-group">
                                <form:label path="name" class="col-form-label">Nome: </form:label>
                                <form:input id="name" type="text" path="name" class="form-control" />
                            </div>
                            <div class="form-group">
                                <form:label path="description" class="col-form-label">Descrição:</form:label>
                                <form:textarea id="description" path="description" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <form:label path="initialDate" class="col-form-label">Dt. Início:</form:label>
                                <form:input id="initialDate" type="date" path="initialDate" class="form-control" />
                            </div>
                            <div class="form-group">
                                <form:label path="forecastDate" class="col-form-label">Dt. Previsão:</form:label>
                                <form:input  id="forecastDate" type="date" path="forecastDate" class="form-control" />
                            </div>
                            <div class="form-group">
                                <form:label path="finalDate" class="col-form-label">Dt. Final:</form:label>
                                <form:input  id="finalDate" type="date" path="finalDate" class="form-control" />
                            </div>
                            <div class="form-group">
                                <form:label path="status" class="col-form-label">Status:</form:label>
                                <form:select id="status" path="status" class="form-select" aria-label="status-select">
                                    <form:option value="NONE">Selecione</form:option>
                                    <form:option value="EM-ANALISE">Em Análise</form:option>
                                    <form:option value="ANALISE-REALIZADA">Análise Realizada</form:option>
                                    <form:option value="ANALISE-APROVADA">Análise Aprovada</form:option>
                                    <form:option value="INICIADO">Iniciado</form:option>
                                    <form:option value="PLANEJADO">Planejado</form:option>
                                    <form:option value="EM-ANDAMENTO">Em Andamento</form:option>
                                    <form:option value="ENCERRADO">Encerrado</form:option>
                                    <form:option value="CANCELADO">Cancelado</form:option>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <form:label path="budget" class="col-form-label">Orçamento R$:</form:label>
                                <form:input id="budget" type="number" path="budget" class="form-control" />
                            </div>
                            <div class="form-group">
                                <form:label path="risk" class="col-form-label">Risco:</form:label>
                                <form:select id="risk" path="risk" class="form-select" aria-label="risk-select">
                                    <form:option value="NONE">Selecione um</form:option>
                                    <form:option value="BAIXO">Baixo Risco</form:option>
                                    <form:option value="MEDIO">Médio Risco</form:option>
                                    <form:option value="ALTO">Alto Risco</form:option>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <form:label path="manager" class="col-form-label">Gerente:</form:label>
                                <form:select id="manager" path="manager" class="form-select" aria-label="manager-select">
                                    <form:option value="${null}">Selecione um</form:option>
                                    <c:forEach items="${managers}" var="manager">
                                        <form:option value="${manager}">${manager.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </form:form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                        <button type="submit" value="submit" class="btn btn-primary" form="projectForm">Cadastrar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="container mt-4">
            <table
                    class="table table-striped"
                    id="project-table"
                    data-toggle="table"
                    data-toolbar="#toolbar"
                    data-show-footer="true"
                    data-side-pagination="server"
                    data-id-field="id"
            >
                <caption>Projetos</caption>
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Dt. Início</th>
                    <th scope="col">Dt. Previsão</th>
                    <th scope="col">Dt. Fim</th>
                    <th scope="col">Descrição</th>
                    <th scope="col">Status</th>
                    <th scope="col">Orçamento</th>
                    <th scope="col">Risco</th>
                    <th scope="col">Gerente</th>
                    <th scope="col">Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${projects}" var="project">
                    <tr>
                        <td>${project.id}</td>
                        <td>${project.name}</td>
                        <td><fmt:formatDate value="${project.initialDate}" pattern="dd/MM/yyyy" /></td>
                        <td><fmt:formatDate value="${project.forecastDate}" pattern="dd/MM/yyyy" /></td>
                        <td><fmt:formatDate value="${project.finalDate}" pattern="dd/MM/yyyy" /></td>
                        <td>${project.description}</td>
                        <td>${project.status}</td>
                        <td>${project.budget}</td>
                        <td>${project.risk}</td>
                        <td>${project.manager.name}</td>
                        <td>
                            <button type="button" class="btn btn-primary"><i class="bi bi-eye"></i></button>
                            <button
                                    type="button"
                                    class="btn btn-secondary btnUpdate"
                                    data-bs-toggle="modal"
                                    data-bs-target="#modalAddProject"
                            >
                                <i class="bi bi-pencil"></i>
                            </button>
                            <button type="button" class="btn btn-danger"><i class="bi bi-trash"></i></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <script>
            $(document).ready(function(){

                // code to read selected table row cell data (values).
                $("#project-table").on('click','.btnUpdate',function(){
                    // get the current row
                    const currentRow=$(this).closest("tr");

                    const id =currentRow.find("td:eq(0)").text();

                    $.get("/project/updateProject?id="+id, function (data) {
                        $("#name").val(currentRow.find("td:eq(1)").text());
                        $("#initialDate").val(currentRow.find("td:eq(2)").text());
                        $("#forecastDate").val(currentRow.find("td:eq(3)").text());
                        $("#finalDate").val(currentRow.find("td:eq(4)").text());
                        $("#description").val(currentRow.find("td:eq(5)").text());
                        $("#status").val(currentRow.find("td:eq(6)").text());
                        $("#budget").val(currentRow.find("td:eq(7)").text());
                        $("#risk").val(currentRow.find("td:eq(8)").text());
                        $("#manager").val(currentRow.find("td:eq(9)").text());
                    });

                    // alert(id)
                });
            });
        </script>

        <script src="<c:url value="/static/node_modules/bootstrap/dist/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/static/node_modules/bootstrap-table/dist/bootstrap-table.js"/>"></script>
    </body>
</html>