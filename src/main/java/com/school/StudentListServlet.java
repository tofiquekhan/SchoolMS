
package com.school;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//
///**
// * Servlet implementation class StudentListServlet
// */
//@WebServlet("/StudentListServlet")
//public class StudentListServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public StudentListServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<User> studentList = new ArrayList<>();
//        Config config = new Config();
//        try {
//            Connection connection = config.getcon();
//            System.out.print("rrrrrrrrrr");
//            
//            PreparedStatement ps = connection
//                    .prepareStatement("SELECT * FROM \"User\".\"User\" WHERE userrole='student'");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                User student = new User();
//                student.setId(rs.getInt("id"));
//                student.setName(rs.getString("name"));
//                student.setEmail(rs.getString("email"));
//                student.setMobileno(rs.getString("mobile"));
//                student.setAddress(rs.getString("address"));
//                student.setGender(rs.getString("gender"));
//                student.setDob(rs.getString("dob"));
//                studentList.add(student);
//            }
//            // Close the result set, statement, and connection
//            rs.close();
//            ps.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle the exception, e.g., show an error message
//            response.getWriter().println("An error occurred while fetching student data.");
//            return;
//        }
//
//        // Convert studentList to JSON
//        Gson gson = new Gson();
//        String json = gson.toJson(studentList);
//
//        // Set content type and write JSON response
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);
//    }
//}
