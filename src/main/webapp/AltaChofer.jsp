<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@page
import="java.util.*" %>

<% Map<String,String> errores = (Map<String, String>) request.getAttribute("errores"); %>

    <!DOCTYPE html>
    <html lang="en">
      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link
          rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
        />
        <script
          src="https://code.jquery.com/jquery-2.2.4.min.js"
          integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
          crossorigin="anonymous"
        ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      </head>
      <body>
        <%@ include file="Header.jsp" %>


        <div class="container">
          <div class="row">
            <div class="col-12">
              <h2>Formulario alta chofer</h2>
            </div>

          </div>

          <br>
          <% if(errores != null && errores.size() > 0){ %>
            <ul class="alert alert-danger mx-5 px-5">

              <% for(String e : errores.values()){ %>
                <li><%=e%></li>
              <% } %>

            </ul>
          <% } %>

          <div class="row">
            <form action="<%=request.getContextPath()%>/choferes/alta" method="post">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="">Nombre</label>
                        <input type="text" id="nombre" name="nombre" class="form-control" value="${param.nombre}">
                        <% if(errores != null && errores.containsKey("nombre")){
                            out.println("<span class='text-danger'>" + errores.get("nombre") + "</span>");
                        }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Apellido Paterno</label>
                        <input type="text" id="apPaternos" name="apPaternos" class="form-control" value="${param.apPaternos}">
                        <% if(errores != null && errores.containsKey("apPaternos")){
                            out.println("<span class='text-danger'>" + errores.get("apPaternos") + "</span>");
                        }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Apellido Materno</label>
                        <input type="text" id="apMaterno" name="apMaternos" class="form-control" value="${param.apMaternos}">
                        <% if(errores != null && errores.containsKey("apMaternos")){
                            out.println("<span class='text-danger'>" + errores.get("apMaternos") + "</span>");
                        }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Licencia</label>
                        <input type="text" id="licencias" name="licencias" class="form-control" value="${param.licencias}">
                        <% if(errores != null && errores.containsKey("licencias")){
                            out.println("<span class='text-danger'>" + errores.get("licencias") + "</span>");
                        }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Telefono</label>
                        <input type="text" id="telefono" name="telefono" class="form-control" value="${param.telefono}">
                        <% if(errores != null && errores.containsKey("telefono")){
                            out.println("<span class='text-danger'>" + errores.get("telefono") + "</span>");
                        }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Fecha Nacimiento</label>
                        <input type="text" id="fechaNacimiento" name="fechaNacimiento" class="form-control" value="${param.fechaNacimiento}">
                        <% if(errores != null && errores.containsKey("fechaNacimiento")){
                            out.println("<span class='text-danger'>" + errores.get("fechaNacimiento") + "</span>");
                        }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Disponibilidad</label>
                        <input type="checkbox" name="disponibilidad" id="disponibilidad" class="form-check-input" value="${param.disponibilidad}">
                    </div>

                    <div class="form-group">

                        <button type="submit" class="btn btn-success">Guardar</button>
                    </div>
                </div>
            </form>
          </div>
        </div>
      </body>
    </html>
  </String,></String,
>
