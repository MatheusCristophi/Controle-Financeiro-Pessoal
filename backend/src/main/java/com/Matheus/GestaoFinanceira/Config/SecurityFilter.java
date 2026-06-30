package com.Matheus.GestaoFinanceira.Config;

import com.Matheus.GestaoFinanceira.Exceptions.security.UserNotFoundException;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.verifyToken(token);

        if (login != null) {
            User user = userRepository.findByEmail(login)
            .orElseThrow(() -> new UserNotFoundException());
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var auth = request.getHeader("Authorization");
        if (auth == null){ 
            return null;
        }
        return auth.replace("Bearer ", "");
    }
}
