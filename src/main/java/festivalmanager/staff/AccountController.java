package festivalmanager.staff;



import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@Controller
class 	AccountController {

		private final AccountManager accountManager;

		AccountController(AccountManager accountManager){
			Assert.notNull(accountManager, "kickstart.account.AccountManager must be not null");
			this.accountManager = accountManager;
		}


		@PostMapping("/createAccount")
		String createAccountNew(@Valid @ModelAttribute("form") CreationForm form, Model model, Errors result){
			accountManager.createAccount(form, result);
			if(result.hasErrors()){
				return createAccount(model,form,result);
			}
			return "redirect:/";
		}


		@GetMapping("/createAccount")
		String createAccount(Model model, CreationForm form, Errors result) {
			model.addAttribute("form", form);
			model.addAttribute("error", result);
			return "createAccount";
		}

}


