<%@page import="com.dao.CartDao"%>
<%@page import="com.dao.ProductDao"%>
<%@page import="com.bean.Product"%>
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
      Product p=ProductDao.getProductsByPid(pid);
      c.setProduct_price(p.getProduct_price());
      c.setProduct_qty(1);
      c.setTotal_price(p.getProduct_price());
      c.setPayment_status(false);
      CartDao.addToCart(c);
      
      
      List<Cart> list=CartDao.getCartByUSer(uid);

   session.setAttribute("cart_counter",list.size());
    /* request.setAttribute("msg","Product Added To Wishlist Successfully");
    request.getRequestDispatcher("wishlist.jsp").forward(request,response); */
    response.sendRedirect("cart.jsp");
    
    %>