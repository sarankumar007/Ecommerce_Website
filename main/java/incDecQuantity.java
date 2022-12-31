

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
 * Servlet implementation class incDecQuantity
 */
@WebServlet("/incDecQuantity")
public class incDecQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String id=request.getParameter("id");
		String q=request.getParameter("q");
		String email=session.getAttribute("email")+"";
		int quantity=0;
		int price=0;
		int total=0;
		int final_total=0;
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from cart where product_id='"+id+"' and email='"+email+"' and address is NULL");
			while(rs.next()) {
				price=rs.getInt(4);
			quantity=rs.getInt(3);
			total=rs.getInt(5);
				
			}
			if(quantity==1 && q.equals("dec")) {
				response.sendRedirect("myCart.jsp?msg=notpossible");
			}
			else if(q.equals("dec") && quantity!=1) {
				quantity--;
				total=total-price;
				st.executeUpdate("update cart set quantity='"+quantity+"',total='"+total+"' where email='"+email+"' and product_id='"+id+"' and address is NULL");
				response.sendRedirect("myCart.jsp?msg=dec");
			}
			else {
				quantity++;
				total=total+price;
				st.executeUpdate("update cart set quantity='"+quantity+"',total='"+total+"' where email='"+email+"' and product_id='"+id+"' and address is NULL");
				response.sendRedirect("myCart.jsp?msg=inc");
			}
			
		
		       
		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("home.jsp?msg=invalid");
		}
		
       
		
	}
}

