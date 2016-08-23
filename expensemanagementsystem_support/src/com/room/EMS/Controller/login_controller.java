package com.room.EMS.Controller;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;
import javax.validation.Valid;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.room.EMS.Beans.IndividualExpense;
import com.room.EMS.Beans.User_bean;
import com.room.EMS.Beans.login_bean;
import com.room.EMS.Service.login_service;
import com.room.EMS.Service.user_service;
import com.room.EMS.constants.ApplicationConstants;
import com.room.EMS.util.EmsUtil;
import com.room.EMS.util.HeaderAndFooter;
/**
 * 
 * @author sukanthgunda
 * 
 *
 */
@Controller
@SessionAttributes("username")
public class login_controller {
	EmsUtil util;
	@Autowired
	login_service login_service;
	@Autowired
	user_service user_service;

	@RequestMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute(new login_bean());
		return ApplicationConstants.LOGINPAGE;
	}

	// try again
	@RequestMapping(value = "/tryagain")
	public String tryagain(Model model, HttpSession session) {
		model.addAttribute(new login_bean());
		return ApplicationConstants.LOGINPAGE;
	}

	@RequestMapping(value = "/AddMember")
	public String PreAdd(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			model.addAttribute("Add_member", new login_bean());
		}
		return ApplicationConstants.ADDMEMBERPAGE;
	}

	// update
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/UpdateMember")
	public String preupdate(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			List<login_bean> l = new ArrayList<login_bean>();
			l = login_service.showdata();
			session.setAttribute("l", l);
		}
		return ApplicationConstants.UPDATEMEMBERPAGE;
	}
	
	//back to statement page
		@RequestMapping(value = "/backtoStatement")
		public String backtoStatement(@Valid User_bean statement,BindingResult result,Model model,HttpSession session) {
			EmsUtil util = new EmsUtil();
			if (util.isValidSession(session)) {
				model.addAttribute(ApplicationConstants.STATEMENT, new User_bean());
			}
			return ApplicationConstants.STATEMENT;
		}

	
	//view statement
	
		@RequestMapping(value = "/viewstatement",method=RequestMethod.POST)
		public String viewstatement(@ModelAttribute("statement") @Valid User_bean statement,BindingResult binding, Model model, HttpSession session)  {
			EmsUtil util = new EmsUtil();
			if (util.isValidSession(session)) {
				List<User_bean> s=login_service.getStatement(statement.getFromdate(),statement.getTodate());
			session.setAttribute("statementList", s);
			session.setAttribute("from", statement.getFromdate());
			session.setAttribute("to", statement.getTodate());
			}
			return ApplicationConstants.VIEWSTATEMENTPAGE;
		}
		

	@RequestMapping(value = "/DoUpdate")
	public String update(Model model, @RequestParam("id") String id,
			login_bean login_bean, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			model.addAttribute("l1", login_service.showmember(id));
		}
		return ApplicationConstants.DOUPDATEPAGE;
	}
	
			// statement
			@RequestMapping(value = "/statement")
			public String statement(@Valid User_bean statement,BindingResult result,Model model,HttpSession session) {
				model.addAttribute(ApplicationConstants.STATEMENT, new User_bean());
				return ApplicationConstants.STATEMENT;
			}

	@RequestMapping(value = "/Update")
	public String postupdate(
			@ModelAttribute("l1") @Valid login_bean login_bean,
			BindingResult result, Model model, HttpSession session) {

		boolean b = login_service.updateMember(login_bean);
		
		if (b == true)
			return ApplicationConstants.ADDSUCCESSPAGE;
		else
			return ApplicationConstants.FAILUREPAGE;
	}

	// delete list
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/DeleteMember")
	public String predelete(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			List<login_bean> l = new ArrayList<login_bean>();
			l = login_service.showdata();
			model.addAttribute("dl", l);
		}
		return ApplicationConstants.DELETESUCCESSPAGE;
	}

	@RequestMapping(value = "/DoDelete")
	public String delete(Model model, @RequestParam("id") String id,
			login_bean login_bean, HttpSession session) {

		boolean b = login_service.DeleteMember(id);

		if (b == true)
			return ApplicationConstants.DELETESUCCESSPAGE;

		else
			return ApplicationConstants.FAILUREPAGE;
	}

	// login validation
	@RequestMapping(value = "/login_bean", method = RequestMethod.POST)
	public String SubmitLogin(@Valid login_bean login_bean,
			BindingResult binding, Model model, HttpSession session) {
		if (binding.hasErrors()) {
			return ApplicationConstants.LOGINPAGE;
		} else {
			String role = login_service.authenticate(login_bean);
			if (role != null) {
				session.setAttribute(ApplicationConstants.USERNAME, login_bean.getUsername());
				if (role.equals(ApplicationConstants.ADMIN)) {
					model.addAttribute(ApplicationConstants.USERNAME, login_bean.getUsername());
					return ApplicationConstants.ADMINPAGE;
				} else if (role.equals(ApplicationConstants.USER)) {
					model.addAttribute(ApplicationConstants.USERNAME, login_bean.getUsername());
					return ApplicationConstants.USERPAGE;
				}
			}
		}
		return ApplicationConstants.FAILUREPAGE;
	}

	// logout
	@RequestMapping(value = "/adminlogout")
	public String adminlogout(Model model, login_bean adminlogout,
			HttpSession session) {
		session.invalidate();
		return ApplicationConstants.LOGINPAGE;
	}

	// add another member
	@RequestMapping(value = "/addanother")
	public String addanother(Model model, login_bean addanother,
			HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
		}
		return ApplicationConstants.ADMINPAGE;
	}

	// all expense manager
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/all")
	public String allexpensemanager(Model model, HttpSession session) {
		EmsUtil util = new EmsUtil();
		if (util.isValidSession(session)) {
			List<User_bean> v = new ArrayList<User_bean>();
			Double total = 0.0;
			v = login_service.viewall();
			for (User_bean ub : v) {
				total += ub.getAmount();
			}
			session.setAttribute("va", v);
			session.setAttribute("all", total);
			// model.addAttribute("av", v);
			return ApplicationConstants.ALLEXPENSEMANAGERPAGE;
		} else {
			return ApplicationConstants.FAILUREPAGE;
		}
	}

	// delete expense
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete")
	public String delete(Model model, HttpSession session,@RequestParam("id") String id) {
		
		boolean b = login_service.DeleteExpense(id);
		if (b == true){
			EmsUtil util = new EmsUtil();
			if (util.isValidSession(session)) {
				List<User_bean> v = new ArrayList<User_bean>();
				Double total = 0.0;
				v = login_service.viewall();
				for (User_bean ub : v) {
					total += ub.getAmount();
				}
				session.setAttribute("va", v);
				session.setAttribute("all", total);
		
			
			}
			return ApplicationConstants.ALLEXPENSEMANAGERPAGE;
		}
		else
		return ApplicationConstants.FAILUREPAGE;
	}
	

	// add member
	@RequestMapping(value = "/AddData", method = RequestMethod.POST)
	public String Add(
			@ModelAttribute("Add_member") @Valid login_bean Add_member,
			BindingResult binding, Model model, HttpSession session) {

		if (binding.hasErrors()) {
			return ApplicationConstants.ADDMEMBERPAGE;
		} else {

			int i = login_service.Add(Add_member);
			if (i == 1) {
				return ApplicationConstants.ADDSUCCESSPAGE;
			} else
				return ApplicationConstants.FAILUREPAGE;
		}

	}

	// make all active as inactive
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/bulkoperations")
	public String bulk(Model model, HttpSession session) {
		boolean r = login_service.bulkoperations();
		if (r) {
			List<User_bean> v = new ArrayList<User_bean>();
			Double total = 0.0;
			v = login_service.viewall();
			for (User_bean ub : v) {
				total += ub.getAmount();
			}
			session.setAttribute("va", v);
			session.setAttribute("all", total);
			return ApplicationConstants.BULKOPERATIONSPAGE;
		} else
			return ApplicationConstants.NOTHINGUPDATEDPAGE;
	}
	
	//Generate Reports
	@RequestMapping(value ="/generateReports")
	public String generateReports(Model model, HttpSession session) throws MalformedURLException{
	    /*local code*/
	    Calendar cal = Calendar.getInstance();
	    int currYear = cal.get(Calendar.YEAR);
	    int currDay = cal.get(Calendar.DAY_OF_MONTH);
	    String currMonth = new SimpleDateFormat("MMM").format(cal.getTime());
	    String today = (currYear+"-"+currMonth+"-"+currDay+"-"+cal.getTimeInMillis()).toString();
	    boolean flag = true ;
	    OutputStream invoiceFile =null;
	    File homedir = FileSystemView.getFileSystemView().getHomeDirectory();
	    File folderPath =new File(homedir+File.separator+ApplicationConstants.FOLDERPATH);
	    if(!folderPath.exists()){
		folderPath.mkdir();
	    }
	     File filePath = new File(folderPath+File.separator + today);
	     if(!filePath.exists()){
		 filePath.mkdir();
		    }
	    List<String> activeUsers = new ArrayList<String>();
	    activeUsers = login_service.getActiveUsers();
	    for(String activeUser : activeUsers){
		try {
		    invoiceFile = new FileOutputStream(new File(filePath+"/EXPENSEINVOICE-")+activeUser.toUpperCase()+"_"+new Date().toString()+ApplicationConstants.PDFEXTENSTION);
		    flag=invoiceGeneration(invoiceFile,activeUser,flag,session);
		} 
		
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}	
	    }
	   
	   sendExpenseReport(filePath,activeUsers);
	    /*local code end*/
	  
	    /* prod code*/
	   /*Calendar cal = Calendar.getInstance();
	    int currYear = cal.get(Calendar.YEAR);
	    int currDay = cal.get(Calendar.DAY_OF_MONTH);
	    String currMonth = new SimpleDateFormat("MMM").format(cal.getTime());
	    String today = (currYear+"-"+currMonth+"-"+currDay+"-"+cal.getTimeInMillis()).toString();
	    boolean flag = true ;
	    OutputStream invoiceFile =null;
	   //File homedir = FileSystemView.getFileSystemView().getHomeDirectory();
	  // File folderPath =new File("/tmp"+File.separator+ApplicationConstants.FOLDERPATH);
	    File folderPath =new File("/tmp");
	  // File filePath = new File(folderPath+File.separator + today);
	    //if(!filePath.exists()){
		//filePath.mkdir();
	    //}
	    List<String> activeUsers = new ArrayList<String>();
	    activeUsers = login_service.getActiveUsers();
	    for(String activeUser : activeUsers){
		try {
		    invoiceFile = new FileOutputStream(new File(folderPath+"/EXPENSEINVOICE-")+activeUser.toUpperCase()+ApplicationConstants.PDFEXTENSTION);
		    flag=invoiceGeneration(invoiceFile,activeUser,flag,session);
		} 
		
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}	
	    }
	   
	   sendExpenseReport(folderPath,activeUsers);*/
	   /* prod code end*/
	    if(flag){
		model.addAttribute(ApplicationConstants.FILEPATH, new File(folderPath.toString()).toURI().toURL());
		 return ApplicationConstants.REPORTSUCCESS; 
	    }
	    else{
		return ApplicationConstants.FAILUREPAGE;
	    } 
	}
	/**
	      * @author Sukanth Gunda
	      * @param multipart
	      * @param filename
	      */
	      private static void addAttachment(Multipart multipart, File attachment)
	      {
	          try {
	          DataSource source = new FileDataSource(attachment.toString());
	          BodyPart messageBodyPart = new MimeBodyPart();       
	          messageBodyPart.setDataHandler(new DataHandler(source));
	          messageBodyPart.setFileName(attachment.getName());
	          multipart.addBodyPart(messageBodyPart);
	          }
	          catch (MessagingException e) {
	                  e.printStackTrace();
	            }
	      }
	
	  /**
	      * Description : This method sends emails to the users .
	      * @param sourcePath
	      * @author sukanthgunda
	      */
	      public void sendExpenseReport(File sourcePath,List<String> activeUsers){
	          try{
	              for(String user : activeUsers){ 
	              	    StringBuilder builder = new StringBuilder();
                	    final String username = "noreply.roomexpenses@gmail.com";
                	    final String password = "room@123";
                	    Calendar cal = Calendar.getInstance();
                	    int currYear = cal.get(Calendar.YEAR);
                	    int currDay = cal.get(Calendar.DAY_OF_MONTH);
                	    String currMonth = new SimpleDateFormat("MMM").format(cal.getTime());
                	    String msgSubject = "ROOM EXPENSE -" + currDay + "-" + currMonth.toUpperCase() + "-" + currYear;
                	    String msgBody = "PFA Attached files .. password to open the file is username@123 Ex: gunda@123";
                	    
                	    List<String> emailAddress = login_service.getActiveEmails(user);
                		for (String value : emailAddress) {
                		    builder.append(value);
                		    builder.append(",");
                		}
                		String toAddress = builder.toString();
                		toAddress = toAddress.substring(0, toAddress.lastIndexOf(","));
                	    Properties props = System.getProperties();
                	    props.put("mail.smtp.auth", "true");
                	    props.put("mail.smtp.starttls.enable", "true");
                	    props.put("mail.smtp.host", "smtp.gmail.com");
                	    props.put("mail.smtp.port", "587");
                	    File[] generatedFiles = sourcePath.listFiles((FileFilter) new PrefixFileFilter("EXPENSEINVOICE-"+user.toUpperCase(), IOCase.SENSITIVE));
                	    Session session = Session.getInstance(props,
            	    		new javax.mail.Authenticator() {
            	    		    protected PasswordAuthentication getPasswordAuthentication() {
            	    			return new PasswordAuthentication(username, password);
            	    		    }
            	    		});
                	    MimeMessage message = new MimeMessage(session);
        	            message.setSubject(msgSubject);
        	            message.setFrom(new InternetAddress("noreply@roomexpense.com"));
        	            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));
        	            Multipart multipart = new MimeMultipart("alternative"); 
        	            BodyPart messageBodyPart = new MimeBodyPart();
        	            StringBuffer bodyPart = new StringBuffer();
        	            bodyPart.append("Hi " + user.toUpperCase() + "," +"\n");
        	            bodyPart.append(msgBody);
        	            bodyPart.append("\n");
        	            bodyPart.append("Regards," + "\n");
        	            bodyPart.append("Expense Management System.");
        	            messageBodyPart.setText(bodyPart.toString());
        	            multipart.addBodyPart(messageBodyPart);
        	            if(generatedFiles.length > 0){
        	                for(File file : generatedFiles){
        	                  if(file.isFile()){
        	                                   addAttachment(multipart,file);
        	                              }
        	                        }
        	                  }
		message.setContent(multipart);
		/*local code*/
		//Transport.send(message);
		/*prod code*/
		//Transport.send(message);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
	
	/**
	 * 
	 * @param currentUser
	 * @return
	 */
	public IndividualExpense getFinalCal(String currentUser){
	    /*Start Calculation*/
	    List<IndividualExpense> cal = new ArrayList<IndividualExpense>();
		List<IndividualExpense> result = new ArrayList<IndividualExpense>();
		cal = user_service.getCalculations(currentUser);
		result = user_service.getIndividualExpense(currentUser);

		IndividualExpense ie = new IndividualExpense();
		ie = User_controller.doCalculations(currentUser, cal,result);
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
		/*End Calculation*/
		return ie;
	}
	
	public  boolean invoiceGeneration(OutputStream invoiceFile,String activeUser,boolean flag,HttpSession session){
		try {
            	    Document document = new Document();
            	    String currentUser = activeUser.trim();
            	    String pdfPassword = currentUser + "@123";
            	    Font redFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, Font.BOLD, new CMYKColor(0, 255, 0, 0));
            	    Font blueFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, Font.BOLD, new CMYKColor(255, 0, 0, 0));
            	    Font greenFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, Font.BOLD, new CMYKColor(94, 0, 100, 0));
            	    PdfWriter writer = PdfWriter.getInstance(document, invoiceFile);
            	    writer.setPageEvent( new HeaderAndFooter());
            	    	/*Encrypting the PDF File*/
            		writer.setEncryption(pdfPassword.getBytes(), pdfPassword.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
            		// Inserting Image in PDF
            		Image image = Image.getInstance("http://sageone-uk-wordpress.s3.amazonaws.com/images/addOns/xpenditure/large.png");
            		image.scaleAbsolute(150f, 80f);// image width,height
            		image.setAlignment(50);
            		
            		IndividualExpense ie=getFinalCal(currentUser);
			/*double chowdary = ie.getChowdary();
			double datta 	= ie.getDatta();*/
			double gunda 	= ie.getGunda();
			double uday 	= ie.getUday();
			double vamsi 	= ie.getVamsi();
			/*double kiran	= ie.getKiran();
			double vihar	= ie.getVihar();*/
			double zubair	= ie.getZubair();
			double murali	= ie.getMurali();
			double sudheer	= ie.getSudheer();
				
		// Inserting Table in PDF
		PdfPTable table1 = new PdfPTable(2);
		PdfPCell cell1 = new PdfPCell(new Paragraph(ApplicationConstants.TABLEHEADER1));
		cell1.setColspan(2);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setPadding(10.0f);
		cell1.setBackgroundColor(new BaseColor(204,0,102));

		table1.addCell(cell1);
		table1.addCell(new Paragraph(ApplicationConstants.NAME, redFont));
		table1.addCell(new Paragraph(ApplicationConstants.FINALAMOUNT, redFont));

		/*table.addCell("chowdary");
		table.addCell(new Double(chowdary).toString());
		
		table.addCell("datta");
		table.addCell(new Double(datta).toString());*/
		
		if(!ApplicationConstants.GUNDA.equalsIgnoreCase(currentUser)){
		    table1.addCell(ApplicationConstants.GUNDA); 
		    table1.addCell(new Paragraph(new Double(gunda).toString()+" Rs/-" , (gunda < 0) ? redFont : greenFont));   
		}
		
		if(!ApplicationConstants.UDAY.equalsIgnoreCase(currentUser)){
		    table1.addCell(ApplicationConstants.UDAY);
		    table1.addCell(new Paragraph(new Double(uday).toString()+" Rs/-", (uday < 0) ? redFont : greenFont));
		}
		
		if(!ApplicationConstants.VAMSI.equalsIgnoreCase(currentUser)){
		    table1.addCell(ApplicationConstants.VAMSI);
		    table1.addCell(new Paragraph(new Double(vamsi).toString()+" Rs/-", (vamsi < 0) ? redFont : greenFont));
		}
		
		
		/*table.addCell("kiran");
		table.addCell(new Double(kiran).toString());
		
		table.addCell("vihar");
		table.addCell(new Double(vihar).toString());*/
		
		if(!ApplicationConstants.ZUBAIR.equalsIgnoreCase(currentUser)){
		    table1.addCell(ApplicationConstants.ZUBAIR);
		    table1.addCell(new Paragraph(new Double(zubair).toString()+" Rs/-", (zubair < 0) ? redFont : greenFont));
		}
		
		if(!ApplicationConstants.MURALI.equalsIgnoreCase(currentUser)){
		    table1.addCell(ApplicationConstants.MURALI);
		    table1.addCell(new Paragraph(new Double(murali).toString()+" Rs/-", (murali < 0) ? redFont : greenFont));
		}
		
		if(!ApplicationConstants.SUDHEER.equalsIgnoreCase(currentUser)){
		    table1.addCell(ApplicationConstants.SUDHEER);
		    table1.addCell(new Paragraph(new Double(sudheer).toString()+" Rs/-", (sudheer < 0) ? redFont : greenFont));
		}
		table1.setSpacingBefore(30.0f); // Space Before table1 starts, like margin-top in CSS
		table1.setSpacingAfter(30.0f); // Space After table1 starts, like margin-Bottom in CSS
		
		List<User_bean> activeExpenses =login_service.getActiveExpenses();
		
		PdfPTable table2 = new PdfPTable(5);
		PdfPCell cell2 = new PdfPCell(new Paragraph(ApplicationConstants.TABLEHEADER2));
		cell2.setColspan(5);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setPadding(15.0f);
		cell2.setBackgroundColor(new BaseColor(204,0,102));
		
		table2.addCell(cell2);
		table2.addCell(new Paragraph(ApplicationConstants.USRNAME, redFont));
		table2.addCell(new Paragraph(ApplicationConstants.EXPENSENAME,redFont));
		table2.addCell(new Paragraph(ApplicationConstants.EXPENSEDATE,redFont));
		table2.addCell(new Paragraph(ApplicationConstants.EXPENSEAMT,redFont));
		table2.addCell(new Paragraph(ApplicationConstants.APPLICABLE,redFont));
		
		for(User_bean bean: activeExpenses){
		    table2.addCell(bean.getUsername());
		    table2.addCell(bean.getName());
		    table2.addCell(bean.getDate());
		    table2.addCell(new Double(bean.getAmount()).toString());
		    table2.addCell(bean.getApplicableto());   
		}
		
		table2.setSpacingBefore(40.0f); // Space Before table1 starts, like margin-top in CSS
		table2.setSpacingAfter(40.0f); // Space After table1 starts, like margin-Bottom in CSS
		
		// Now Insert Every Thing Into PDF Document
		document.open();// PDF document opened........
		document.addCreator(ApplicationConstants.EXPENSEMANAGEMENTSYSTEM);
		document.addAuthor("Admin@EMS");
		document.addTitle("Expense Report " + new Date().toString());
		document.add(image);
		document.add(Chunk.NEWLINE); // Something like in HTML :-)
		document.add(new Paragraph("Dear "+activeUser.toUpperCase()+" ,"));
		document.add(new Paragraph("Document Generated On - "+ new Date().toString()));
		document.add(table1);
		document.add(table2);
		document.add(Chunk.NEWLINE);
		document.add(new Paragraph(ApplicationConstants.QUESTIONSQUERIES));
		document.close();
		invoiceFile.close();
		} catch (DocumentException e) {
		    e.printStackTrace();
		    flag =false;
		} catch (MalformedURLException e1) {
		    e1.printStackTrace();
		    flag =false;
		} catch (IOException e1) {
		    e1.printStackTrace();
		    flag =false;
		}
		catch(Exception e){
		    e.printStackTrace();
		    flag =false;
		}
		return flag;
	}
}
