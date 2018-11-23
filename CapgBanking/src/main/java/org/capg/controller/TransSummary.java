package org.capg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TransSummary")
public class TransSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public TransSummary() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out =response.getWriter();
        out.println("<html>\r\n" +

"<html>\r\n" + 
	"<head>\r\n" + 
	"<meta charset=\"ISO-8859-1\">\r\n" + 
	"<title>Transaction summary</title>\r\n" + 
	
	"</head>\r\n" + 
	"<body>\r\n" + 
	 
	"<form method=\"get\" action=\"Summary\"> \n" +
	"	<div>\r\n" + 
	"		<table>\r\n" + 
                            "\r\n" + 
                            "                                                <tr>\r\n" + 
                            "\r\n" + 
                            "                                                       <th colspan=\"3\">TransactionSummary</th>\r\n" + 
                            "\r\n" + 
                            "                                                </tr>\r\n" + 
                            "\r\n" + 
                            "                                                <tr>\r\n" + 
                            "\r\n" + 
                            "                            <td>FromDate[dd-mm-yyyy]</td>\r\n" + 
                            "\r\n" + 
                            "                            <td><input type=\"text\" name=\"fromdate\"></td></tr>\r\n" + 
                            "\r\n" + 
                            "                            <tr><td>ToDate[dd-mm-yyyy]</td>\r\n" + 
                            "\r\n" + 
                            "                            <td><input type=\"text\" name=\"todate\" required></td></tr>\r\n" + 
                            "\r\n" + 
                            "                            <tr><td><input type=\"submit\" name=\"submit\" size=\"20\" required></td></tr>\r\n" + 
                            "\r\n" + 
                            "                            </table>\r\n" + 
                            "\r\n" + 
                            "                            </form>\r\n" + 
                            "\r\n" + 
                            "</body>\r\n" + 
                            "\r\n" + 
                            "</html>");

}


}
