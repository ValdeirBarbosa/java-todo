package com.valdeir.java_todo.filter;


import java.util.Base64;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.valdeir.java_todo.user.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class filterTaskAuth extends OncePerRequestFilter{
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servletPath = request.getServletPath();

                if(servletPath.equals("/tasks/")){
                
                    var authorization = request.getHeader("Authorization");
                    byte[]  authDecoded = Base64.getDecoder().decode(authorization.substring("Basic".length()).trim());
                    String[] credentials =  new String(authDecoded).split(":");
                    //credentials = [userName, password]
                    String userName = credentials[0];
                    String userPwd = credentials[1];
                    var userExists = this.iUserRepository.findUserByUserName(userName);
                    System.out.println(userName);
                    if(userExists == null){
                        response.sendError(401); // unauthorized
                    }else{
                        var pwdVerifyed = BCrypt.verifyer().verify(userPwd.toCharArray(),userExists.getPassword());
                        if(pwdVerifyed.verified){
                            request.setAttribute("userId",userExists.getId());
                            filterChain.doFilter(request, response);
                        }else{
                            response.sendError(401); // unauthorized
                        }

                    }
 
                }else{
                    filterChain.doFilter(request, response);
                }
                
                
                
                
                
                
                
        
    }


    
}
