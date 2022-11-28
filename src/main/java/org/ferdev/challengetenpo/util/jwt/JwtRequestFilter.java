package org.ferdev.challengetenpo.util.jwt;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ferdev.challengetenpo.exception.UnauthorizedHandler;
import org.ferdev.challengetenpo.exception.UserNotFoundHandler;
import org.ferdev.challengetenpo.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserNotFoundHandler userNotFoundHandler;
    private final UnauthorizedHandler unauthorizedHandler;

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        try {
            if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);

                username = jwtUtil.getUserNameFromJwtToken(jwt);
            }

            if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateJwtToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetailsService, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (ArrayIndexOutOfBoundsException | JwtException exception) {
            unauthorizedHandler.apply(response, request);
        } catch (UsernameNotFoundException exception) {
            userNotFoundHandler.apply(response, request);
        }
    }

}
