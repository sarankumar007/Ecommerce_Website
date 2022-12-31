<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">My Orders <i class='fab fa-elementor'></i></div>
<table>
        <thead>
          <tr>
            <th scope="col">S.No</th>
            <th scope="col">Product Name</th>
            <th scope="col">category</th>
            <th scope="col"><i class="fa fa-inr"></i>  Price</th>
            <th scope="col">Quantity</th>
            <th scope="col"><i class="fa fa-inr"></i> Sub Total</th>
            <th scope="col">Order Date</th>
             <th scope="col">Expected Delivery Date</th>
             <th scope="col">Payment Method</th>
              <th scope="col">Status</th>
              
          </tr>
        </thead>
        <tbody>
<%try{
	int sno=0;
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from product inner join cart where cart.product_id=product.id and cart.email='"+email+"' and orderDate is not null");
	while(rs.next()){
		sno++;
	%>

          <tr>
            <td><%=sno %></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3) %></td>
            <td><i class="fa fa-inr"></i> <%=rs.getString(4) %></td>
            <td><%=rs.getString(8) %></td>
            <td><i class="fa fa-inr"></i> <%=rs.getString(10) %></td>
             <td><%=rs.getString(16) %></td>
              <td><%=rs.getString(17) %></td>
               <td><%=rs.getString(18) %></td>
               <td><%=rs.getString(20) %></td>
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