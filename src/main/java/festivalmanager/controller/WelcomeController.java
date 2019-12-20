/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package festivalmanager.controller;

import festivalmanager.staff.Account;

import festivalmanager.staff.AccountManager;
import festivalmanager.staff.MessageManagement;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class WelcomeController {



	public final MessageManagement messageManagement;
	public final AccountManager accountManager;

	WelcomeController(MessageManagement messageManagement, AccountManager accountManager){
		this.messageManagement = messageManagement;
		this.accountManager = accountManager;
	}

	@RequestMapping("/")
	public String index(Model model, @LoggedIn Optional<UserAccount> userAccount, MessageManagement messageManagement) {
		Assert.notNull(messageManagement, "MessageManagement must not be null");
		if(userAccount.isPresent()) {
			Account account = accountManager.findByUserAccount(userAccount.get()).get();
			model.addAttribute("Account", account);
		}
		model.addAttribute("messageManagement", messageManagement);
		return "welcome";
	}
}
