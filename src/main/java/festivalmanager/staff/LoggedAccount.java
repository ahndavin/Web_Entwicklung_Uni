package festivalmanager.staff;

import org.apache.juli.logging.Log;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;

@Component
public class LoggedAccount implements HttpSessionBindingListener {
	private String usrName;
	private ActiveAccountsStore activeAccountsStore;

	public LoggedAccount(String usrName, ActiveAccountsStore activeAccountsStore){
		this.usrName = usrName;
		this.activeAccountsStore = activeAccountsStore;
	}

	public LoggedAccount(){}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		List<String> accounts = activeAccountsStore.getAccounts();
		LoggedAccount account = (LoggedAccount) event.getValue();
		if(!accounts.contains(account.getUsrName())) {
			accounts.add(account.getUsrName());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event){
		List<String> accounts = activeAccountsStore.getAccounts();
		LoggedAccount account = (LoggedAccount) event.getValue();
		if(!accounts.contains(account.getUsrName())){
			accounts.remove(account.getUsrName());
		}
	}


	public ActiveAccountsStore getActiveAccountsStore() {
		return activeAccountsStore;
	}

	public void setActiveAccountsStore(ActiveAccountsStore activeAccountsStore) {
		this.activeAccountsStore = activeAccountsStore;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
}

