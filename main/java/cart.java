

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class cart
 */
@WebServlet("/cart")
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String id=request.getParameter("id");
		String email=session.getAttribute("email")+"";
		int quantity=1;
		int product_price=0;
		int product_total=0;
		int cart_total=0;
		boolean f=true;
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from product where id='"+id+"'");
			while(rs.next()) {
				product_price=rs.getInt(4);
				product_total=product_price;
				
			}
			ResultSet rs1=st.executeQuery("select * from cart where product_id='"+id+"' and email='"+email+"' and address is NULL");
			while(rs1.next()) {
				cart_total=rs1.getInt(5);
				cart_total=cart_total+product_total;
				quantity=rs1.getInt(3);
				quantity++;
				f=false;
			}
			if(!f) {
				st.executeUpdate("update cart set total='"+cart_total+"',quantity='"+quantity+"' where email='"+email+"' and product_id='"+id+"' and address is NULL");
				response.sendRedirect("home.jsp?msg=exist");
			}
			else
			{
				PreparedStatement pstmt = con.prepareStatement("insert into cart (email,product_id,quantity,price,total) values (?,?,?,?,?)");
				pstmt.setString(1, email);
				pstmt.setString(2, id);
				pstmt.setInt(3, quantity);
				pstmt.setInt(4, product_price);
				pstmt.setInt(5, product_total);
				pstmt.executeUpdate();
				response.sendRedirect("home.jsp?msg=added");
				
			}
		       
		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("home.jsp?msg=invalid"); 
		}
		
       
		
	}
}
