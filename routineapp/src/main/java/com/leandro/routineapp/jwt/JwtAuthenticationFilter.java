package com.leandro.routineapp.jwt;

import com.leandro.routineapp.exceptions.RoutineAppException;
import com.leandro.routineapp.security.CustomUserDetailsService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //obtenemos el token de la solicitud HTTP
        String token = obtenerJWTdeLaSolicitud(request);

        //validamos el token
        if(StringUtils.hasText(token)) {
            try {
                if (jwtTokenProvider.validarToken(token)) {
                    //obtenemos el username del token
                    String username = jwtTokenProvider.obtenerUsernameDelJWT(token);

                    //cargamos el usuario asociado al token
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    //establecemos la seguridad
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    //devolvemos un mensaje de error
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.getWriter().write("{\"error\":\"Token JWT inválido\"}");
                    return;
                }
            } catch (RoutineAppException ex) {
                //devolvemos un mensaje de error
                response.setStatus(ex.getEstado().value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write("{\"error\":\"" + ex.getMensaje() + "\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    //Bearer token de acceso
    private String obtenerJWTdeLaSolicitud(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
