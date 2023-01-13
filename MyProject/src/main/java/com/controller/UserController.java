package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Cart;
import com.bean.User;import com.bean.Wishlist;
import com.dao.CartDao;
import com.dao.UserDao;
import com.dao.WishlistDao;
import com.service.Services;


@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		
		if(action.equalsIgnoreCase("sign up")) {
			boolean flag=UserDao.CheckEmail(request.getParameter("email"));
			
			if(flag==true) {
				request.setAttribute("msg", "Email Already registerd");
	            request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
			
			else if(request.getParameter("password").equals(request.getParameter("cpassword"))){
			User u=new User();
			u.setFname(request.getParameter("fname"));
			u.setLname(request.getParameter("lname"));
			u.setEmail(request.getParameter("email"));
			u.setMobile(request.getParameter("mobile"));
			u.setAddress(request.getParameter("address"));
			u.setPassword(request.getParameter("password"));
			u.setUsertype(request.getParameter("usertype"));
			UserDao.signupUser(u);
			request.setAttribute("msg", "User SignUp Successfully");
            request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg", "password and confirm passwrd doesnt match");
	            request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
			
		}
		else if(action.equalsIgnoreCase("login")){
			User u=UserDao.loginUser(request.getParameter("email"));
			
			if(u!=null) {
				
				if(u.getPassword().equals(request.getParameter("password"))) {			
					HttpSession session=request.getSession();
					session.setAttribute("u",u);
					 if(u.getUsertype().equals("user")) {
						    List<Wishlist> list=WishlistDao.getWishlistByUSer(u.getId());
						    session.setAttribute("wishlist_counter",list.size());
					        
					        List<Cart> list1=CartDao.getCartByUSer(u.getId());
						    session.setAttribute("cart_counter",list1.size());
					        request.getRequestDispatcher("index.jsp").forward(request, response);

					  }
					 else {
							request.getRequestDispatcher("seller_index.jsp").forward(request, response);

					  }
				}
				else {
					request.setAttribute("msg","Incorrect Password");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				
			}
			else {
				request.setAttribute("msg","email id not resgistred");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
			
		}
		
		else if(action.equalsIgnoreCase("change password")) {
			
			HttpSession session=request.getSession();
			User u=(User)session.getAttribute("u");
			if(u.getPassword().equals(request.getParameter("old_password"))) {
				
				    if(request.getParameter("new_password").equals(request.getParameter("cnew_password"))) {
					
					UserDao.changePassword(u.getEmail(),request.getParameter("new_password"));
					response.sendRedirect("logout.jsp");
				    }
				    else { 
					request.setAttribute("msg","New password and Confirm New password does not match");
                           if(u.getUsertype().equals("user")) {
           				     request.getRequestDispatcher("change_password.jsp").forward(request, response);

                             }
                           else {
           				   request.getRequestDispatcher("seller_change_password.jsp").forward(request, response);

                           }
					
				        }
			    }
			else {
				request.setAttribute("msg","Old password does not matched");
				   if(u.getUsertype().equals("user")) {
       				request.getRequestDispatcher("change_password.jsp").forward(request, response);

                   }
                   else {
       				request.getRequestDispatcher("seller_change_password.jsp").forward(request, response);

                   }
				
				}
		  }
		
		
	         else if(action.equalsIgnoreCase("send otp")) {
			
		             boolean flag=UserDao.CheckEmail(request.getParameter("email"));
		                if(flag==true){
				             Random t= new Random();
				             int minRange=1000, maxRange=9999;
				             int value =t.nextInt(maxRange-minRange)+minRange;
				             Services.sendMail(request.getParameter("email"), value);
				             request.setAttribute("email",request.getParameter("email"));
				             request.setAttribute("otp",value);
				             request.getRequestDispatcher("otp.jsp").forward(request, response);
				
		                }
		                
		                else{
		                      request.setAttribute("msg","Email not registered");
						      request.getRequestDispatcher("forgot_password.jsp").forward(request, response);
						}
		
		     }
		 
	         else if(action.equalsIgnoreCase("Verify OTP")) {
	        	  
	        	 int otp=Integer.parseInt(request.getParameter("otp"));
	        	 int uotp=Integer.parseInt(request.getParameter("uotp"));
	        	 String email=request.getParameter("email");
	        	 
	        	 if(otp==uotp) {
	        		 request.setAttribute("email",email);
	        		 request.getRequestDispatcher("new_password.jsp").forward(request, response);
	        	 }
	        	 else {
	        		 request.setAttribute("msg","Invalid OTP");
	        		 request.setAttribute("email",email);
		             request.setAttribute("otp",otp);
		             request.getRequestDispatcher("otp.jsp").forward(request, response);
	        	 }
	        	 
           }
		
	         else if(action.equalsIgnoreCase("update Password")) {
	        	 String email=request.getParameter("email");
	        	 String new_password=request.getParameter("new_password");
	        	 String cnew_password=request.getParameter("cnew_password");
	        	 
	        	 if(new_password.equals(cnew_password)) {
	        		 UserDao.changePassword(email, new_password);
	        		 request.setAttribute("msg","password updated successfully");
		             request.getRequestDispatcher("login.jsp").forward(request, response);
	        	 }
	        	 else {
	        		 request.setAttribute("msg","password and confirm password does not matched");
	        		 request.setAttribute("email",email);
		             request.getRequestDispatcher("new_password.jsp").forward(request, response);
	        	 }

	        	 
	         }
		
		
		
		
	}
}
