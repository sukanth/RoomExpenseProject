package com.room.EMS.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.room.EMS.Beans.IndividualExpense;
import com.room.EMS.Beans.User_bean;
import com.room.EMS.Beans.login_bean;
import com.room.EMS.Service.login_service;
import com.room.EMS.Service.user_service;
import com.room.EMS.constants.ApplicationConstants;
import com.room.EMS.util.EmsUtil;

@Controller
public class User_controller {
	@Autowired
	user_service user_service;
	@Autowired
	login_service login_service;
	

	@RequestMapping(value = "/addexpense")
	public String details(Model model, HttpSession session) {
		List<String> ulist = new ArrayList<String>();
		ulist = user_service.fetchUsers();
		User_bean user_bean = new User_bean();
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			user_bean.setUsername((String) session.getAttribute(ApplicationConstants.USERNAME));
			model.addAttribute(ApplicationConstants.USERBEAN, user_bean);
			session.setAttribute(ApplicationConstants.APPLICABLETO, ulist);
			return ApplicationConstants.EXPENSEPAGE;
		} else {
			return ApplicationConstants.FAILUREPAGE;
		}

	}

	// calculations
	@RequestMapping(value = "/calculations")
	public String calculations(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			List<IndividualExpense> cal = new ArrayList<IndividualExpense>();
			List<IndividualExpense> result = new ArrayList<IndividualExpense>();
			cal = user_service.getCalculations((String) session
					.getAttribute(ApplicationConstants.USERNAME));
			result = user_service.getIndividualExpense((String) session
					.getAttribute(ApplicationConstants.USERNAME));

			IndividualExpense ie = new IndividualExpense();
			ie = doCalculations((String) session.getAttribute(ApplicationConstants.USERNAME), cal,
					result);
			ie.setChowdary(Math.round(ie.getChowdary()));
			ie.setDatta(Math.round(ie.getDatta()));
			ie.setGunda(Math.round(ie.getGunda()));
			ie.setUday(Math.round(ie.getUday()));
			ie.setVamsi(Math.round(ie.getVamsi()));
			ie.setKiran(Math.round(ie.getKiran()));
			ie.setVihar(Math.round(ie.getVihar()));
			ie.setZubair(Math.round(ie.getZubair()));
			ie.setMurali(Math.round(ie.getMurali()));
			ie.setSudheer(Math.round(ie.getSudheer()));

			session.setAttribute(ApplicationConstants.FINALRESULT, ie);
		}
		return ApplicationConstants.CALCULATIONSPAGE;

	}

	public static IndividualExpense doCalculations(String attribute,
			List<IndividualExpense> cal, List<IndividualExpense> result) {
		// TODO Auto-generated method stub
		IndividualExpense ie = new IndividualExpense();
		for (IndividualExpense er : cal) {
			for (IndividualExpense re : result) {
				if (re.getUsername().equalsIgnoreCase("chowdary")) {

					ie.setChowdary(er.getChowdary() - re.getExpense());

				} else if (re.getUsername().equalsIgnoreCase("datta")) {

					ie.setDatta(er.getDatta() - re.getExpense());

				} else if (re.getUsername().equalsIgnoreCase(ApplicationConstants.GUNDA)) {

					ie.setGunda(er.getGunda() - re.getExpense());

				} else if (re.getUsername().equalsIgnoreCase("kiran")) {
					ie.setKiran(er.getKiran() - re.getExpense());

				} else if (re.getUsername().equalsIgnoreCase(ApplicationConstants.UDAY)) {
					ie.setUday(er.getUday() - re.getExpense());

				} else if (re.getUsername().equalsIgnoreCase(ApplicationConstants.VAMSI)) {
					ie.setVamsi(er.getVamsi() - re.getExpense());

				}
				else if (re.getUsername().equalsIgnoreCase("vihar")) {
					ie.setVihar(er.getVihar()-re.getExpense());
				}
				else if(re.getUsername().equalsIgnoreCase(ApplicationConstants.ZUBAIR)){
				    ie.setZubair(er.getZubair() - re.getExpense());
				}
				else if(re.getUsername().equalsIgnoreCase(ApplicationConstants.MURALI)){
				    ie.setMurali(er.getMurali() - re.getExpense());
				}
				else if(re.getUsername().equalsIgnoreCase(ApplicationConstants.SUDHEER)){
				    ie.setSudheer(er.getSudheer() - re.getExpense());
				}
			}
		}
		return ie;
	}

	// goback
	@RequestMapping(value = "/back")
	public String back(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
		}
		return ApplicationConstants.USERPAGE;

	}
	//go back
	@RequestMapping(value = "/backToUpdate")
	public String backToUpdate(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
		}
		return ApplicationConstants.UPDATEMEMBERPAGE;

	}
	// goback
	@RequestMapping(value = "/goback")
	public String goback(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
		}
		return ApplicationConstants.ADMINPAGE;
	}
	// change password
				@RequestMapping(value = "/changePassword")
				public String changePassword(
						@ModelAttribute("newpassword") @Valid User_bean newpassword,
						BindingResult binding, Model model, HttpSession session) {
					// EmsUtil util = new EmsUtil();
					// if (util.isValidSession(session)) {
					// }
					model.addAttribute(ApplicationConstants.USERNAME, session.getAttribute(ApplicationConstants.USERNAME));
					return ApplicationConstants.CHANGEPASSWORD;
				}

				// dopassword change
				@RequestMapping(value = "/doPasswordChange", method = RequestMethod.POST)
				public String doPasswordchange(@Valid User_bean newpassword,
						BindingResult binding,
						@RequestParam("newpassword") String changedpassword, Model model,
						HttpSession session) {
					
						String uname=(String) session.getAttribute(ApplicationConstants.USERNAME);
						boolean boo=user_service.changePassword(changedpassword,uname);
						model.addAttribute("newpassword", changedpassword);
						if(boo==true){
							return "passchangesuccess";
						}
						else
							return ApplicationConstants.FAILUREPAGE;
				}
	
	@RequestMapping(value = "/expenseaddition", method = RequestMethod.POST)
	public String expadd(@ModelAttribute("User_bean") @Valid User_bean expense,
			BindingResult binding, Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {

			if (binding.hasErrors()) {
				return ApplicationConstants.EXPENSEPAGE;

			} else {
				expense = assembleExpense(expense);
				int i = user_service.addexpense(expense);
				login_service.sendExpenseAlert(expense);

			}
		}
		return "expensesuccess";

	}

	// getting the count of checkbox and dividing it with amount
	private User_bean assembleExpense(User_bean expense) {
		// TODO Auto-generated method stub
		List<String> borrowers = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(expense.getApplicableto(), ",");
		while (st.hasMoreElements()) {
			borrowers.add((String) st.nextElement());

		}

		double amount = expense.getAmount() / borrowers.size();

		for (String borrower : borrowers) {
	    if (borrower.equalsIgnoreCase("datta")) {
		expense.setDatta(amount);
	    } else if (borrower.equalsIgnoreCase(ApplicationConstants.VAMSI)) {
		expense.setVamsi(amount);
	    } else if (borrower.equalsIgnoreCase(ApplicationConstants.GUNDA)) {
		expense.setGunda(amount);
	    } else if (borrower.equalsIgnoreCase("chowdary")) {
		expense.setChowdary(amount);
	    } else if (borrower.equalsIgnoreCase("kiran")) {
		expense.setKiran(amount);
	    } else if (borrower.equalsIgnoreCase(ApplicationConstants.UDAY)) {
		expense.setUday(amount);
	    } else if (borrower.equalsIgnoreCase("vihar")) {
		expense.setVihar(amount);
	    } else if (borrower.equalsIgnoreCase(ApplicationConstants.ZUBAIR)) {
		expense.setZubair(amount);
	    } else if (borrower.equalsIgnoreCase(ApplicationConstants.MURALI)) {
		expense.setMurali(amount);
	    }
	      else if (borrower.equalsIgnoreCase(ApplicationConstants.SUDHEER)){
		expense.setSudheer(amount);
	    }

	}
	return expense;
	}

	// adding another expense
	@RequestMapping(value = "/addanotherexp")
	public String addanotherexp(Model model, User_bean addanotherexp,
			HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			model.addAttribute(ApplicationConstants.USERBEAN, new User_bean());
		}
		return ApplicationConstants.USERPAGE;
	}

	@RequestMapping(value = "/userlogout")
	public String adminlogout(Model model, login_bean userlogout,
			HttpSession session) {
		session.invalidate();
		return ApplicationConstants.LOGINPAGE;
	}

	// view all expenses
	@RequestMapping(value = "/allexpense")
	public String viewall(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			List<User_bean> v = new ArrayList<User_bean>();
			Double total = 0.0;
			v = user_service.viewall((String)session.getAttribute(ApplicationConstants.USERNAME));
			for (User_bean ub : v) {
				total += ub.getAmount();
			}
			session.setAttribute("va", v);
			session.setAttribute("all", total);
			// model.addAttribute("av", v);
			return "viewall";
		} else {
			return ApplicationConstants.FAILUREPAGE;
		}
	}

	// view your expenses
	@RequestMapping(value = "/yourexpense")
	public String viewyours(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			List<User_bean> y = new ArrayList();
			Double total = 0.0;
			y = user_service
					.viewyour((String) session.getAttribute(ApplicationConstants.USERNAME));
			for (User_bean ub : y) {
				total += ub.getAmount();
			}
			session.setAttribute("total", total);
			session.setAttribute("you", y);

			// model.addAttribute("you", y);

			return "viewyour";
		} else {
			return ApplicationConstants.FAILUREPAGE;
		}

	}
	
	//Bank Account Details ..
	@RequestMapping(value= "/bankAccount")
	public String viewBankAccountDetails(Model model,HttpSession session){
	    EmsUtil util = new EmsUtil();
	    if(util.isValidSession(session)){
		List<Map<String,Object>> accountDetails = user_service.getBankAccountDetails();
		session.setAttribute("accountDetails", accountDetails);
	    }
	    return "bankAccountDetails";
	}
}
