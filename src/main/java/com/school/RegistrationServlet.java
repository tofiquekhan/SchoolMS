package com.school;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.dto.UserDto;
import com.school.exception.UserNotFoundException;
import com.school.service.UserService;
import com.school.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegisterServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService userService  = new UserServiceImpl();
		 PrintWriter out = response.getWriter();
		 

			String name = request.getParameter("name");
	       String email = request.getParameter("email");
	       String mobile = request.getParameter("mobile");
	       long mobileNo = Long.parseLong(mobile);
	       String gender = request.getParameter("gender");
	       String address = request.getParameter("address");
	       String dob = request.getParameter("dob");
	       String userrole = request.getParameter("userrole");
	       String password = request.getParameter("password");
	       
	       UserDto userDto = new UserDto();
	       userDto.setAddress(address);
	       try {
	UserDto savedUserDto = userService.registration(userDto);
	           if (savedUserDto.getId()>0) {

	               response.setContentType("text/html");
	               out.println("<h3>User registered successfully!</h3>");
	               RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
	               rd.include(request, response);
	          
	       } }catch (UserNotFoundException e) {
			// TODO: handle exception
		
	           response.setContentType("text/html");
	           out.println("<h3>Error: " + e.getMessage() + "</h3>");
	       } 
	}

}
