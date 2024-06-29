<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.lopez.app.rutas.models.*" %>

<%
//recuperamos la lista de choferes que seteamos en el request desde el servlet
Map<String,String> errores = (Map<String, String>) request.getAttribute("errores");
List<Ruta> rutas =  (List<Ruta>) request.getAttribute("rutas");
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
    
          <br>
          <% if(errores != null && errores.size() > 0){ %>
            <ul class="alert alert-danger mx-5 px-5">

              <% for(String e : errores.values()){ %>
                <li><%=e%></li>
              <% } %>

            </ul>
          <% } %>

    <div class="container">
      <div class="row">
        <div class="col-6">
          <h2>listado Rutas</h2>
        </div>
        <div class="col-6">
          <a
            href="<%=request.getContextPath()%>/rutas/alta"
            class="btn btn-success"
            >Alta Rutas</a
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
                  <th>ID</th>
                  <th>ID_CAMION</th>
                  <th>ID_DIRECCION_ORIGEN</th>
                  <th>ID_DIRECCION_DESTINO</th>
                  <th>ID_CHOFER</th>
                  <th>DISTANCIA</th>
                  <th>FECHA_SALIDA</th>
                  <th>FECHA_LLEGADA_ESTIMADA</th>
                  <th>FECHA_LLEGADA_REAL</th>
                  <th>A_TIEMPO</th>
                </tr>
              </thead>

              <tbody>
                <% for(Ruta c : rutas){ %>
                <tr>
                  <td><%=c.getId()%></td>
                  <td><%=c.getCamionId()%></td>
                  <td><%=c.getChoferId()%></td>
                  <td><%=c.getDireccionOriginalId()%></td>
                  <td><%=c.getDireccionDestinoId()%></td>
                  <td><%=c.getDiatancio()%></td>
                  <td><%=c.getFechaSalida()%></td>
                  <td><%=c.getFechaLlegadaEstimada()%></td>
                  <td><%=c.getFechaLlegadaReal()%></td>
                  <td><%=c.getaTiempo()%></td>                 
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
