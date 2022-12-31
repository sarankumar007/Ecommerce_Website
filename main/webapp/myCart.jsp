<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Cart</title>
<style>
h3
{
	color: yellow;
	text-align: center;
}
</style>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">My Cart <i class='fas fa-cart-arrow-down'></i></div>
<%String msg=request.getParameter("msg");
if("notpossible".equals(msg)){
%>
<h3 class="alert">There is only one Quantity! So click on remove!</h3>
<%} %>
<% 
if("inc".equals(msg)){%>
<h3 class="alert">Quantity  Increased Successfully!</h3>
<%} %>
<%if("dec".equals(msg)) {%>
<h3 class="alert">Quantity  Decreased Successfully!</h3>
<%} %>
<%if("removed".equals(msg)){ %>)
<h3 class="alert">Product Successfully Removed!</h3>
<%} %>
<table>
<thead>
<%
int total=0;
int sno=0;
try{
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
	 PreparedStatement pstmt = con.prepareStatement("select sum(total) from cart where email='"+email+"'and address is NULL");
	 ResultSet rs=pstmt.executeQuery();
	 while(rs.next()){
		 total=rs.getInt(1);
	 }
%>
          <tr>
            <th scope="col" style="background-color: yellow;">Total: <i class="fa fa-inr"><%=total %></i> </th>
          <%if(total>0) %>  <th scope="col"><a href="addressPaymentForOrder.jsp">Proceed to order</a></th><% %>
          </tr>
        </thead>
        <thead>
          <tr>
          <th scope="col">S.No</th>
            <th scope="col">Product Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-inr"></i> price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Sub Total</th>
            <th scope="col">Remove <i class='fas fa-trash-alt'></i></th>
          </tr>
        </thead>
        <tbody>
      <%
      
      PreparedStatement pstmt1 = con.prepareStatement("select * from product inner join cart on product.id=cart.product_id and cart.email='"+email+"' and cart.address is NULL");
 	 ResultSet rs1=pstmt1.executeQuery(); 
      while(rs1.next()){
    	  sno++;
      %>
          <tr>

           <td><%=sno %></td>
            <td><%=rs1.getString(2) %></td>
            <td><%=rs1.getString(3) %></td>
            <td><i class="fa fa-inr"></i><%=rs1.getString(4) %> </td>
            <td><a href="incDecQuantity?id=<%=rs1.getString(1)%>&q=inc"><i class='fas fa-plus-circle'></i></a> <%=rs1.getString(8) %> <a href="incDecQuantity?id=<%=rs1.getString(1)%>&q=dec"><i class='fas fa-minus-circle'></i></a></td>
            <td><i class="fa fa-inr"><%=rs1.getString(10) %></i> </td>
            <td><a href="removeCart?id=<%=rs1.getString(1)%>">Remove <i class='fas fa-trash-alt'></i></a></td>
          </tr>
<%}}
catch(Exception e){
	System.out.println(e);
}
%>
        </tbody>
      </table>
      <br>
      <br>
      <br>

</body>
</html>