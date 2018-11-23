package org.capg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/MainPageServlet")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		out.println("<html>"
				+ "<head>"
				+ "<title>CapgBanking</title>\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"./styles/mainStyles.css\">"
				+ "</head>"
				+ "<body>\r\n" + 
				"\r\n" + 
				"<div id=\"mainCnt\">\r\n" + 
				"	<h2 align=\"center\">CapgBanking Process</h2>\r\n"
				+ "<div class=\"greet\">Hello!"+ session.getAttribute("myUser") +"</div>" + 
				"	<hr>\r\n" + 
				"	\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"view/createAccount.html\" target=\"mainfrm\">Create Account</a></li>\r\n" + 
				"		<li><a href=\"./performTrans\" target=\"mainfrm\">Deposit/withdraw</a></li>\r\n" + 
				"		<li><a href=\"./FundTransfer\" target=\"mainfrm\">Fund Transfer</a></li>\r\n" + 
				"		<li><a href=\"./TransSummary\" target=\"mainfrm\">Transaction Summary</a></li>\r\n" + 
				"		<li><a href=\"./LogoutServlet\">Logout</a></li>\r\n" + 
				"	\r\n" + 
				"	</ul>\r\n" + 
				"	<div id=\"ctrCnt\">\r\n" + 
				"		<iframe name=\"mainfrm\" width=\"800px\" height=\"300px\" src=\"view/titlePage.html\"></iframe>\r\n" + 
				"	\r\n" + 
				"	</div>\r\n" + 
				"<div id=\"footer\">\r\n" + 
				"<div style=\"float:left;padding-left: 10px;\">CapgBanking</div>\r\n" + 
				"<div style=\"margin-left:650px;\">Capg-FLP-2018</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"\r\n" + 
				"</body>"
				
				+ "</html>");
		
	
	}

}
