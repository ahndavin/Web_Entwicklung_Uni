package festivalmanager.staff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("FestivalLogoutSuccessHandler")
public class FestivalLogoutSuccessHandler implements LogoutSuccessHandler {

	private static final Logger LOG = LoggerFactory.getLogger(FestivalLogoutSuccessHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
		throws IOException, ServletException {
		HttpSession session = request.getSession();
		if(session != null){
			session.removeAttribute("account");
		LOG.info("remove one account from logged in accounts");
		}

	}
}
