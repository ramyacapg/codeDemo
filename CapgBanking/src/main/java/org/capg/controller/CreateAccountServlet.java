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
import org.capg.model.Customer;
import org.capg.service.ILoginService;
import org.capg.service.LoginServiceImpl;


@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ILoginService loginService=new LoginServiceImpl();
		
		String accountType=request.getParameter("accountType");
		String balance=request.getParameter("balance");
		String description=request.getParameter("description");
		
		
		
		
		Account account=new Account();
		account.setAccountType(AccountType.valueOf(accountType));
		account.setDescription(description);
		account.setOpeningBalance(Double.parseDouble(balance));
		account.setOpeningDate(LocalDate.now());
		
		
		HttpSession session=request.getSession();
		int custId=Integer.parseInt(session.getAttribute("custId").toString());
		Customer customer=new Customer();
		customer.setCustomerId(custId);
		account.setCustomer(customer);
		
		Account acc=loginService.createAccount(account);
		if(acc!=null) {
			
		  PrintWriter out =response.getWriter();
		  out.println("<html>\r\n" + 	
		  		"<body>"+"<p>"+"your Account created successfully"+"</p>"+"</body>\r\n" + 
		  				"</html>");
		  response.sendRedirect("./view/createAccount.html");
			System.out.println("Account Inserted.....");
		}
		else
			System.out.println("Account Insertion Failed.....");
		
		
	}

}
