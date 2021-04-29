package br.com.zupacademy.fabio.mercadolivre.shared.config.security;

import br.com.zupacademy.fabio.mercadolivre.user.User;
import br.com.zupacademy.fabio.mercadolivre.user.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final TokenUtil tokenUtil;
    private final UserRepository userRepository;

    public AuthenticationFilter(TokenUtil tokenUtil, UserRepository userRepository) {
        this.tokenUtil = tokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenUtil.retrieveToken(httpServletRequest);
        if (token != null){
            boolean validToken = tokenUtil.isValidToken(token);
            if(validToken){
                authenticateUser(token);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authenticateUser(String token) {
        long userId = tokenUtil.getUserId(token);
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
}
