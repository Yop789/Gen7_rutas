<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.lopez.app.rutas.models.*" %>
<%
// Recuperamos la lista de choferes y camiones que se seteó en el request desde el servlet
List<Chofer> choferes = (List<Chofer>) request.getAttribute("choferes");
List<Camion> camiones = (List<Camion>) request.getAttribute("camiones");
List<Direccion> direcciones = (List<Direccion>) request.getAttribute("direcciones");
Map<String,String> errores = (Map<String, String>) request.getAttribute("errores");

%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Iniciar Ruta</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
    <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
          .spinner {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                border: 4px solid rgba(0, 0, 0, 0.1);
                border-top-color: #3498db;
                animation: spin 1s ease-in-out infinite;
                position: relative;
                margin: auto;
              }

              @keyframes spin {
                0% {
                  transform: rotate(0deg);
                }
                100% {
                  transform: rotate(360deg);
                }
              }
        </style>
      </head>
  </head>
  <body>
    <%@ include file="Header.jsp" %>

    <div class="container body-content">
      <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyCWeeateTaYGqsHhNcmoDfT7Us-vLDZVPs&sensor=false&language=en"></script>

      <div class="row">
        <div class="col-md-12">
          <h2>Iniciar Ruta</h2>
        </div>

      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="form-group">
            <label for="chofer">Chofer</label>
            <select name="chofer" id="chofer" class="form-control">
              <option value="">Seleccionar</option>
              <% for (Chofer c : choferes) { %>
              <option value="<%= c.getId() %>"><%= c.getNombre() %></option>
              <% } %>
            </select>
          </div>

          <div class="form-group">
              <div class="row">
                  <div class="col-md-9">
                      <label for="origen">Origen</label>
                      <input type="text" name="origen" id="origen" class="form-control" />
                      <input type="hidden" name="idOrigen" id="idOrigen" class="form-control" />
                  </div>
                  <div class="col-md-3">
                      <button class="btn btn-primary btn-xs" style="margin-top: 30px" onclick="getDirecciones(1)">Obtener Datos</button>
                  </div>
              </div>
          </div>
          <div class="form-group">
              <label for="selectDireccion">O una dirección</label>
              <select name="selectDireccion" id="selectDireccion" class="form-control" aria-label="Default select example">
                  <option selected>---Selecciona una Dirección---</option>
                  <% for (Direccion m : direcciones) { %>
                      <option value="<%= m.getId() %>" data-address="<%= m.getCalle() + ' ' + m.getNumero() + ' ' + m.getColonia() + ' ' + m.getCiudad() + ' ' + m.getEstado() %>">
                          <%= m.getCalle() + " " + m.getNumero() + " " + m.getColonia() + " " + m.getCiudad() + " " + m.getEstado() %>
                      </option>
                  <% } %>
              </select>
              <% if (errores != null && errores.containsKey("idOrigen")) { %>
                  <span class="text-danger"><%= errores.get("idOrigen") %></span>
              <% } %>
          </div>

          <div class="form-group">
            <label for="FSalida">Fecha Salida</label>
            <input type="datetime-local" id="FSalida" name="FSalida" class="form-control" />
             <button class="btn btn-primary" onclick=" initMap()">Llegada</button>
          </div>

          <div class="form-group">
            <label for="distancia">Distancia</label>
            <input type="text" id="distancia" name="distancia" class="form-control" />
          </div>
        </div>

        <div class="col-md-6">
          <div class="form-group">
            <label for="camion">Camión</label>
            <select name="camion" id="camion" class="form-control">
              <option value="">Seleccionar</option>
              <% for (Camion c : camiones) { %>
              <option  value="<%= c.getId() %>" data-capacidad="<%= c.getCapacidad() %>" ><%= c.getMatricula() %></option>
              <% } %>
            </select>
          </div>

          <div class="form-group">
            <div class="row">
                <div class="col-md-9">
                    <label for="destino">Destino</label>
                    <input type="text" name="destino" id="destino" class="form-control" />
                    <input type="hidden" name="idDestino" id="idDestino" class="form-control" />
                </div>
                <div class="col-md-3">
                    <button class="btn btn-primary btn-xs" style="margin-top: 30px" onclick="getDirecciones(2)">Obtener Datos</button>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="selectDestino">O una dirección</label>
            <select name="selectDestino" id="selectDestino" class="form-control" aria-label="Default select example">
                <option selected>---Selecciona una Dirección---</option>
                <% for (Direccion m : direcciones) { %>
                    <option value="<%= m.getId() %>" data-address="<%= m.getCalle() + ' ' + m.getNumero() + ' ' + m.getColonia() + ' ' + m.getCiudad() + ' ' + m.getEstado() %>">
                        <%= m.getCalle() + " " + m.getNumero() + " " + m.getColonia() + " " + m.getCiudad() + " " + m.getEstado() %>
                    </option>
                <% } %>
            </select>
            <% if (errores != null && errores.containsKey("idDestino")) { %>
                <span class="text-danger"><%= errores.get("idDestino") %></span>
            <% } %>
        </div>

          <div class="form-group mt-1">
            <label for="FLlegada">Fecha Estimada de Llegada</label>
            <input type="datetime-local" id="FLlegada" name="FLlegada" class="form-control" />
          </div>

          <div class="form-group">
            <label for="capCamion">Capacidad Camión</label>
            <input type="text" id="capCamion" name="capCamion" class="form-control" />
          </div>
                    

          <div class="form-group">
            <button class="btn btn-success" onclick ="iniciarRuta()">Iniciar Rutas</button>
          </div>
        </div>
      </div>

       <div class="row">
        <div class="col-md-12">
            <h2>Cargamento</h2>
        </div>

            <div class="form-group">
                <div class="row">
                  <div class="col-md-6">
                    <label for="descripcionC">Descripcion</label>
                    <input type="text" name="descripcionC" id="descripcionC" class="form-control" />
                  </div>
                  <div class="col-md-3">
                    <label for="pesoC">Peso</label>
                    <input type="number" name="pesoC" id="pesoC" class="form-control" />
                  </div>
                  <div class="col-md-3">
                    <button class="btn btn-primary btn-xs" style="margin-top: 30px" onclick="btnCargar()">Agregar</button>
                  </div>
                </div>
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
                      <th>Descripcion</th>
                      <th>Peso</th>
                      <th></th>
                    </tr>
                  </thead>

                  <tbody id="tbody">
                    
                  </tbody>
                </table>
              </div>
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
                <h4>Guardar Dirección</h4>
              </div>
            </div>
          </div>

          <div class="modal-body">
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label for="calle">Calle</label>
                  <input type="text" id="calle" name="calle" class="form-control" />
                </div>

                <div class="form-group">
                  <label for="Numero">Número</label>
                  <input type="text" id="Numero" name="Numero" class="form-control" />
                </div>

                <div class="form-group">
                  <label for="Colonia">Colonia</label>
                  <input type="text" id="Colonia" name="Colonia" class="form-control" />
                </div>

                <div class="form-group">
                  <label for="Ciudad">Ciudad</label>
                  <input type="text" id="Ciudad" name="Ciudad" class="form-control" />
                </div>

                <div class="form-group">
                  <label for="Estado">Estado</label>
                  <input type="text" id="Estado" name="Estado" class="form-control" />
                </div>

                <div class="form-group">
                  <label for="CP">CP</label>
                  <input type="text" id="CP" name="CP" class="form-control" />
                </div>
              </div>
            </div>
          </div>

          <div class="modal-footer">
            <div class="row">
              <div class="col-md-10 col-md-offset-1">
                <div class="col-md-4">
                  <button class="btn btn-success" onclick="btnGuardarDir()">Guardar</button>
                </div>
                <div class="col-md-4 col-md-offset-2">
                  <button class="btn btn-default" data-dismiss="modal">Cancelar</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="modal modal-dialog modal-dialog-centered" id="loading" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
           <div class="modal-body">
              <div class="text-center">
                <div class="spinner-border" role="status">
                  <div class="spinner"></div>
                  Cargando..
                </div>
              </div>
            </div>
        </div>
      </div>
    </div>
 

    <script>
        var cargamentos = [];
      $(document).ready(function() {
            $('#camion').change(function() {
                var selectedOption = $(this).find('option:selected');
                var capacidad = selectedOption.data('capacidad');
                $('#capCamion').val(capacidad);
            });
            $('#selectDireccion').on('change', function() {
              var selectedOption = $(this).find('option:selected');
              var address = selectedOption.data('address');
              var id = selectedOption.val();

              $('#origen').val(address);
              $('#idOrigen').val(id);
          });
           $('#selectDestino').on('change', function() {
            var selectedOption = $(this).find('option:selected');
            var address = selectedOption.data('address');
            var id = selectedOption.val();

            $('#destino').val(address);
            $('#idDestino').val(id);
          });
        });
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
                if (results[0].address_components.length > 0) {
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

      function btnGuardarDir() {

        var calle = $("#calle").val();
        var numero = $("#Numero").val();
        var colonia = $("#Colonia").val();
        var ciudad = $("#Ciudad").val();
        var estado = $("#Estado").val();
        var cp = $("#CP").val();
        
        $('#loading').modal('show');
        $("#myModal").modal("hide");

       $.ajax({
               type: 'POST',
               url: "http://localhost:8080/Gen7_ApiRutas/api/direcciones",
               data: {
                   calle: calle,
                   numero: numero,
                   colonia: colonia,
                   ciudad: ciudad,
                   estado: estado,
                   cp: cp,
               },
               success: function(data) {
                  $('#loading').modal('hide');
                   console.log(data);  // Verificar los datos de respuesta
                    // Asegurarse de que el modal se cierre
                   if ($("#txtEsOD").val() == 1) {
                       $("#idOrigen").val(data.mensaje);
                   } else {
                       $("#idDestino").val(data.mensaje);
                   }

               },
               error: function(xhr, status, error) {
                  alert("Hubo un problema al guardar la dirección. Por favor, intenta nuevamente.");
                   $("#myModal").modal("show");
              }
           });
      }
      

      function initMap() {
            var origin = $("#origen").val();
            var destination = $("#destino").val();
            var salida =$("#FSalida").val()

            if (!origin || !destination || !salida) {
                alert("Agrega origen y destino de la ruta ademas de la fecha de salida");
                return;
            }

            var service = new google.maps.DistanceMatrixService();
            service.getDistanceMatrix(
                {
                    origins: [origin],
                    destinations: [destination],
                    travelMode: 'DRIVING',
                }, callback);
        }

        function callback(response, status) {
            if (status == 'OK') {
                var origins = response.originAddresses;
                var destinations = response.destinationAddresses;
                var distance = response.rows[0].elements[0].distance.value
             
                 $("#distancia").val( distance);

                var durationSeconds = response.rows[0].elements[0].duration.value;
                 var seconds = response.rows[0].elements[0].duration.value; // Obtienes los segundos de la duración desde la respuesta

                  var date = new Date($('#FSalida').val()); // Fecha de salida
                  var offset = new Date().getTimezoneOffset(); // Offset de la zona horaria local

                  // Convertir segundos a días, horas y minutos
                  var days = Math.floor(seconds / (3600 * 24)); // Días completos
                  var hours = Math.floor((seconds % (3600 * 24)) / 3600); // Horas restantes
                  var minutes = Math.floor((seconds % 3600) / 60); // Minutos restantes

                  // Agregar días, horas y minutos a la fecha de salida
                  date.setDate(date.getDate() + days);
                  date.setHours(date.getHours() + hours);
                  date.setMinutes(date.getMinutes() + minutes);

                  // Calcular el offset y establecer la fecha estimada de llegada
                  $('#FLlegada').val(add_seconds(date, -offset).toISOString().replace("Z", ""));

                  function add_seconds(dt, seconds) {
                      return new Date(dt.getTime() + seconds * 1000);
                  }
            }
        }

        function iniciarRuta(){
          var cliente = $("#chofer").val();
          var origen = $("#idOrigen").val();
          var Fsalida =$("#FSalida").val();
          var distancia =$("#distancia").val();
          var camion = $("#camion").val();
          var destino = $("#idDestino").val();
          var Fllegada =$("#FLlegada").val();
          $.ajax({
               type: 'POST',
               url: "http://localhost:8080/Gen7_ruta/rutas/alta",
               data: {
                   cliente:cliente,
                   origen:origen,
                   fsalida:Fsalida,
                   distancia:distancia,
                   camion:camion,
                   destino:destino,
                   Fllegada:Fllegada,
                   cargamento:cargamentos
               },success: function(response) {
                     // Redirigir inmediatamente a la página listar
                      window.location.href = 'http://localhost:8080/Gen7_ruta/rutas/listar';
                  },
                  error: function(xhr, status, error) {
                     alert("No se pudo realisar la peticion intentalo nuevamente")
                  }
              
           });

        }
      function btnCargar() {
            var descripcionC = $("#descripcionC").val();
            var pesoC = $("#pesoC").val();
            var cargamento = {
                id: Date.now(), // Usar la marca de tiempo como ID único
                desc: descripcionC,
                peso: pesoC
            }
            cargamentos.push(cargamento);
            actualizarTabla();
            console.log(cargamentos);
        }

        function actualizarTabla() {
            $("#tbody").empty(); // Limpiar el contenido del tbody

            cargamentos.forEach(element => {
              console.log(element);
               $("#tbody").append(
                  '<tr id="row-' + element.id + '">' +
                    '<td>' + element.desc + '</td>' +
                    '<td>' + element.peso + '</td>' +
                    '<td><button class="btn btn-danger" onclick="eliminarCargamento('+element.id+')">Eliminar</button></td>' +
                '</tr>'
                );
            });
        }

        function eliminarCargamento(id) {
            console.log(id)
           cargamentos = cargamentos.filter(element => element.id !== id);
            $('#row-' + id).remove();
        }

      
    </script>
  </body>
</html>
