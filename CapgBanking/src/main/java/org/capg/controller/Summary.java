package org.capg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


@WebServlet("/Summary")
public class Summary extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Summary() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session=request.getSession(true);
		String custId=session.getAttribute("custId").toString();
		List<Transaction> transacts=new ArrayList<>();
		ILoginService loginservice=new LoginServiceImpl();
		PrintWriter out =response.getWriter();
		String mydate1=request.getParameter("fromdate");
		String[] array1=mydate1.split("-");
		int dd=Integer.parseInt(array1[0]);
		int mm=Integer.parseInt(array1[1]);
		int yyyy=Integer.parseInt(array1[2]);	
		LocalDate frmdate=LocalDate.of(yyyy, mm, dd);
		String mydate2=request.getParameter("todate");
		String[] array2=mydate2.split("-");
		int date=Integer.parseInt(array2[0]);
		int month=Integer.parseInt(array2[1]);
		int year=Integer.parseInt(array2[2]);	
		LocalDate todate=LocalDate.of(year, month, date);
		transacts=loginservice.getAlltransactions(Integer.parseInt(custId));
	//	System.out.println("getting transacts"+transacts);
		
			
		  out.println("<html>\r\n" + 
		  		"<head>\r\n" + 
		  		"<meta charset=\"ISO-8859-1\">\r\n" + 
		  		"<title>TransactionSummary</title>\r\n" +
		  		
		  		"</head>\r\n" + 
		  		"<body>\r\n" +  
		  		"	<div>\r\n" + 
		  		"		<table>\r\n" + 
		  		"			<tr>\r\n" + 
		  		"				<th colspan=\"3\">TransactionSummary</th>\r\n" + 
		  		"			</tr>\r\n" + 
		  		"			<tr>\r\n" + 
		  		"<td>from date:</td><td>");out.println(frmdate);
		  		out.println("</td>\r\r<td>To date:</td><td>");out.println(todate);
		  		out.println("</td></tr>\r\n\n<tr><th>Date</th><th>from_acc</th>"
		  				+ "<th>To_acc</th><th>Account_type</th><th>trans_type</th><th>Amount</th></tr>");
		  		
		  	
		  		
		  		long accountno = 0;
		  		double currentbal=0;
		  		List<Account> accnts=loginservice.getAllaccountdetails(custId);
		  		for(Account acc:accnts) {
		  		currentbal = currentbal+acc.getOpeningBalance();
		  		}
		  	
		  		for(Transaction tra:transacts) {
		  			
		  		
		  		if(frmdate.isBefore(tra.getTansactionDate())&&(todate.isAfter(tra.getTansactionDate()))|| (frmdate.isEqual(tra.getTansactionDate()) || todate.isEqual(tra.getTansactionDate()))) {
		  		out.println("<tr><td>"+tra.getTansactionDate()+"</td>");		  		
		  		out.println("<td>"+tra.getFromAccount().getAccountNumber()+"</td>");
		  		
		  		  				  					  			
		  		out.println("<td>"+tra.getToAccount().getAccountNumber()+"</td>");
		  		out.println("<td>"+tra.getFromAccount().getAccountType()+"</td>");
		  		out.println("<td>"+tra.getTransactionType()+"</td>");
		  		out.println("<td>"+tra.getAmount()+"</td>"+"</tr>");
		  		
		  		if(tra.getTransactionType()=="Deposit") {
		  			currentbal=currentbal+tra.getAmount();  		
		  		}
		  		else if(tra.getTransactionType()=="Withdraw") {
		  			currentbal=currentbal-tra.getAmount();  		
		  		}
		  		else if(tra.getTransactionType()=="FundTransfer") {
		  			currentbal=currentbal-tra.getAmount();  		
		  		}
		  		}
		  		}
		  		out.println("</table>\r\n" + "<table>"+"<tr><th>Current Balance in your acount is:</th><th>"+currentbal+
		  				
		  			 "</th></tr></table>\r\n</body></html>");
		  		
	}

}
