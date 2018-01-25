package vn.edu.tdmu.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author NguyenLinh
 */

public class UsernameAuditorAware implements AuditorAware<String>{

    public static final Logger LOGGER = LoggerFactory.getLogger(UsernameAuditorAware.class);

	@Override
	public String getCurrentAuditor() {

        LOGGER.debug("Getting the user of authenticated user.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()){
            LOGGER.debug("Current user is anonymus. Returning null.");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails){
            String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();

            LOGGER.debug("Returning username: {}", username);
            return username;
        }

		return null;
	}

}