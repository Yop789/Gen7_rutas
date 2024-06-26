<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.lopez.app.rutas.models.*" %>

<%
//recuperamos la lista de choferes que seteamos en el request desde el servlet
List<Camion> camiones =  (List<Camion>) request.getAttribute("camiones");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  </head>
  <body>

  <%@ include file="Header.jsp" %>


    <div class="container">
      <div class="row">
        <div class="col-6">
          <h2>listado de Camiones</h2>
        </div>
        <div class="col-6">
          <a
            href="<%=request.getContextPath()%>/camiones/alta"
            class="btn btn-success"
            >Alta camion</a
          >
        </div>
      </div>

      <div class="row">
        <div class="col-12">
          <div class="table-responsive">
            <table
              class="table table-bordered table-straped"
              width="100%"
              cellspacing="0"
            >
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Matricula</th>
                  <th>Tipo de camion</th>
                  <th>Modelo</th>
                  <th>Marca</th>
                  <th>Capacidad</th>
                  <th>Kilómetros</th>
                  <th></th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>

              <tbody>
                <% for(Camion c : camiones){ %>
                <tr>
                  <td><%=c.getId()%></td>
                  <td><%=c.getMatricula()%></td>
                  <td><%=c.getTipoCamion()%></td>
                  <td><%=c.getModelo()%></td>
                  <td><%=c.getMarca()%></td>
                  <td><%=c.getCapacidad()%></td>
                  <td><%=c.getKilometros()%></td>
                  <td>
                    <a
                      href="<%=request.getContextPath()%>/camiones/detalle?id=<%=c.getId()%>"
                      class="btn btn-success"
                      >detalle</a
                    >
                  </td>

                  <td>
                    <a
                      href="<%=request.getContextPath()%>/camiones/editar?id=<%=c.getId()%>"
                      class="btn btn-primary"
                      >editar</a
                    >
                  </td>

                  <td>
                    <a
                      href="<%=request.getContextPath()%>/camiones/eliminar?id=<%=c.getId()%>"
                      class="btn btn-danger"
                      >eliminar</a
                    >
                  </td>
                </tr>
                <%}%>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
