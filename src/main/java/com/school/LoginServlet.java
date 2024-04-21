package com.school;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.school.util.ConnectionUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String userRole = authenticateUser(name, password, role);

        if (userRole != null) {
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            session.setAttribute("userRole", userRole);

            if (userRole.equals("admin")) {
                response.sendRedirect("Admin.jsp");
            } else if (userRole.equals("teacher")) {
                response.sendRedirect("Teacher.jsp");
            } else if (userRole.equals("student")) {
                response.sendRedirect("Student.jsp");
            }
        } else {
            response.sendRedirect("Login.jsp");
        }
    }

    private String authenticateUser(String name, String password, String role) {
        try {

        	Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT userrole FROM \"User\".\"User\" WHERE name=? AND password=? AND userrole=?");
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("userrole");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "error";
    }

}