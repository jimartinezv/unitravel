package co.edu.uniquindio.unitravel.filtro;

import co.edu.uniquindio.unitravel.bean.SeguridadBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SeguridadFiltro implements Filter {

    public static final String PAGINA_INICIO = "/index.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            final String requestURI = request.getRequestURI();
            SeguridadBean userManager = (SeguridadBean)
                    request.getSession().getAttribute("seguridadBean");
            //Aplicar el filtro a esta carpeta
            if (requestURI.startsWith("/usuario/") ) {
            //Obtenemos el objeto seguridadBean de la sesión actual


                if (userManager != null) {
                    if (userManager.getAutenticado() && userManager.getRol()==1) {
                //El usuario está logueado entonces si puede ver la página  solicitada

                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al   inicio

                        response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                }
            }else  if (requestURI.startsWith("/admin/") ) {
                //Obtenemos el objeto seguridadBean de la sesión actual


                if (userManager != null) {
                    if (userManager.getAutenticado() && userManager.getRol()==2) {
                        //El usuario está logueado entonces si puede ver la página  solicitada

                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al   inicio

                        response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                }
            }else  if (requestURI.startsWith("/admin_hotel/") ) {
                //Obtenemos el objeto seguridadBean de la sesión actual


                if (userManager != null) {
                    if (userManager.getAutenticado() && userManager.getRol()==3) {
                        //El usuario está logueado entonces si puede ver la página  solicitada

                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al   inicio

                        response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                }
            }else{
                //La página solicitada no está en la carpeta /usuario entonces el filtro no   aplica

                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
