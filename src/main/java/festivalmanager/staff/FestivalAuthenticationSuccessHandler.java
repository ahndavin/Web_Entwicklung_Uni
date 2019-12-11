package festivalmanager.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("FestivalAuthenticationSuccessHandler")
public class FestivalAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	ActiveAccountsStore activeAccountsStore;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
		throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoggedAccount account = new LoggedAccount(authentication.getName(), activeAccountsStore);
			session.setAttribute("account", account);
		}
	}
}

