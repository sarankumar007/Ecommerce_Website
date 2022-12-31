<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
<%@page import="java.sql.*" %>
<%if(session.getAttribute("email")==null){
	response.sendRedirect("login.jsp");}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style>
h3
{
	color: yellow;
	text-align: center;
}
</style>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">Home <i class="fa fa-institution"></i></div>
<% String msg=request.getParameter("msg");
if("added".equals(msg)){
%>
<h3 class="alert">Product added successfully!</h3>
<%} %>
<%if("exist".equals(msg)){ %>
<h3 class="alert">Product already exist in you cart! Quantity  increased!</h3>
<%} %>
<%if("invalid".equals(msg)){ %>
<h3 class="alert">Something went wrong... Try again</h3>
<%} %>
<table>
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-inr"></i> Price</th>
            <th scope="col">Add to cart <i class='fas fa-cart-plus'></i></th>
          </tr>
        </thead>
        <tbody>
<%
try{
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
	 PreparedStatement pstmt = con.prepareStatement("select * from product where active='yes'");
	 ResultSet rs=pstmt.executeQuery();
	 while(rs.next()){
%>
          <tr>
            <td><%=rs.getString(1) %></td>
            <td><%=rs.getString(2) %></td>
            <td><%=rs.getString(3) %></td>
            <td><%=rs.getString(4) %><i class="fa fa-inr"></i> </i></td>
            <td><a href="cart?id=<%=rs.getString(1)%>">Add to cart <i class='fas fa-cart-plus'></i></a></td>
          </tr>
<%}}
catch(Exception e){
	System.out.println(e);
}%>
        </tbody>
      </table>
      <br>
      <br>
      <br>

</body>
</html>