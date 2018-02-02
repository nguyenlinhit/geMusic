package vn.edu.tdmu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nguye on 2/2/2018.
 *
 */
@Component
public class AjaxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxAuthenticationFailureHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public AjaxAuthenticationFailureHandler() {
        LOGGER.info("Inside constructor of AjaxAuthenticationFailureHandler.");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        if (RequestUtil.isAjaxRequest(request)) {
            RequestUtil.sendJsonResponse(response, "false");
        } else {
            redirectStrategy.sendRedirect(request, response, "/login?error");
        }
    }
}
