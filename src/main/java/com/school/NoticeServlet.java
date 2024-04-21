package com.school;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       

    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
//       List<Notice> noticeList = getNoticeListFromDatabase(); // Implement this method to fetch data from the database

//       request.setAttribute("noticeList", noticeList);
//       request.getRequestDispatcher("yourNoticeJspPage.jsp").forward(request, response);
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String notice = request.getParameter("notice");
   
   try {
       Connection connection = Config.getcon();
       PreparedStatement ps = connection.prepareStatement("INSERT INTO \"User\".\"Notice\" (notice) VALUES (?)");
       ps.setString(1, notice);
       ps.executeUpdate();
       
       // Retrieve the notice list from the application scope
       List<String> noticeList = (List<String>) getServletContext().getAttribute("noticeList");
       if (noticeList == null) {
           noticeList = new ArrayList<>();
           getServletContext().setAttribute("noticeList", noticeList);
       }
       
       // Add the new notice to the list
       noticeList.add(notice);
   } catch (SQLException e) {
       e.printStackTrace();
   }

   // Redirect to the referring page
   response.sendRedirect(request.getHeader("Referer"));
}}

