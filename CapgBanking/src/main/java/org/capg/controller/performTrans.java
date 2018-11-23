package org.capg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.capg.model.Account;
import org.capg.model.Transaction;
import org.capg.service.ILoginService;
import org.capg.service.LoginServiceImpl;

@WebServlet("/performTrans")
public class performTrans extends HttpServlet {
	private static final long serialVersionUID = 1L;
  ILoginService loginservice=new LoginServiceImpl();
		  
    public performTrans() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		List<Account> acc=new ArrayList<>();
		Transaction trans=new Transaction();
		
		acc=loginservice.getaccountdetails(session.getAttribute("custId").toString());
		
		 PrintWriter out =response.getWriter();
		
		  out.println("<html>\r\n" + 
		  		"<head>\r\n" + 
		  		"<meta charset=\"ISO-8859-1\">\r\n" + 
		  		"<title>Deposit/Withdrawal</title>\r\n" + 
		  		
		  		"</head>\r\n" + 
		  		"<body>\r\n" + 
		  		//"<form method=\"get\" action=\"/performTrans\"> name=\"f2\" onsubmit=\"return performTrans\"" + 
		  		"<form method=\"get\" action=\"PerformTransImpl\"> " +
		  		"	<div>\r\n" + 
		  		"		<table>\r\n" + 
		  		"			<tr>\r\n" + 
		  		"				<th colspan=\"3\">performTransaction</th>\r\n" + 
		  		"			</tr>\r\n" + 
		  		"			<tr>\r\n" + 
		  		"				<td>select Account:</td>\r\n" + 
		  		"				<td>\r\n" + 
		  		"					<select name=\"accnt\" >\r\n");
		  		
		  			 
		  			 for(Account ac:acc) {
		  				 out.println("<option value="+ac.getAccountNumber()+">"); 
		  				out.println( ac.getAccountNumber());
		  				out.println("-");
		  				out.println(ac.getAccountType());
		  				 out.println("</option>"); 
		  				 }
		  				
		  		 
		  			out.println("</td></tr>\r\n<tr><td><input type=\"radio\" name=\"choice\" value=\"Deposit\" required >Deposit <input type=\"radio\" name=\"choice\" value=\"Withdraw\" required>Withdraw  "+
		  			"</td></tr> "+
		  			"<tr><td>Amount:</td><td><input type=\"text\" name=\"amount\" size=\"20\" required> </td></tr> "+
		  			 "<tr><td>Description</td><td><input type=\"text\" name=\"description\"  size=\"20\" required></td></tr> "+
		  			"<tr><td><input type=\"submit\" name=\"doTransaction\" size=\"20\" required></tr></td></table> </form>\r\n" + 
		  			 		"</body>\r\n" + 
		  			 		"</html>");
//		  			if(acc!=null) {
//		  			
//	}
	}
}
