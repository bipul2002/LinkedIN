package com.elasticsearch.elasticsearch.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtHelper jwtHelper;


    @Autowired
    private UserDetailsService userDetailsService;

    private  String identity=null;
    Claims claims=null;
    private String username=null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().matches("/users/ | /auth/login")){
            filterChain.doFilter(request,response);
        }
        else {
            String authorizationHeader=request.getHeader("Authorization");
            String token= null;
            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
                token=authorizationHeader.substring(7);
                username= jwtHelper.getUsernameFromToken(token);//Token Username
                claims= jwtHelper.getAllClaimsFromToken(token);
            }
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails= userDetailsService.loadUserByUsername(username);//Database username
                if(jwtHelper.validateToken(token,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken
                            (userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
                identity = username;

            }
            username = null;
            filterChain.doFilter(request,response);

        }

    }
}
