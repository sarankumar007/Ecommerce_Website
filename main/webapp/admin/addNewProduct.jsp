<%@include file="adminHeader.jsp" %>
<%@include file="../footer.jsp" %>
<%@page import="java.sql.*" %>

<html>
<head>
<link rel="stylesheet" href="../css/addNewProduct-style.css">
<title>Add New Product</title>
</head>
<body>
<%String msg=request.getParameter("msg");
if("done".equals(msg)){
%>
<h3 class="alert">Product Added Successfully!</h3>
<%} %>
<% if("invalid".equals(msg)){%>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<%}%>
<% 
int id=1;
try{
	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
	 PreparedStatement pstmt = con.prepareStatement("select max(id) from product");
	 ResultSet rs=pstmt.executeQuery();
	 while(rs.next()){
		 id=rs.getInt(1);
		 id++;
	 }
	
}
catch(Exception e){
	
}

%>
<form action="../addNewProduct" method="post">

<h3 style="color: yellow;">Product ID: <%out.println(id); %></h3>
<input type="hidden" name="id" value="<%out.println(id); %>">


<div class="left-div">
 <h3>Enter Name</h3>
 <input class="input-style" type="text" name="name" placeholder="Enter Name" required>
<hr>
</div>

<div class="right-div">
<h3>Enter Category</h3>
  <input class="input-style" type="text" name="category" placeholder="Enter Category" required>
<hr>
</div>

<div class="left-div">
<h3>Enter Price</h3>
  <input class="input-style" type="number" name="price" placeholder="Enter Price" required>
<hr>
</div>

<div class="right-div">
<h3>Active</h3>
   <select name="active" class="input-style">
   <option value="yes">Yes</option>
    <option value="no">No</option>
   </select>
<hr>
</div>
 <button class="button" >Save<i class='far fa-arrow-alt-circle-right'></i></button>
</form>
</body>
<br><br><br>
</body>
</html>