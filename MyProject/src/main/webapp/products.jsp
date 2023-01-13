<%@page import="com.dao.ProductDao"%>
<%@page import="com.bean.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ include file="header.jsp" %>
       
<!DOCTYPE html>
<html lang="en">
   <head></head>
   <!-- body -->
   <body class="main-layout footer_to90 project_page">
      <div class="blue_bg">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="titlepage">
                     <h2>My Products</h2>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- project section -->
      <%
      
      List<Product> list=ProductDao.getAllProducts();
      
      %>
      <div id="project" class="project">
         <div class="container">
           
            <div class="row">
            <div class="product_main">
             <%
           for(Product p:list)
           {
        	    %>
                  <div class="project_box ">
                     <div class="dark_white_bg" ><img  src="product_images/<%=p.getProduct_image()%>" alt="#"/></div>
                     <h3> <%=p.getProduct_name()%> </h3>
                     <h3> Rs.<%=p.getProduct_price() %></h3>
                     <a href="product_details.jsp?pid=<%=p.getPid()%>"><input type="button" value="Details" class="btn btn-primary"></a>
                     
                     <%-- <a href="seller_product_edit.jsp?pid=<%=p.getPid()%>&uid=<%=u.getId()%>"><input type="button" value="EDIT" class="btn btn-primary"></a>
                     <a href="seller_product_delete.jsp?pid=<%=p.getPid()%>&uid=<%=u.getId()%>"><input type="button" value="DELETE" class="btn btn-danger"></a>
                      --%>
                      </div>
            <%
           }
            %>
            </div>
            </div>
         </div>
      </div>
      <!-- end project section -->
      <!--  footer -->
      <footer>
         <div class="footer">
            <div class="container">
               <div class="row">
                  <div class="col-md-3">
                     <div class="inror_box">
                        <h3>INFORMATION </h3>
                        <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable</p>
                     </div>
                  </div>
                  <div class="col-md-3">
                     <div class="inror_box">
                        <h3>MY ACCOUNT </h3>
                        <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable</p>
                     </div>
                  </div>
                  <div class="col-md-3">
                     <div class="inror_box">
                        <h3>ABOUT  </h3>
                        <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable</p>
                     </div>
                  </div>
                  <div class="col-md-3">
                     <div class="inror_box">
                        <h3>CONTACTS  </h3>
                        <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable</p>
                     </div>
                  </div>
               </div>
            </div>
            <div class="copyright">
               <div class="container">
                  <div class="row">
                     <div class="col-md-12">
                        <p>© 2019 All Rights Reserved. Design by<a href="https://html.design/"> Free Html Templates</a></p>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </footer>
      <!-- end footer -->
      <!-- Javascript files-->
      <script src="js/jquery.min.js"></script>
      <script src="js/popper.min.js"></script>
      <script src="js/bootstrap.bundle.min.js"></script>
      <script src="js/jquery-3.0.0.min.js"></script>
      <!-- sidebar -->
      <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
      <script src="js/custom.js"></script>
   </body>
</html>

