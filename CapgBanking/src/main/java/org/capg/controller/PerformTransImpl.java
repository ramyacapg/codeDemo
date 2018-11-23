package org.capg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.capg.model.Account;
import org.capg.model.AccountType;
import org.capg.model.Transaction;
import org.capg.service.ILoginService;
import org.capg.service.LoginServiceImpl;


@WebServlet("/PerformTransImpl")
public class PerformTransImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ILoginService loginservice=new LoginServiceImpl();

	
    public PerformTransImpl() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.sendRedirect("/performTrans");
		HttpSession session=request.getSession();
		int custId=Integer.parseInt(session.getAttribute("custId").toString());
		Transaction trans=new Transaction();
		 PrintWriter out =response.getWriter();
			//String acnt=Long.parseLong(request.getParameter("accnt"));
			String choice=request.getParameter("choice");
			trans.setTransactionType(choice);
			
		
			String transAmount=request.getParameter("amount");
			if(Integer.parseInt(transAmount)!=0) {
			trans.setTansactionDate(LocalDate.now());
			String des=request.getParameter("description");
//			String[] arr=acnt.split("-");
//			long accNo=Long.parseLong(arr[0]);
//			String accType=arr[1];
			//Account frmAccount=new Account();
			Account toAccount=new Account();
			Long accNo=Long.parseLong(request.getParameter("accnt"));
			toAccount=loginservice.getdetails_ToAccount(accNo);
		
			if(trans.getTransactionType().equals("Deposit")) {
			
			Double bal=toAccount.getOpeningBalance()+Double.parseDouble(transAmount);
			toAccount.setOpeningBalance(bal);		
			
			}
			else if(trans.getTransactionType().equals("Withdraw") && toAccount.getOpeningBalance()>Double.parseDouble(transAmount)) {
				Double bal2=toAccount.getOpeningBalance()-Double.parseDouble(transAmount);
				toAccount.setOpeningBalance(bal2);
				
			}
			else {
				out.println("<p>invalid choice</p>");
				//response.sendRedirect("/performTrans");
			}
			//loginservice.createToaccount(toAccount);
			loginservice.updateAccount(toAccount);
		
			trans.setAmount(0);
			trans.setAmount(Double.parseDouble(transAmount));
			trans.setDescription(des);
			trans.setFromAccount(toAccount);
			trans.setTansactionDate(LocalDate.now());
			trans.setCustomerId(custId);
			Account dummyac=new Account();
			dummyac.setAccountNumber(0);			
			trans.setToAccount(dummyac);
			
			loginservice.createTransaction(trans);
			out.println("<html>\r\n" + 
			  		"<head>\r\n" + 
			  		"<meta charset=\"ISO-8859-1\">\r\n" + 
			  		"<title>Deposit/Withdrawal</title>\r\n" + 
			  		
			  		"</head>\r\n" + 
			  		"<body>\r\n" + 
			  		"<p>Transaction Successful..\r\n <br> your current bal of accountNo"+toAccount.getAccountNumber()+ "is " );
			out.println(toAccount.getOpeningBalance());
			
			
			//response.sendRedirect("performTrans");
			}
			else
			{
				out.println("alert(\"enter valid amount\")");
				 response.setIntHeader("Refresh", 30);
				response.sendRedirect("performTrans");
			}
	}
	
	}


