<nav class="navbar navbar-inverse">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" id="div1">
              <button
                type="button"
                class="navbar-toggle collapsed"
                data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1"
                aria-expanded="false"
              >
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#" id="enlace1">Rutas App</a>
            </div>

            <div
              class="collapse navbar-collapse"
              id="bs-example-navbar-collapse-1"
            >
              <ul class="nav navbar-nav">
                <li class="dropdown">
                  <a
                    href="#"
                    class="dropdown-toggle"
                    data-toggle="dropdown"
                    role="button"
                    aria-haspopup="true"
                    aria-expanded="false"
                    >Choferes<span class="caret"></span
                  ></a>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="<%=request.getContextPath()%>/choferes/listar"
                        >Lista Choferes</a
                      >
                    </li>
                    <li>
                      <a href="<%=request.getContextPath()%>/choferes/alta"
                        >Alta Chofer</a
                      >
                    </li>
                  </ul>
                </li>

                <li class="dropdown">
                  <a
                    href="#"
                    class="dropdown-toggle"
                    data-toggle="dropdown"
                    role="button"
                    aria-haspopup="true"
                    aria-expanded="false"
                    >Camiones<span class="caret"></span
                  ></a>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="<%=request.getContextPath()%>/camiones/listar"
                        >Lista Camiones</a
                      >
                    </li>
                    <li>
                      <a href="<%=request.getContextPath()%>/camiones/alta"
                        >Alta Camion</a
                      >
                    </li>
                  </ul>
                </li>

                <li class="dropdown">
                  <a
                    href="#"
                    class="dropdown-toggle"
                    data-toggle="dropdown"
                    role="button"
                    aria-haspopup="true"
                    aria-expanded="false"
                    >Rutas<span class="caret"></span
                  ></a>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="<%=request.getContextPath()%>/rutas/alta"
                        >Alta Ruta</a
                      >
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
            <!-- /.navbar-collapse -->
          </div>
          <!-- /.container-fluid -->
        </nav>