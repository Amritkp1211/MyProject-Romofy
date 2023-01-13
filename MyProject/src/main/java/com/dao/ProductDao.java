package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Product;
import com.util.ProjectUtil;

public class ProductDao {
	
	public static void addProduct(Product p) {
		try {

			Connection conn=ProjectUtil.createConnection();
			String sql="insert into product(product_category,product_name,product_price,product_image,product_desc,uid) values(?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,p.getProduct_category());
			pst.setString(2, p.getProduct_name());
			pst.setInt(3, p.getProduct_price());
			pst.setString(4,p.getProduct_image());
			pst.setString(5, p.getProduct_desc());
			pst.setInt(6, p.getUid());
			pst.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static List<Product> getProductsBySeller(int uid){
		List<Product> list=new ArrayList<Product>();
		try {
			 Connection conn=ProjectUtil.createConnection();
			 String sql="select * from product where uid=?";
			 PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setInt(1, uid);
			 ResultSet rs=pst.executeQuery();
			 while(rs.next()) {
				 Product p=new Product();
				 p.setPid(rs.getInt("pid"));
				 p.setUid(rs.getInt("uid"));
				 p.setProduct_category(rs.getString("product_category"));
				 p.setProduct_name(rs.getString("product_name"));
				 p.setProduct_desc(rs.getString("product_desc"));
				 p.setProduct_image(rs.getString("product_image"));
				 p.setProduct_price(rs.getInt("product_price"));
				 list.add(p);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Product getProductsByPid(int uid,int pid){
		
		Product p=null;
		try {
			 Connection conn=ProjectUtil.createConnection();
			 String sql="select * from product where uid=? and pid=?";
			 PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setInt(1, uid);
			 pst.setInt(2, pid);
			 ResultSet rs=pst.executeQuery();
			 if(rs.next()) {
				  p=new Product();
				  
				 p.setPid(rs.getInt("pid"));
				 p.setUid(rs.getInt("uid"));
				 p.setProduct_category(rs.getString("product_category"));
				 p.setProduct_name(rs.getString("product_name"));
				 p.setProduct_desc(rs.getString("product_desc"));
				 p.setProduct_image(rs.getString("product_image"));
				 p.setProduct_price(rs.getInt("product_price"));
				 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	 
	public static void updateProduct(Product p)
	{
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="update product set product_category=?,product_name=?,product_desc=?,product_price=? where pid=? and uid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,p.getProduct_category());
			pst.setString(2, p.getProduct_name());
			pst.setString(3, p.getProduct_desc());
			pst.setInt(4, p.getProduct_price());
			pst.setInt(5, p.getPid());
			pst.setInt(6, p.getUid());
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public static void deleteProduct(int pid,int uid)
	{
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="delete from product where pid=? and uid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pid);
			pst.setInt(2, uid);
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Product> getAllProducts(){
		List<Product> list=new ArrayList<Product>();
		try {
			 Connection conn=ProjectUtil.createConnection();
			 String sql="select * from product";
			 PreparedStatement pst=conn.prepareStatement(sql);
			 ResultSet rs=pst.executeQuery();
			 while(rs.next()) {
				 Product p=new Product();
				 p.setPid(rs.getInt("pid"));
				 p.setUid(rs.getInt("uid"));
				 p.setProduct_category(rs.getString("product_category"));
				 p.setProduct_name(rs.getString("product_name"));
				 p.setProduct_desc(rs.getString("product_desc"));
				 p.setProduct_image(rs.getString("product_image"));
				 p.setProduct_price(rs.getInt("product_price"));
				 list.add(p);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
      public static Product getProductsByPid(int pid){
		
		Product p=null;
		try {
			 Connection conn=ProjectUtil.createConnection();
			 String sql="select * from product where pid=?";
			 PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setInt(1, pid);
			 ResultSet rs=pst.executeQuery();
			 if(rs.next()) {
				  p=new Product();
				 p.setPid(rs.getInt("pid"));
				 p.setUid(rs.getInt("uid"));
				 p.setProduct_category(rs.getString("product_category"));
				 p.setProduct_name(rs.getString("product_name"));
				 p.setProduct_desc(rs.getString("product_desc"));
				 p.setProduct_image(rs.getString("product_image"));
				 p.setProduct_price(rs.getInt("product_price"));
				 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

       

}
