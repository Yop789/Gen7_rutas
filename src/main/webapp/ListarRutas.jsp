<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.lopez.app.rutas.models.*" %>

<%
//recuperamos la lista de choferes que seteamos en el request desde el servlet
Map<String,String> errores = (Map<String, String>) request.getAttribute("errores");
List<Ruta> rutas =  (List<Ruta>) request.getAttribute("rutas");
List<RutaCompleta> rutasCompletas = (List<RutaCompleta>) request.getAttribute("rutasCompleta");

%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Listar rutas</title>
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
            <table class="table table-bordered table-striped" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>CAMIÓN</th>
                        <th>DIRECCIÓN ORIGEN</th>
                        <th>DIRECCIÓN DESTINO</th>
                        <th>CHOFER</th>
                        <th>DISTANCIA</th>
                        <th>FECHA SALIDA</th>
                        <th>FECHA LLEGADA ESTIMADA</th>
                        <th>FECHA LLEGADA REAL</th>
                        <th>A TIEMPO</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(RutaCompleta c : rutasCompletas) { %>
                    <tr>
                        <td><%= c.getRuta().getId() %></td>
                        <td><%= c.getCamion().getMatricula() %></td>
                        <td><% 
                            Direccion direccionOrigen = c.getDireccionOrigen();
                            if (direccionOrigen != null) {
                                String direccionCompleta = "";
                                if (direccionOrigen.getCalle() != null) {
                                    direccionCompleta += direccionOrigen.getCalle() + " ";
                                }
                                if (direccionOrigen.getNumero() != null) {
                                    direccionCompleta += direccionOrigen.getNumero() + " ";
                                }
                                if (direccionOrigen.getColonia() != null) {
                                    direccionCompleta += direccionOrigen.getColonia() + " ";
                                }
                                if (direccionOrigen.getCp() != null) {
                                    direccionCompleta += direccionOrigen.getCp() + " ";
                                }
                                if (direccionOrigen.getCiudad() != null) {
                                    direccionCompleta += direccionOrigen.getCiudad() + " ";
                                }
                                if (direccionOrigen.getEstado() != null) {
                                    direccionCompleta += direccionOrigen.getEstado();
                                }
                                out.print(direccionCompleta.trim());
                            }
                            %>
                        </td>
                        <td>
                            <% 
                            Direccion direccionDestino = c.getDireccionDestino();
                            if (direccionDestino != null) {
                                String direccionCompleta = "";
                                if (direccionDestino.getCalle() != null) {
                                    direccionCompleta += direccionDestino.getCalle() + " ";
                                }
                                if (direccionDestino.getNumero() != null) {
                                    direccionCompleta += direccionDestino.getNumero() + " ";
                                }
                                if (direccionDestino.getColonia() != null) {
                                    direccionCompleta += direccionDestino.getColonia() + " ";
                                }
                                if (direccionDestino.getCp() != null) {
                                    direccionCompleta += direccionDestino.getCp() + " ";
                                }
                                if (direccionDestino.getCiudad() != null) {
                                    direccionCompleta += direccionDestino.getCiudad() + " ";
                                }
                                if (direccionDestino.getEstado() != null) {
                                    direccionCompleta += direccionDestino.getEstado();
                                }
                                out.print(direccionCompleta.trim());
                            }
                            %>
                        </td>
                        <td><%= c.getChofer().getNombre()+" "+ c.getChofer().getApPaternos() +" "+ c.getChofer().getApMaternos() +" "+ c.getChofer().getLicencias() %></td>
                        <td><%= c.getRuta().getDiatancio() %></td>
                        <td><%= c.getRuta().getFechaSalida() %></td>
                        <td><%= c.getRuta().getFechaLlegadaEstimada() %></td>
                        <td><%= c.getRuta().getFechaLlegadaReal() %></td>
                        <td><%= c.getRuta().getaTiempo() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
    
    </div>

   

    </div>
  </body>
</html>
