<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.Wishlist"%>
<%
   int uid=Integer.parseInt(request.getParameter("uid")); 
   int pid=Integer.parseInt(request.getParameter("pid")); 
  
    Cart c=new Cart();
    c.setUid(uid);
    c.setPid(pid);
    CartDao.removeFromCart(c);
   
    /* request.setAttribute("msg","Product Added To Wishlist Successfully");
    request.getRequestDispatcher("wishlist.jsp").forward(request,response); */
    List<Cart> list=CartDao.getCartByUSer(uid);
    session.setAttribute("cart_counter",list.size());
    response.sendRedirect("cart.jsp");
    
    %>