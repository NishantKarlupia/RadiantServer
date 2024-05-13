package com.stone.stone.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// import com.exam.examserver.services.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        

                String requestTokenHeader= request.getHeader("Authorization");
                System.out.println(requestTokenHeader);

                String username=null;
                String jwtToken=null;

                if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
                {
                    jwtToken=requestTokenHeader.substring(7);
                    try
                    {
                        username=this.jwtUtil.extractUsername(jwtToken);
                    }
                    catch(ExpiredJwtException e)
                    {
                        e.printStackTrace();
                        System.out.println("Jwt token expired");
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }


                }
                else{
                    System.out.println("Invalid token");
                }

                // validate the token

                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
                {
                   final UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(username);
                   if(this.jwtUtil.validateToken(jwtToken, userDetails))
                   {
                    // token is valid
                    Authentication authentication=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                   }

                }

                filterChain.doFilter(request, response);




        
        
    }
    
}

