package org.capg.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.capg.model.Customer;
import org.capg.model.LoginBean;
import org.capg.service.ILoginService;
import org.capg.service.LoginServiceImpl;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		ILoginService loginService=new LoginServiceImpl();
		
		//Capture the data from View
		String userName=req.getParameter("userName");
		String userPwd=req.getParameter("userPwd");
		
		
		//Convert the input into Model
		LoginBean login=new LoginBean();
		login.setUserName(userName);
		login.setUserPassword(userPwd);
		
		
		//Call Your business Logic
		//Navigate to next page
		Customer customer=loginService.isValidLogin(login);
		if(customer!=null) {
			//ServletContext context=getServletContext(); 
			//Session creation and bind user into session object
			HttpSession session=req.getSession(true);
			session.setAttribute("myUser", customer.getFirstName());
			session.setAttribute("custId", customer.getCustomerId());
			
			//context.setInitParameter("userName", userName);
			
		//	req.getRequestDispatcher("success").forward(req, resp);
			req.getRequestDispatcher("MainPageServlet").forward(req, resp);
			//resp.sendRedirect("success");
		}
		else
			//req.getRequestDispatcher("index.html").forward(req, resp);
			req.getRequestDispatcher("index.html").include(req, resp);
			//resp.sendRedirect("index.html");
		
		
	}

	
	
}
