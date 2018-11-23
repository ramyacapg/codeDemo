package org.capg.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.capg.model.Address;
import org.capg.model.Customer;
import org.capg.service.ILoginService;
import org.capg.service.LoginServiceImpl;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ILoginService loginService=new LoginServiceImpl();
		
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String dateOfbirth=request.getParameter("dateOfbirth");
		String addressline1=request.getParameter("addressline1");
		String addressline2=request.getParameter("addressline2");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String pincode=request.getParameter("pincode");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String custPwd=request.getParameter("custPwd");
		
		
		Address address=new Address();
		address.setAddressLine1(addressline1);
		address.setAddressLine2(addressline2);
		address.setCity(city);
		address.setState(state);
		address.setPincode(pincode);
		
		
		
		Customer customer=new Customer();
		customer.setAddress(address);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		
		String[] dateParts=dateOfbirth.split("-");
		customer.setDateOfBirth(LocalDate.of(Integer.parseInt(dateParts[0]),
				Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2])));
		
		customer.setEmailId(email);
		customer.setMobile(mobile);
		customer.setCustomerPwd(custPwd);
		
		
		if(loginService.createCustomer(customer)) {
			System.out.println("Record Inserted...");
			response.sendRedirect("./index.html");
		}
		
		
	}

}
