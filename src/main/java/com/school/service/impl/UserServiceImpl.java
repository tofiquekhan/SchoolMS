package com.school.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;

import com.school.Config;
import com.school.dto.UserDto;
import com.school.exception.UserNotFoundException;
import com.school.service.UserService;
import com.school.util.ConnectionUtil;

public class UserServiceImpl implements UserService{
	
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
    
    public UserDto registration(UserDto userDto) {
    	

	       Connection connection = null;
	       try {
	           connection = ConnectionUtil.getConnection();

	           PreparedStatement ps = connection.prepareStatement(
	                   "INSERT INTO \"User\".\"User\" (name, email, password, mobileno, gender, address, dob, userrole) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

	           ps.setString(1, userDto.getName());
	           ps.setString(2, userDto.getEmail());
	           ps.setString(3, userDto.getPassword());
	           ps.setLong(4, userDto.getMobileno());
	           ps.setString(5, userDto.getGender());
	           ps.setString(6, userDto.getAddress());
	           ps.setString(7, userDto.getDob());
	           ps.setString(8, userDto.getRole());

	           int count = ps.executeUpdate();
	           if (count > 0) {

	               int id = getUserId(userDto.getEmail());
	               userDto.setId(id);
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	       return userDto;
    }
    
    public int getUserId(String email) {
    	 Connection connection = null;
	       try {
	          
	           connection = ConnectionUtil.getConnection();
	           PreparedStatement ps = connection.prepareStatement(
	                   "select id from tableName where email = ?");

	           ps.setString(1, email);
	          

	           ResultSet rs = ps.executeQuery();
	           if(rs.next()) {
	        	   return rs.getInt(1);
	           }else {
	        	   throw new UserNotFoundException("User Not Found With Email : "+email);
	           }
    	
    }
	       catch (SQLException e) {
			// TODO: handle exception
	    	   e.printStackTrace();
		}
	       return 0;

}
}
