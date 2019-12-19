package festivalmanager.staff;



import javax.validation.Valid;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.salespointframework.useraccount.web.LoggedIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

@Controller
class 	AccountController {

		private final AccountManager accountManager;
		public final UserAccountManager userAccounts;
		private final MessageManagement messageManagement;
		private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
		@Autowired
		ActiveAccountsStore activeAccountsStore;
		MessageEventPublisher publisher;


		@Autowired


		AccountController(AccountManager accountManager, UserAccountManager userAccounts, MessageManagement messageManagement, MessageEventPublisher publisher){
			Assert.notNull(accountManager, "kickstart.account.AccountManager must be not null");
			this.accountManager = accountManager;
			this.userAccounts = userAccounts;
			this.messageManagement = messageManagement;
			this.publisher = publisher;
		}


		@PostMapping("/createAccount")
		String createAccountNew(@Valid @ModelAttribute("form") CreationForm form, Model model, Errors result){
			accountManager.createAccount(form, result);
			if(result.hasErrors()){
				return createAccount(model,form,result);
			}
			return "redirect:/";
		}

	@PreAuthorize("hasAuthority('MANAGER')")
		@GetMapping("/createAccount")
		String createAccount(Model model, CreationForm form, Errors result) {
			model.addAttribute("form", form);
			model.addAttribute("error", result);
			return "createAccount";
		}

	@PreAuthorize("hasAuthority('MANAGER')")
		@GetMapping("/allAccounts")
		String allAccounts(Model model){
			model.addAttribute("accountList", accountManager.findAll());

			return "allAccounts";
		}

		@GetMapping(value = "/loggedaccounts")
		public String getLoggedAccounts(Locale locale, Model model){
			model.addAttribute( "accounts", activeAccountsStore.getAccounts());

			return "accounts";
		}

		@GetMapping("/myProfile")
		String profile(@LoggedIn UserAccount account, Model model){

			model.addAttribute("account", account);
			return "myProfile";
		}

		@GetMapping("/changePassword")
		String changePassword(@LoggedIn UserAccount account, Model model, changePasswordForm form){
			model.addAttribute("form", form);
			model.addAttribute("account", account);

			return "changePassword";
		}

		@PostMapping("/changePassword")
		String changePasswordPost( @LoggedIn UserAccount account, @Valid changePasswordForm form, Model model){
			accountManager.changePassword(account, form);

			return "redirect:/";
		}

		@PreAuthorize("hasAuthority('MANAGER')")
		@GetMapping("/changePassword/{name}")
		String changePasswordByUsername(Model model, changePasswordForm form, @PathVariable String name){
			model.addAttribute("form", form);
			model.addAttribute("name", name);
			return "changePassword";
		}


		@PostMapping("changePassword/{name}")
		String changePasswordByUsernamePost(Model model, changePasswordForm form, @PathVariable String name){

			accountManager.changePassword(userAccounts.findByUsername(name).get(), form);
			LOG.info("changing password for " + name);
			return "redirect:/allAccounts";
		}

		@GetMapping("/sendMessage")
		String sendMessage(Model model, MessageForm form){
			model.addAttribute("form", form);
			return "sendMessage";
		}

		@PostMapping("/sendMessage")
		String sendMessagePost(Model model, MessageForm form){
			/*MessageEvent messageEvent = new MessageEvent(form.getSender(), accountManager.findByUserAccount(userAccounts.findByUsername(form.getSender()).get()),
					accountManager.findByUserAccount(userAccounts.findByUsername(form.getReceiver()).get()), form.getMessage());
			publisher.publishEvent(messageEvent);*/
			return("sendMessage");
		}

}

