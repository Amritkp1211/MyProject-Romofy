package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.bean.Contact;
import com.util.ProjectUtil;

public class ContactDao {
   
	public static void submitContact(Contact c) {
		
		try {
			Connection conn=ProjectUtil.createConnection();
			String Sql="insert into contact(name,email,mobile,address,msg) values(?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(Sql);
			pst.setString(1,c.getName());
			pst.setString(2,c.getEmail());
			pst.setString(3,c.getMobile());
			pst.setString(4,c.getAddress());
			pst.setString(5,c.getMsg());
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
