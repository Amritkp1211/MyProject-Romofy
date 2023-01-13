<%@page import="java.util.List"%>
<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.Wishlist"%>
<%
   int uid=Integer.parseInt(request.getParameter("uid")); 
   int pid=Integer.parseInt(request.getParameter("pid")); 
   
   Wishlist w=new Wishlist();
   w.setUid(uid);
   w.setPid(pid);
   WishlistDao.addToWishlist(w);
   List<Wishlist> list=WishlistDao.getWishlistByUSer(uid);
   session.setAttribute("wishlist_counter",list.size());
    /* request.setAttribute("msg","Product Added To Wishlist Successfully");
    request.getRequestDispatcher("wishlist.jsp").forward(request,response); */
    response.sendRedirect("wishlist.jsp");
    
    %>