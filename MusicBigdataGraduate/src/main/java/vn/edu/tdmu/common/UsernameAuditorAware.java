package vn.edu.tdmu.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author NguyenLinh
 */

public class UsernameAuditorAware implements AuditorAware<String>{

    public static final Logger LOGGER = LoggerFactory.getLogger(UsernameAuditorAware.class);

	@Override
	public String getCurrentAuditor() {

        LOGGER.info("Getting the username of authenticated user.");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            LOGGER.info("Current user is anonymous. Returning null.");
            return null;
        }

        String username = authentication.getName();
        LOGGER.info("Returning username: {}", username);

        return username;
	}

}