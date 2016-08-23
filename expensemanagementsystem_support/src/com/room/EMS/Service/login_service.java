package com.room.EMS.Service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.room.EMS.Beans.User_bean;
import com.room.EMS.Beans.login_bean;
import com.room.EMS.DataAccess.login_dao;
import com.room.EMS.util.EmsUtil;

@Service
public class login_service {

    @Autowired
    login_dao login_dao;

    public String authenticate(login_bean login_bean) {
	// TODO Auto-generated method stub
	return login_dao.authenticate(login_bean);
    }

    public int Add(login_bean add_member) {
	// TODO Auto-generated method stub
	/*add_member.setPassword(EmsUtil.encrypt(add_member.getPassword()));*/
	add_member.setPassword(add_member.getPassword());
	return login_dao.Add(add_member);
    }

    public List showdata() {
	// TODO Auto-generated method stub
	return login_dao.showdata();
    }

    public login_bean showmember(String id) {
	// TODO Auto-generated method stub
	return login_dao.showmember(id);
    }

    public boolean updateMember(login_bean login_bean) {
	// TODO Auto-generated method stub
	/*login_bean.setPassword(EmsUtil.encrypt(login_bean.getPassword()));*/
	login_bean.setPassword(login_bean.getPassword());
	return login_dao.updateMember(login_bean);
    }

    public boolean DeleteMember(String id) {
	// TODO Auto-generated method stub
	return login_dao.DeleteMember(id);
    }

    public List viewall() {
	// TODO Auto-generated method stub
	return login_dao.viewall();
    }

    public boolean bulkoperations() {
	// TODO Auto-generated method stub
	return login_dao.bulkoperations();
    }

    public boolean DeleteExpense(String id) {
	// TODO Auto-generated method stub
	return login_dao.DeleteExpense(id);
    }

    public List<User_bean> getStatement(String fromdate, String todate) {
	// TODO Auto-generated method stub
	return login_dao.getStatement(fromdate, todate);
    }

    public void sendExpenseAlert(User_bean expense) {

	List<String> toAddress = login_dao.getEmailAddress(expense
		.getApplicableto());

	StringBuilder builder = new StringBuilder();
	for (String value : toAddress) {
	    builder.append(value);
	    builder.append(",");
	}
	String text = builder.toString();
	text = text.substring(0, text.lastIndexOf(","));
	int amt = (int) (expense.getAmount() / (expense.getApplicableto()
		.split(",").length));
	final String username = "noreply.roomexpenses@gmail.com";
	final String password = "room@123";

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
		new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		    }
		});

	try {

	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress("noreply@roomexpense.com"));
	    message.setRecipients(Message.RecipientType.TO,
		    InternetAddress.parse(text));
	    message.setSubject("Room expense added against you!!!");
	    message.setContent(
		    "<h2>Hello Expense Management User ,<br><br> This Email is to notify you that , MR."
			    + expense.getUsername()
			    + " has added an amount of Rs."
			    + amt
			    + " for "
			    + expense.getName()
			    + " on date "
			    + expense.getDate()
			    + " against you..</h2><br><h3>In case of any Questions/Queries Please write to sukanth_g@yahoo.co.in..</h3>",
		    "text/html");
	    Transport.send(message);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public List<String> getActiveUsers() {
	return login_dao.getActiveUsers();
    }

    public List<String> getActiveEmails(String user) {
	
	return login_dao.getActiveEmails(user);
    }

    public List<User_bean> getActiveExpenses() {
	return login_dao.getActiveExpenses();
	
    }

}
