package br.ifes.pmxnet.seguranca;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String token = JwtUtil.getTokenAtual();

        if (token == null || JwtUtil.getClaims(token) == null) {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Acesso negado: token inválido ou ausente.");
            return;
        }

        // Se o token for válido, continue o fluxo
        chain.doFilter(req, res);
    }
}
