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
import org.capg.model.Transaction;
import org.capg.service.ILoginService;
import org.capg.service.LoginServiceImpl;


@WebServlet("/FundTransferImpl")
public class FundTransferImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ILoginService loginservice=new LoginServiceImpl();
    public FundTransferImpl() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.sendRedirect("/performTrans");
		HttpSession session=request.getSession();
		int custId=Integer.parseInt(session.getAttribute("custId").toString());
		Transaction trans=new Transaction();
		 PrintWriter out =response.getWriter();
			
//			String acntfrm=request.getParameter("accntfrm");
//			String acntto=request.getParameter("accntto");
			//String choice=request.getParameter("choice");
			String transAmount=request.getParameter("amount");
			String des=request.getParameter("description");
//			String[] arr=acntfrm.split("-");
//			String accNo=arr[0];
//			
//			String[] arr1=acntto.split("-");
//			int accNoto=Integer.parseInt(arr1[0]);
			trans.setAmount(Double.parseDouble(transAmount));
			trans.setDescription(des);
			trans.setTansactionDate(LocalDate.now());
			trans.setCustomerId(custId);
			Account frmAccount=new Account();
			frmAccount=loginservice.getdetails_ToAccount(Long.parseLong(request.getParameter("accntfrm")));
			trans.setFromAccount(frmAccount);
			Account toAccount=new Account();
			toAccount=loginservice.getdetails_ToAccount(Long.parseLong(request.getParameter("accntto")));
			trans.setToAccount(toAccount);
			trans.setTransactionType("FundTransfer");
			loginservice.createTransaction(trans);
			if(frmAccount.getOpeningBalance()>Double.parseDouble(transAmount)) {
				frmAccount.setOpeningBalance(frmAccount.getOpeningBalance()-Double.parseDouble(transAmount));
				toAccount.setOpeningBalance(toAccount.getOpeningBalance()+Double.parseDouble(transAmount));
				
				}
				loginservice.updateAccount(frmAccount);
				loginservice.updateAccount(toAccount);
			out.println("<html>\r\n" + 
			  		"<head>\r\n" + 
			  		"<meta charset=\"ISO-8859-1\">\r\n" + 
			  		"<title>Deposit/Withdrawal</title>\r\n" + 
			  		
			  		"</head>\r\n" + 
			  		"<body>\r\n" + 
			  		"<p>Transaction Successful..\r\n your current bal of " );
			out.println(frmAccount.getAccountNumber());
			out.println("is </p>");
			out.println(frmAccount.getOpeningBalance());
			//response.sendRedirect("performTrans");
			
			
		
	}

	
	

}
