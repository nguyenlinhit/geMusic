package vn.edu.tdmu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nguye on 2/2/2018.
 *
 */
@Component
public class AjaxAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxAuthenticationSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public AjaxAuthenticationSuccessHandler() {
        LOGGER.info("Inside constructor of AjaxAuthenticationSuccessHandler.");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        if (RequestUtil.isAjaxRequest(request)) {
            RequestUtil.sendJsonResponse(response, "true");
        } else {
            String targetUrl = determineTargetUrl(authentication);

            if (response.isCommitted()) {
                LOGGER.info("Can't redirect");
                return;
            }

            redirectStrategy.sendRedirect(request, response, targetUrl);
        }
    }

    private String determineTargetUrl(Authentication authentication) {
        String url;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (isAdmin(roles) || isDba(roles)) {
            url = "/admin";
        } else {
            url = "/home";
        }

        return url;
    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }

    private boolean isDba(List<String> roles) {
        return roles.contains("ROLE_DBA");
    }
}
