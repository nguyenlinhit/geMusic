package vn.edu.tdmu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import vn.edu.tdmu.utils.WebInvocationPrivilegeEvaluatorUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NguyenLinh on 2/2/2018.
 *
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String referrer = request.getHeader("referer");

        if (requiresAuthentication("/admin", authentication)) {
            redirectStrategy.sendRedirect(request, response, "/login?logout");
        } else {
            redirectStrategy.sendRedirect(request, response, referrer);
        }
    }
    
    private boolean requiresAuthentication(String uri, Authentication authentication) {
        WebInvocationPrivilegeEvaluator wipe = WebInvocationPrivilegeEvaluatorUtil.getWebInvocationPrivilegeEvaluator();
        return wipe.isAllowed(uri, authentication);
    }

}
