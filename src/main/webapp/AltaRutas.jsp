<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.lopez.app.rutas.models.*" %>

<%
//recuperamos la lista de choferes que seteamos en el request desde el servlet
List<Chofer> choferes = (List<Chofer>) request.getAttribute("choferes");
List<Camion> camiones = (List<Camion>) request.getAttribute("camiones");
%>

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

        <div class="container body-content">

            <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyCWeeateTaYGqsHhNcmoDfT7Us-vLDZVPs&amp;sensor=false&amp;language=en"></script>


            <div class="row">
                <div class="col-md-12">
                    <h2>Iniciar Ruta</h2>
                </div>
                <div style="display: block;"><input type="text" name="" id="txtEsOD"> </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="">Chofer</label>
                        <select name="chofer" id="chofer" class="form-control">
                            <option value="">seleccionar</option>
                            <% for(Chofer c : choferes){ %>
                                <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-9">
                                <label>origen</label>
                                <input type="text" name="origen" id="origen" class="form-control">
                                <input type="hidden" name="idOrigen" id="idOrigen" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <button class="btn btn-primary btn-xs" style="margin-top: 30px;" onclick="getDirecciones(1)" >
                                    Optener Datos
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="">Fecha salida</label>
                        <input type="text" id="FSalida" name="FSalida" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="">Distancia</label>
                        <input type="text" id="distancia" name="distancia" class="form-control">
                    </div>
                </div>



                <div class="col-md-6">
                    <div class="form-group">
                        <label for="">Camion</label>
                        <select name="camion" id="camion" class="form-control">
                            <option value="">seleccionar</option>
                            <% for(Camion c : camiones){ %>
                                <option value="<%=c.getId()%>"><%=c.getMatricula()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-9">
                                <label>Destino</label>
                                <input type="text" name="destino" id="destino" class="form-control">
                                <input type="hidden" name="idDestino" id="idDestino" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <button class="btn btn-primary btn-xs" style="margin-top: 30px;" onclick="getDirecciones(2)" >
                                    Optener Datos
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="">Fecha Estimada de llegada</label>
                        <input type="text" id="FELlegada" name="FELlegada" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="">Capacidad Camion</label>
                        <input type="text" id="capCamion" name="capCamion" class="form-control">
                    </div>

                    <div class="form-group">
                        <button class=" btn btn-success" >Iniciar Rutas</button>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="row">
                            <div class="col-md-12">
                                <h4>Guardar direccion</h4>
                            </div>
                        </div>
                    </div>

                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="">calle</label>
                                    <input type="text" id="calle" name="calle" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">Numero</label>
                                    <input type="text" id="Numero" name="Numero" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">Colonia</label>
                                    <input type="text" id="Colonia" name="Colonia" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">Ciudad</label>
                                    <input type="text" id="Ciudad" name="Ciudad" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">Estado</label>
                                    <input type="text" id="Estado" name="Estado" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">CP</label>
                                    <input type="text" id="CP" name="CP" class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-md-10 com-md-offset-1">
                                <div class="col-md-4">
                                    <button class="btn btn-success" onclick="btnDuardarDir()">
                                        Guardar
                                    </button>
                                </div>
                                <div class="col-md-4 col-md-offset-2">
                                    <button class="btn btn-default" data-dismiss="modal">
                                        Cancelar
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <script>
  function limpiarDatos() {
    $("#calle").val("");
    $("#Numero").val("");
    $("#Colonia").val("");
    $("#Ciudad").val("");
    $("#Estado").val("");
    $("#CP").val("");
  }

  function getDirecciones(fuente) {
    limpiarDatos();
    $("#myModal").modal("show");
    var direccion = "";

    if (fuente == 1) {
      direccion = $("#origen").val();
    } else {
      direccion = $("#destino").val();
    }

    $("#txtEsOD").val(fuente);
    if (direccion != "") {
      var geocoder = new google.maps.Geocoder();
      geocoder.geocode(
        { address: direccion },
        function (results, status) {
          console.log(results);
          console.log(status);

          if (status == google.maps.GeocoderStatus.OK) {
            var numresult = results[0].address_components.length;
            for (let index = 0; index < numresult; index++) {
              var numresultstype = results[0].address_components[index].types.length;

              for (var propiedad = 0; propiedad < numresultstype; propiedad++) {
                var Tipo = results[0].address_components[index].types[propiedad];
                var Descripcion = results[0].address_components[index].long_name;
                switch (Tipo) {
                  case "route":
                    $("#calle").val(Descripcion);
                    break;
                  case "street_number":
                    $("#Numero").val(Descripcion);
                    break;
                  case "sublocality_level_1": // Colonia
                    $("#Colonia").val(Descripcion);
                    break;
                  case "locality": // Ciudad
                    $("#Ciudad").val(Descripcion);
                    break;
                  case "administrative_area_level_1": // Estado
                    $("#Estado").val(Descripcion);
                    break;
                  case "postal_code": // C.P.
                    $("#CP").val(Descripcion);
                    break;
                }
              }
            }
            if (results[0].address_components.length == 0) {
              if (fuente == 1) {
                $("#origen").val(results[0].formatted_address);
              } else {
                $("#destino").val(results[0].formatted_address);
              }
            }
          } else {
            alert("Google no obtuvo datos");
          }
        }
      );
    } else {
      alert("No puedes obtener datos si no proporcionas una dirección");
      $("#myModal").modal("hide");
    }
  }
</script>

      </body>
    </html>

