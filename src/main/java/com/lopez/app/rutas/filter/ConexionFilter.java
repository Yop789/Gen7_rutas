package com.lopez.app.rutas.filter;

import com.lopez.app.rutas.utils.ConexionBD;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.sql.Connection;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    private Connection getConnection() {
        return ConexionBD.getInstance();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Connection conn = this.getConnection();
        request.setAttribute("conn", conn);

        try {
            chain.doFilter(request, response);

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

}
