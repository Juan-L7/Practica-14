package com.example.Pokemon.API.REST.security;

import com.example.Pokemon.API.REST.dto.UserPermission;
import com.example.Pokemon.API.REST.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE, OPTIONS , HEAD, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, Origin, X-Requested-with");

        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        if(isPublicRoute(request)){
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            sendUnauthorized(response, "Se requiere un token de autorizacion");
            return;
        }

        String token = authHeader.substring(7);
        if(!jwtService.isTokenValid(token)){
            sendUnauthorized(response, "Token invalido o expirado");
            return;
        }

        UserPermission user = jwtService.extractUser(token);
        request.setAttribute("user", user.getMail());
        request.setAttribute("role", user.getRole());

        filterChain.doFilter(request, response);

    }

    private void sendUnauthorized(HttpServletResponse response, String mensaje) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String error = String.format("{\"error\": \"%s\"", mensaje);
        response.getWriter().write(error);
    }

    private boolean isPublicRoute(HttpServletRequest request){
        String method = request.getMethod();
        String path = request.getRequestURI();

        if(path.equals("/api/auth/login") && method.equals("POST")) return true;

        if(path.equals("/api/users") && method.equals("POST")) return true;

        if(path.equals("/api/pokemones") && method.equals("GET")) return true;

        if(path.equals("/api/pokemones/*") && method.equals("GET")) return true;


        return false;
    }
}
