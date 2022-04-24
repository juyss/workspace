package com.github.tangyi.common.security.ty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.authentication.*;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TyAuthFilter extends OAuth2AuthenticationProcessingFilter {
    private final static Log logger = LogFactory.getLog(TyAuthFilter.class);

    public TyAuthFilter(AuthenticationManager authenticationManager,TokenExtractor tokenExtractor) {
        this.authenticationManager = authenticationManager;
        this.tokenExtractor = tokenExtractor;
    }

    private AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();

    private AuthenticationManager authenticationManager;

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new OAuth2AuthenticationDetailsSource();

    private TokenExtractor tokenExtractor ;

//    private AuthenticationEventPublisher eventPublisher = new NullEventPublisher();

    private boolean stateless = true;
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final boolean debug = logger.isDebugEnabled();
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        try {

            Authentication authentication = tokenExtractor.extract(request);

            if (authentication == null) {
                if (stateless && isAuthenticated()) {
                    if (debug) {
                        logger.debug("Clearing security context.");
                    }
                    SecurityContextHolder.clearContext();
                }
                if (debug) {
                    logger.debug("No token in request, will continue chain.");
                }
            }else if(authentication.getPrincipal()!=null && authentication.getPrincipal().toString().length() >36){//统一认证的token 是36位的

            }
            else {
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE, authentication.getPrincipal());
                if (authentication instanceof AbstractAuthenticationToken) {
                    AbstractAuthenticationToken needsDetails = (AbstractAuthenticationToken) authentication;
                    needsDetails.setDetails(authenticationDetailsSource.buildDetails(request));
                }
                Authentication authResult = authenticationManager.authenticate(authentication);

                if (debug) {
                    logger.debug("Authentication success: " + authResult);
                }

//                eventPublisher.publishAuthenticationSuccess(authResult);
                SecurityContextHolder.getContext().setAuthentication(authResult);

            }
        }
        catch (OAuth2Exception failed) {
            SecurityContextHolder.clearContext();

            if (debug) {
                logger.debug("Authentication request failed: " + failed);
            }
//            eventPublisher.publishAuthenticationFailure(new BadCredentialsException(failed.getMessage(), failed),
//                    new PreAuthenticatedAuthenticationToken("access-token", "N/A"));

            authenticationEntryPoint.commence(request, response,
                    new InsufficientAuthenticationException(failed.getMessage(), failed));

            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return true;
    }
}
