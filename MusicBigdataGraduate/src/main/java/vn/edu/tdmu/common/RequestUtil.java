package vn.edu.tdmu.common;

import org.apache.log4j.Logger;
import org.springframework.security.web.util.matcher.ELRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NguyenLinh on 2/2/2018.
 *
 */
class RequestUtil {
    private static final Logger LOGGER = Logger.getLogger(RequestUtil.class.getName());

    private static final RequestMatcher REQUEST_MATCHER = new ELRequestMatcher("hasHeader('X-Requested-With','XMLHttpRequest')");

    private static final String JSON_VALUE = "{\"%s\": %s}";

    static Boolean isAjaxRequest(HttpServletRequest request) {
        return REQUEST_MATCHER.matches(request);
    }

    static void sendJsonResponse(HttpServletResponse response, String message) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(String.format(JSON_VALUE, "success", message));
        } catch (IOException e) {
            LOGGER.error("error writing json to response", e);
        }
    }
}
