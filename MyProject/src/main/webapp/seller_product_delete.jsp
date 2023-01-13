<%@page import="com.dao.ProductDao"%>
<%
   int pid=Integer.parseInt(request.getParameter("pid"));
   int uid=Integer.parseInt(request.getParameter("uid"));
    
   ProductDao.deleteProduct(pid, uid);
   response.sendRedirect("seller_products.jsp");
%>