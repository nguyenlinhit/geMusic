package vn.edu.tdmu.utils;


import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Component;

/**
 * Created by NguyenLinh on 2/2/2018.
 *
 */
@Component
public class WebInvocationPrivilegeEvaluatorUtil {
    private static WebInvocationPrivilegeEvaluator wipe = null;

    private WebInvocationPrivilegeEvaluatorUtil() {
    }

    public static WebInvocationPrivilegeEvaluator getWebInvocationPrivilegeEvaluator() {
        return wipe;
    }

    public void setWebInvocationPrivilegeEvaluator(WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator) {
        wipe = webInvocationPrivilegeEvaluator;
    }
}
