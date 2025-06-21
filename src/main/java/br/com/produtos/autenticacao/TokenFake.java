package br.com.produtos.autenticacao;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class TokenFake implements Filter {
    private final String TOKEN = "th@r120386";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String tokenHeader = request.getHeader("Authorization");
        if (TOKEN.equalsIgnoreCase(tokenHeader)) {
            filterChain.doFilter(request, servletResponse);
        }else{
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Acesso negado sem permisao");
        }

    }
}
